package no.agricolas.scrumnotes.excel.utils;

import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;

/**
 * This class will generate a simple excelfile to a given location
 * @author Simen Søhol
 */
public class SimpleExcelGenerator {
    private static String PATH = "/Excel/";
    private static String FILENAME = "Note.xls";
    private static String SHEETNAME = "Sheet 1";
    public static void main(String [] args) {
        try {
            WritableWorkbook excelWorkbook = Workbook.createWorkbook(new File(FILENAME));
            WritableSheet sheet = excelWorkbook.createSheet(SHEETNAME, 0);

            Label label = new Label(0, 2, "A label record");
            sheet.addCell(label);

            Number number = new Number(3, 4, 3.1459);
            sheet.addCell(number);

            excelWorkbook.write();
            excelWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }
}
