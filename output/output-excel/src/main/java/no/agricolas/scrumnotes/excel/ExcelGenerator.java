package no.agricolas.scrumnotes.excel;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import no.agricolas.scrumnotes.domain.SubtaskNote;
import no.agricolas.scrumnotes.excel.utils.ExcelStyler;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static jxl.format.Colour.ORANGE;
import static no.agricolas.scrumnotes.domain.SubtaskType.ERROR;

/**
 * This class will generate a simple excelfile to a given location
 *
 * @author Simen Søhol
 */
public class ExcelGenerator {
    private static final String FILETYPE = ".xls";
    private static final String SHEETNAME = "Subtasks";
    private static final String QA = StringUtils.rightPad("QA", 25, ' ');
    private static final int A4_HEIGHT = 15;
    private static final int INCREASE_COLUMN = 1;
    private static final int HEIGHT_OF_NOTE = 5;

    private ExcelStyler excelStyler;

    public ExcelGenerator() {
        excelStyler = new ExcelStyler();
    }

    public boolean createNotesFromSubtask(List<SubtaskNote> subtaskList, String path) {
        try {
            WritableWorkbook excelWorkbook = Workbook.createWorkbook(new File(path + FILETYPE));
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

    /**
     * Adds the list of subtasks to the excel worksheet
     *
     * @param sheet       the worksheet to edit
     * @param subtaskList adds the subtaskList to the worksheet
     * @throws WriteException
     */
    private void generateNotesFromList(WritableSheet sheet, List<SubtaskNote> subtaskList) throws WriteException {
        int column = 0;
        int row = 0;

        Colour parentColor = excelStyler.getRandomParentColor();
        for (SubtaskNote subtask : subtaskList) {
            excelStyler.setNoteSize(sheet, row, column);

            if (subtask.getSubtaskType().equals(ERROR)) {
                generateNote(sheet, subtask, row, column, ORANGE);
            } else {
                generateNote(sheet, subtask, row, column, parentColor);
            }

            row += HEIGHT_OF_NOTE;
            if (row == A4_HEIGHT) {
                row = 0;
                column += INCREASE_COLUMN;
            }
        }
    }

    /**
     * Generates a single subtasknote on the given row and column in the excel writable sheet
     *
     * @param sheet       the writable sheet
     * @param subtask     the subtask element from jira
     * @param row         the subtasknote starts on the given row
     * @param column      the subtasknote starts on the given column
     * @param parentColor a random generated color
     * @throws WriteException
     */
    private void generateNote(WritableSheet sheet, SubtaskNote subtask, int row, int column, Colour parentColor) throws WriteException {
        Label header = new Label(column, row, subtask.getHeader());
        Label parent = new Label(column, row + 1, subtask.getSubtaskType().getLabel() + subtask.getParentTask());
        Label note = new Label(column, row + 2, subtask.getNote());
        Label etc = new Label(column, row + 3, QA + subtask.getEtc());

        excelStyler.setCellstyle(header, parent, note, etc);
        excelStyler.setParentColor(parentColor, header);
        excelStyler.setSubtaskTypeColor(subtask.getSubtaskType(), parent);

        sheet.addCell(header);
        sheet.addCell(parent);
        sheet.addCell(note);
        sheet.addCell(etc);
    }
}
