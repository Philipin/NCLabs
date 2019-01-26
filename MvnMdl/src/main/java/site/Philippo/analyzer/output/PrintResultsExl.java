package site.Philippo.analyzer.output;

import site.Philippo.analyzer.Result;
import site.Philippo.sorters.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.charts.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTBoolean;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTLineSer;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTPlotArea;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrintResultsExl {

    public void writeToExl(List<Result> list, File exlFile) throws IOException {
        if(list == null || exlFile == null || list.isEmpty()) throw new IllegalArgumentException();
        Map<String,Map<Sorters,Result>> map = getOrderedMap(list);
        XSSFWorkbook workbook = new XSSFWorkbook();
        for(Map.Entry<String,Map<Sorters,Result>> entry : map.entrySet()){
            XSSFSheet sheet = workbook.createSheet(entry.getKey());

            boolean nFlag = true;
            int rowInd = 0;
            int sorterCol = 0;
            for(Map.Entry<Sorters, Result> sortersResultEntry: entry.getValue().entrySet()){

                if(nFlag){
                    sheet.createRow(0).createCell(0).setCellValue("Size");
                    for(Integer size : sortersResultEntry.getValue().getElapsedTime().keySet()){
                        XSSFRow row = sheet.createRow(++rowInd);
                        row.createCell(0).setCellValue(size);
                    }
                    nFlag = false;
                }
                Sorters sorter = sortersResultEntry.getKey();
                Result result = sortersResultEntry.getValue();
                sorterCol = crtSorterColumn(sheet, sorter);

                for(Map.Entry<Integer, Long> sizeRes : result.getElapsedTime().entrySet()){
                    XSSFRow row = sheet.getRow(findSizeIndex(sheet, sizeRes.getKey()));
                    row.createCell(sorterCol).setCellValue(sizeRes.getValue());
                }
            }
            createLineChart(sheet, rowInd + 1, sorterCol + 1);
        }
        workbook.write(new FileOutputStream(exlFile));
    }


    private void createLineChart(XSSFSheet dataSheet, int rows, int columns) {

        Drawing drawing = dataSheet.createDrawingPatriarch();
        ClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 1, rows + 3, 8, 25);

        Chart chart = drawing.createChart(anchor);
        ChartLegend legend = chart.getOrCreateLegend();
        legend.setPosition(LegendPosition.RIGHT);

        LineChartData data = chart.getChartDataFactory().createLineChartData();

        ChartAxis bottomAxis = chart.getChartAxisFactory().createCategoryAxis(AxisPosition.BOTTOM);
        ValueAxis leftAxis = chart.getChartAxisFactory().createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);

        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(dataSheet, new CellRangeAddress(0, rows - 1, 0, 0));

        for (int i = 1; i < columns; i++) {
            ChartDataSource<Number> ys1 = DataSources.fromNumericCellRange(dataSheet, new CellRangeAddress(0, rows - 1, i, i));
            LineChartSeries series1 = data.addSeries(xs, ys1);
            series1.setTitle(dataSheet.getRow(0).getCell(i).getStringCellValue());
        }

        chart.plot(data, bottomAxis, leftAxis);

        XSSFChart xssfChart = (XSSFChart) chart;
        CTPlotArea plotArea = xssfChart.getCTChart().getPlotArea();
        plotArea.getLineChartArray()[0].getSmooth();
        CTBoolean ctBool = CTBoolean.Factory.newInstance();
        ctBool.setVal(false);
        plotArea.getLineChartArray()[0].setSmooth(ctBool);
        for (CTLineSer ser : plotArea.getLineChartArray()[0].getSerArray()) {
            ser.setSmooth(ctBool);
        }
    }


    private int crtSorterColumn(XSSFSheet sheet, Sorters sorter){
        XSSFRow row = sheet.getRow(0);
        if(row == null) row = sheet.createRow(0);
        int colInd = 1;
        String sorterName;
        while(row.getCell(colInd) != null) colInd++;
        XSSFCell cell = row.createCell(colInd);
        if(sorter.getClass() == HalfSort.class){
            HalfSort halfSort = (HalfSort) sorter;
            sorterName = halfSort.toString();
        }
        else sorterName = sorter.getClass().getAnnotation(Sorter.class).name();
        cell.setCellValue(sorterName);
        sheet.autoSizeColumn(colInd);
        return colInd;
    }



    private int findSizeIndex(XSSFSheet sheet, int size){
        int rowInd = 1;
        while (sheet.getRow(rowInd) != null){
            XSSFRow row = sheet.getRow(rowInd++);
            if (row.getCell(0).getRawValue().equals(size + ".0")) return --rowInd;
        }
        throw new IllegalArgumentException();
    }



    private Map<String,Map<Sorters,Result>> getOrderedMap(List<Result> list){
        Map<String,Map<Sorters,Result>> map = new HashMap<>();
        for(Result result : list){
            if(map.containsKey(result.getFiller())){
                map.get(result.getFiller()).put(result.getSorter(), result);
            }
            else {
                Map<Sorters,Result> entry = new HashMap<>();
                entry.put(result.getSorter(), result);
                map.put(result.getFiller(), entry);
            }
        }
        return map;
}


}
