import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import ru.golubenkov.htmlTable2xls.bean.Cell;
import ru.golubenkov.htmlTable2xls.bean.Row;
import ru.golubenkov.htmlTable2xls.bean.Table;
import ru.golubenkov.htmlTable2xls.bean.style.CellStyle;
import ru.golubenkov.htmlTable2xls.logic.HtmlTableParser;
import ru.golubenkov.htmlTable2xls.logic.XlsBuilder;

public class TransformTest {
   public static void main(String[] args) {

      String htmlTableInString = FileWorker.readFile("src/test/testTable.html", "Cp1251");

      Table table = HtmlTableParser.parseHtmlTable(htmlTableInString);
//      CellStyle cs = new CellStyle();
//      cs.setBorderWeight("BORDER_MEDIUM");
//      for (Row row : table.getHeader()) {
//         for (Cell cell : row.getCells()) {
//            cell.setCellStyle(cs);
//         }
//      }
//      for (Row row : table.getFooter()) {
//         for (Cell cell : row.getCells()) {
//            cell.setCellStyle(cs);
//         }
//      }
      HSSFWorkbook hssfWorkbook = XlsBuilder.buildXlsDocument(table);

      FileWorker.writeFile("src/test/resultTable.xls", hssfWorkbook);
   }
}
