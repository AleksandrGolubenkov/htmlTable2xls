package ru.golubenkov.htmlTable2xls.logic;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import ru.golubenkov.htmlTable2xls.bean.Cell;
import ru.golubenkov.htmlTable2xls.bean.Row;
import ru.golubenkov.htmlTable2xls.bean.Table;

public class XlsBuilder {

   public static HSSFWorkbook buildXlsDocument(Table table) {
      HSSFWorkbook workbook = new HSSFWorkbook();
      try {
         HSSFSheet sheet = workbook.createSheet("ResultReport");
         sheet.setDefaultColumnWidth(25);

         int numberOfRows = table.getAllRows().size();
         int numberOfColumns = table.getNumberOfColumns();
         boolean[][] offsetMatrix = new boolean[numberOfRows][numberOfColumns];

         for (Row row : table.getAllRows()) {
            HSSFRow sheetRow = sheet.createRow(row.getNumber());
            for (Cell cell : row.getCells()) {
               CellRangeAddress cellRangeAddress = new CellRangeAddress(
                       row.getNumber(),
                       row.getNumber() + cell.getRowspan() - 1,
                       cell.getNumber(),
                       cell.getNumber() + cell.getColspan() - 1);
               sheet.addMergedRegion(matrixProcessing(offsetMatrix, cellRangeAddress));
               HSSFCell sheetCell = sheetRow.createCell(cellRangeAddress.getFirstColumn());
               sheetCell.setCellValue(cell.getValue());
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return workbook;
   }

   private static CellRangeAddress matrixProcessing(boolean[][] matrix, CellRangeAddress cellRangeAddress) {
      int temp = cellRangeAddress.getFirstColumn();
      while (temp <= cellRangeAddress.getLastColumn() && matrix[cellRangeAddress.getFirstRow()][temp]) {
         cellRangeAddress.setFirstColumn(cellRangeAddress.getFirstColumn() + 1);
         cellRangeAddress.setLastColumn(cellRangeAddress.getLastColumn() + 1);
         temp++;
      }
      for (int i = cellRangeAddress.getFirstRow(); i <= cellRangeAddress.getLastRow(); i++) {
         for (int j = cellRangeAddress.getFirstColumn(); j <= cellRangeAddress.getLastColumn(); j++) {
            matrix[i][j] = true;
         }
      }
      return cellRangeAddress;
   }
}
