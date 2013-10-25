import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class FileWorker {

   public static String readFile(String filePathName, String encoding) {
      StringBuilder sb = new StringBuilder();
      BufferedReader br = null;
      try {

         String sCurrentLine;
         br = new BufferedReader(new InputStreamReader(new FileInputStream(filePathName), encoding));

         while ((sCurrentLine = br.readLine()) != null) {
            sb.append(sCurrentLine);
         }

      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            if (br != null) br.close();
         } catch (IOException ex) {
            ex.printStackTrace();
         }
         return sb.toString();
      }
   }

   public static void writeFile(String filePathName, HSSFWorkbook hssfWorkbook) {
      OutputStream fileOutputStream = null;
      try {
         File file = new File(filePathName);
         if (!file.exists()) {
            file.createNewFile();
         }

         fileOutputStream = new FileOutputStream(file);
         hssfWorkbook.write(fileOutputStream);

      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            if (fileOutputStream != null) fileOutputStream.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
}
