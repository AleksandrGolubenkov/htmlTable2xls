package ru.golubenkov.htmlTable2xls.bean;

import ru.golubenkov.htmlTable2xls.bean.style.CellStyle;

public class Cell {

   private int number;
   private int colspan;
   private int rowspan;
   private String value;
   private CellType type;
   private CellStyle cellStyle;


   public int getNumber() {
      return number;
   }

   public String getValue() {
      return value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public void setNumber(int number) {
      this.number = number;
   }

   public int getColspan() {
      return colspan;
   }

   public void setColspan(int colspan) {
      this.colspan = colspan;
   }

   public int getRowspan() {
      return rowspan;
   }

   public void setRowspan(int rowspan) {
      this.rowspan = rowspan;
   }

   public CellType getType() {
      return type;
   }

   public void setType(CellType type) {
      this.type = type;
   }

   public CellStyle getCellStyle() {
      return cellStyle;
   }

   public void setCellStyle(CellStyle cellStyle) {
      this.cellStyle = cellStyle;
   }
}
