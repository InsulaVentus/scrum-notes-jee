package no.agricolas.scrumnotes.excel.utils;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import no.agricolas.scrumnotes.entities.SubtaskNote;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This class will generate a simple excelfile to a given location
 * @author Simen Søhol
 */
public class SimpleExcelGenerator {
    private static final String FILENAME = "Note.xls";
    private static final String SHEETNAME = "Sheet 1";
    private static final String QA = StringUtils.rightPad("QA", 40, ' ');
    private static final int A4_HEIGHT = 18;
    private static final int INCREASE_COLUMN = 2;
    private static final int COLUMN_WIDTH = 100*100;
    private static final int HEADER_HEIGHT = 40*20;
    private static final int PARENT_HEIGHT = 20*20;
    private static final int NOTE_HEIGHT = 90*20;
    private static final int ETC_HEIGHT = 20*20;
    private static final int SIZE_OF_NOTE = 6;

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
        int column = 0;
        int row = 0;

        for (SubtaskNote subtask : subtaskList) {
            setSheetStyle(sheet, row, column);
            generateNote(sheet, subtask, row, column);

            row += SIZE_OF_NOTE;
            if (row  == A4_HEIGHT) {
                row = 0;
                column += INCREASE_COLUMN;
            }
        }
    }

    private static void generateNote(WritableSheet sheet, SubtaskNote subtask, int row, int column) throws WriteException {
        Label head = new Label(column, row, subtask.getHeader(), setHeaderstyle());
        Label parent = new Label(column, row + 1, subtask.getParentTask(), setParentStyle());
        Label note = new Label(column, row + 2, subtask.getNote(), setNoteStyle());
        Label etc = new Label(column, row + 3, QA + subtask.getEtc(), setETCStyle());

        sheet.addCell(head);
        sheet.addCell(parent);
        sheet.addCell(note);
        sheet.addCell(etc);
    }

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

    private static void setSheetStyle(WritableSheet sheet, int row, int column) {
        CellView columnWidth = new CellView();
        columnWidth.setSize(COLUMN_WIDTH);
        sheet.setColumnView(column, columnWidth);

        try {
            sheet.setRowView(row, HEADER_HEIGHT);
            sheet.setRowView(row + 1, PARENT_HEIGHT);
            sheet.setRowView(row + 2, NOTE_HEIGHT);
            sheet.setRowView(row + 3, ETC_HEIGHT);

        } catch (RowsExceededException e) {
            e.printStackTrace();
        }
    }
}
