package ru.golubenkov.htmlTable2xls.logic;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import ru.golubenkov.htmlTable2xls.bean.Cell;
import ru.golubenkov.htmlTable2xls.bean.Row;
import ru.golubenkov.htmlTable2xls.bean.Table;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;

public class HtmlTableParser {

   private static final String ALTERNATIVE_VALUE = "alt-value"; //заменить на странице
   private static final String COLSPAN = "colspan";
   private static final String ROWSPAN = "rowspan";
   private static final String THEAD = "thead";
   private static final String TBODY = "tbody";
   private static final String TFOOT = "tfoot";

   public static Table parseHtmlTable(String htmlTableInString) {
      Table table = new Table();
      try {
         InputSource source = new InputSource(new StringReader(htmlTableInString));
         Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(source);
         XPath xPath = XPathFactory.newInstance().newXPath();

         NodeList trList = (NodeList) xPath.evaluate("//tr", document, XPathConstants.NODESET);
         for (int i = 0; i < trList.getLength(); i++) {
            Row row = new Row();
            row.setNumber(i);
            String sectionName = trList.item(i).getParentNode().getNodeName();
            if (sectionName.equalsIgnoreCase(THEAD)) {
               table.getHeader().add(row);
            } else if (sectionName.equalsIgnoreCase(TFOOT)) {
               table.getFooter().add(row);
            } else {
               table.getBody().add(row);
            }

            NodeList trChildList = trList.item(i).getChildNodes();
            for (int j = 0; j < trChildList.getLength(); j++) {
               Cell cell = new Cell();
               cell.setNumber(j);
               NamedNodeMap attrs = trChildList.item(j).getAttributes();
               Node attr;

               attr = attrs.getNamedItem(ALTERNATIVE_VALUE);
               if (attr != null) {
                  cell.setValue(attr.getTextContent());
               } else {
                  cell.setValue(trChildList.item(j).getTextContent());
               }

               attr = attrs.getNamedItem(COLSPAN);
               if (attr != null && !attr.getTextContent().isEmpty()) {
                  cell.setColspan(Integer.parseInt(attr.getTextContent()));
               } else {
                  cell.setColspan(1);
               }

               attr = attrs.getNamedItem(ROWSPAN);
               if (attr != null && !attr.getTextContent().isEmpty()) {
                  cell.setRowspan(Integer.parseInt(attr.getTextContent()));
               } else {
                  cell.setRowspan(1);
               }

               row.getCells().add(cell);
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      return table;
   }
}

