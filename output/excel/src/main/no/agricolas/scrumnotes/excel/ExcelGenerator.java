package no.agricolas.scrumnotes.excel;

import jxl.Workbook;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import no.agricolas.scrumnotes.entities.SubtaskNote;
import no.agricolas.scrumnotes.excel.utils.ExcelStyler;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This class will generate a simple excelfile to a given location
 * @author Simen Søhol
 */
public class ExcelGenerator {
    private static final String FILENAME = "Note.xls";
    private static final String SHEETNAME = "Sheet 1";
    private static final String QA = StringUtils.rightPad("QA", 40, ' ');
    private static final int A4_HEIGHT = 18;
    private static final int INCREASE_COLUMN = 2;
    private static final int HEIGHT_OF_NOTE = 6;

    private ExcelStyler excelStyler;

    public ExcelGenerator() {
        excelStyler = new ExcelStyler();
    }

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
            excelStyler.setNoteSize(sheet, row, column);

            generateNote(sheet, subtask, row, column);

            row += HEIGHT_OF_NOTE;
            if (row  == A4_HEIGHT) {
                row = 0;
                column += INCREASE_COLUMN;
            }
        }
    }

    private void generateNote(WritableSheet sheet, SubtaskNote subtask, int row, int column) throws WriteException {
        Label head = new Label(column, row, subtask.getHeader());
        Label parent = new Label(column, row + 1, subtask.getParentTask());
        Label note = new Label(column, row + 2, subtask.getNote());
        Label etc = new Label(column, row + 3, QA + subtask.getEtc());

        excelStyler.setCellstyle(head, parent, note, etc);

        sheet.addCell(head);
        sheet.addCell(parent);
        sheet.addCell(note);
        sheet.addCell(etc);
    }
}
