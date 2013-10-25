package ru.golubenkov.htmlTable2xls.bean;

import java.util.ArrayList;

public class Table {

   private ArrayList<Row> header = new ArrayList<Row>();
   private ArrayList<Row> body = new ArrayList<Row>();
   private ArrayList<Row> footer = new ArrayList<Row>();

   public ArrayList<Row> getHeader() {
      return header;
   }

   public void setHeader(ArrayList<Row> header) {
      this.header = header;
   }

   public ArrayList<Row> getBody() {
      return body;
   }

   public void setBody(ArrayList<Row> body) {
      this.body = body;
   }

   public ArrayList<Row> getFooter() {
      return footer;
   }

   public void setFooter(ArrayList<Row> footer) {
      this.footer = footer;
   }

   public ArrayList<Row> getAllRows() {
      ArrayList<Row> allRows = new ArrayList<Row>();
      allRows.addAll(getHeader());
      allRows.addAll(getBody());
      allRows.addAll(getFooter());
      return allRows;
   }

   public int getNumberOfColumns() {
      Row firstRow = getHeader().get(0);
      int numberOfColumns = 0;
      if (firstRow != null) {
         for (Cell cell : firstRow.getCells()) {
            numberOfColumns = numberOfColumns + cell.getColspan();
         }
      }
      return numberOfColumns;
   }

}
