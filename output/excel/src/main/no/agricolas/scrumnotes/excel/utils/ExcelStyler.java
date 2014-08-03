package no.agricolas.scrumnotes.excel.utils;

import jxl.CellView;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

/**
 * This class styles the excelsheet that is generated
 *
 * @author Simen Søhol
 */
public class ExcelStyler {
    private static final int COLUMN_WIDTH = 100*100;
    private static final int HEADER_HEIGHT = 40*20;
    private static final int PARENT_HEIGHT = 20*20;
    private static final int NOTE_HEIGHT = 90*20;
    private static final int ETC_HEIGHT = 20*20;


    /**
     * Sets the size on each note
     *
     * @param sheet the sheet to edit
     * @param row the row edit
     * @param column the column to edit
     */
    public void setNoteSize(WritableSheet sheet, int row, int column) {
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

    /**
     * Sets different styles on each cells in the excelsheet
     *
     * @param labels the labels to style
     */
    public void setCellstyle(Label... labels) {
        labels[0].setCellFormat(setHeaderstyle());
        labels[1].setCellFormat(setParentStyle());
        labels[2].setCellFormat(setNoteStyle());
        labels[3].setCellFormat(setETCStyle());
    }

    private WritableCellFormat setHeaderstyle() {
        return defaultCellStyleWithThinBorder(18);
    }

    private WritableCellFormat setParentStyle() {
        return defaultCellStyleWithThinBorder(14);
    }

    private WritableCellFormat setNoteStyle() {
        return defaultCellStyleWithThinBorder(12);
    }

    private WritableCellFormat setETCStyle() {
        return defaultCellStyleWithThinBorder(14);
    }

    /**
     * Sets a default style on each column
     *
     * @param textSize the textsize to use
     * @return the cellstyle
     */
    private WritableCellFormat defaultCellStyleWithThinBorder(int textSize)  {
        WritableCellFormat format = new WritableCellFormat();

        try {
            format.setBorder(Border.ALL, BorderLineStyle.MEDIUM);
            format.setFont(new WritableFont(WritableFont.ARIAL, textSize));
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }
}
