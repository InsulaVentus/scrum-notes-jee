package no.agricolas.scrumnotes.excel.utils;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.*;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.*;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;
import no.agricolas.scrumnotes.entities.SubtaskNote;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * This class will generate a simple excelfile to a given location
 * @author Simen Søhol
 */
public class SimpleExcelGenerator {
    private static final String PATH = "/Excel/";
    private static final String FILENAME = "Note.xls";
    private static final String SHEETNAME = "Sheet 1";
    private static final String QA = "QA                                         ";

    public boolean createNotesFromSubtask(List<SubtaskNote> subtaskList) {
        try {
            WritableWorkbook excelWorkbook = Workbook.createWorkbook(new File(FILENAME));
            WritableSheet sheet = excelWorkbook.createSheet(SHEETNAME, 0);

            generateNotesFromList(sheet, subtaskList);

            excelWorkbook.write();
            excelWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void generateNotesFromList(WritableSheet sheet, List<SubtaskNote> subtaskList) throws WriteException {
        setSheetStyle(sheet);
        for (SubtaskNote subtask : subtaskList) {

            generateNote(sheet, subtask);
        }
    }

    private static void generateNote(WritableSheet sheet, SubtaskNote subtask) throws WriteException {
        Label head = new Label(0, 0, subtask.getHeader(), setHeaderstyle());
        Label parent = new Label(0, 1, subtask.getParentTask(), setParentStyle());
        Label note = new Label(0, 2, subtask.getNote(), setNoteStyle());
        Label etc = new Label(0, 3, QA + subtask.getEtc(), setETCStyle());

        sheet.addCell(head);
        sheet.addCell(parent);
        sheet.addCell(note);
        sheet.addCell(etc);
    }

    /**
     * Sets the head fontstyle
     * @return fontstyle
     */
    private static WritableCellFormat setHeaderstyle() {
        return defaultCellStyleWithThinBorder(18);
    }

    private static WritableCellFormat setParentStyle() {
        return defaultCellStyleWithThinBorder(14);
    }

    private static WritableCellFormat setNoteStyle() {
        return defaultCellStyleWithThinBorder(12);
    }

    private static WritableCellFormat setETCStyle() {
        return defaultCellStyleWithThinBorder(14);
    }

    private static WritableCellFormat setQAStyle() {
        return defaultCellStyleWithThinBorder(14);
    }

    private static WritableCellFormat defaultCellStyleWithThinBorder(int textSize)  {
        WritableCellFormat format = new WritableCellFormat();

        try {
            format.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
            format.setFont(new WritableFont(WritableFont.ARIAL, textSize));

        } catch (WriteException e) {
            e.printStackTrace();
        }

        return format;
    }

    private static void setSheetStyle(WritableSheet sheet) {
        CellView columnWith = new CellView();
        columnWith.setSize(100*100);
        sheet.setColumnView(0, columnWith);

        try {
            sheet.setRowView(0, 40*20);
            sheet.setRowView(1, 20*20);
            sheet.setRowView(2, 90*20);
            sheet.setRowView(3, 20*20);

        } catch (RowsExceededException e) {
            e.printStackTrace();
        }

    }
}
