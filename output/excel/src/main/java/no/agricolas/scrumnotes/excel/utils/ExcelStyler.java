package no.agricolas.scrumnotes.excel.utils;

import jxl.CellView;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import no.agricolas.scrumnotes.domain.SubtaskType;

/**
 * This class styles the excelsheet that is generated
 *
 * @author Simen Søhol
 */
public class ExcelStyler {
    private static final int COLUMN_WIDTH = 100 * 100;
    private static final int HEADER_HEIGHT = 40 * 20;
    private static final int PARENT_HEIGHT = 20 * 20;
    private static final int NOTE_HEIGHT = 90 * 20;
    private static final int ETC_HEIGHT = 20 * 20;

    /**
     * Sets the size on each note
     *
     * @param sheet  the sheet to edit
     * @param row    the row edit
     * @param column the column to edit
     */
    public void setNoteSize(WritableSheet sheet, int row, int column) throws RowsExceededException {
        CellView columnWidth = new CellView();
        columnWidth.setSize(COLUMN_WIDTH);
        sheet.setColumnView(column, columnWidth);

        sheet.setRowView(row, HEADER_HEIGHT);
        sheet.setRowView(row + 1, PARENT_HEIGHT);
        sheet.setRowView(row + 2, NOTE_HEIGHT);
        sheet.setRowView(row + 3, ETC_HEIGHT);


    }

    public void setTaskTypeColor(SubtaskType subtaskType, Label header) throws WriteException {
        if (subtaskType == SubtaskType.UTVIKLING) {
            changeTypeColor(header, Colour.LIGHT_BLUE);
        } else if (subtaskType == SubtaskType.TEST) {
            changeTypeColor(header, Colour.LIGHT_GREEN);
        } else {
            changeTypeColor(header, Colour.LIGHT_ORANGE);
        }
    }

    private void changeTypeColor(Label label, Colour colour) throws WriteException {
        WritableCellFormat format = (WritableCellFormat) label.getCellFormat();
        format.setBackground(colour);

        label.setCellFormat(format);
    }

    /**
     * Sets different styles on each cells in the excelsheet
     *
     * @param labels the labels to style
     */
    public void setCellstyle(Label... labels) throws WriteException {
        labels[0].setCellFormat(setHeaderstyle());
        labels[1].setCellFormat(setParentStyle());
        labels[2].setCellFormat(setNoteStyle());
        labels[3].setCellFormat(setETCStyle());
    }

    private WritableCellFormat setHeaderstyle() throws WriteException {
        return defaultCellStyleWithThinBorder(18);
    }

    private WritableCellFormat setParentStyle() throws WriteException {
        return defaultCellStyleWithThinBorder(14);
    }

    private WritableCellFormat setNoteStyle() throws WriteException {
        return defaultCellStyleWithThinBorder(12);
    }

    private WritableCellFormat setETCStyle() throws WriteException {
        return defaultCellStyleWithThinBorder(14);
    }

    /**
     * Sets a default style on each column
     *
     * @param textSize the textsize to use
     * @return the cellstyle
     */
    private WritableCellFormat defaultCellStyleWithThinBorder(int textSize) throws WriteException {
        WritableCellFormat format = new WritableCellFormat();
        format.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
        format.setFont(new WritableFont(WritableFont.ARIAL, textSize));

        return format;
    }
}
