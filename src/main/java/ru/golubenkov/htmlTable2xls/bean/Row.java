package ru.golubenkov.htmlTable2xls.bean;

import java.util.ArrayList;

public class Row {

   private int number;
   private ArrayList<Cell> cells = new ArrayList<Cell>();

   public int getNumber() {
      return number;
   }

   public void setNumber(int number) {
      this.number = number;
   }

   public ArrayList<Cell> getCells() {
      return cells;
   }

   public void setCells(ArrayList<Cell> cells) {
      this.cells = cells;
   }

}
