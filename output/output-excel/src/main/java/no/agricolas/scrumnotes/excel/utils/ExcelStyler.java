package no.agricolas.scrumnotes.excel.utils;

import jxl.CellView;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

import java.util.Arrays;
import java.util.List;


/**
 * This class styles the excelsheet that is generated
 *
 * @author Simen Søhol
 */
public class ExcelStyler {
    private static final int COLUMN_WIDTH = 90 * 90;
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

    /**
     * Sets a given color on a given lable
     *
     * @param subtaskColor the color to set
     * @param label        the label to set the color on
     * @throws WriteException
     */
    public void setSubtaskColor(Colour subtaskColor, Label label) throws WriteException {
        changeTypeColor(label, subtaskColor);
    }

    /**
     * Sets a background color on the writableCellformat to the given label.
     *
     * @param label the label to set the color on
     * @param color the color to set
     * @throws WriteException
     */
    private void changeTypeColor(Label label, Colour color) throws WriteException {
        WritableCellFormat format = (WritableCellFormat) label.getCellFormat();
        format.setBackground(color);

        label.setCellFormat(format);
    }

    /**
     * Returns a random color from the the list
     *
     * @return random backgroundcolor
     */
    public Colour getRandomParentColor() {
        List<Colour> colorList = Arrays.asList(Colour.getAllColours());

        int randomColor = (int) (Math.random() * colorList.size()) + 1;

        return colorList.get(randomColor);
    }

    /**
     * Sets different styles on each cells in the excelsheet
     *
     * @param labels the labels to style
     */
    public void setCellstyle(Label... labels) throws WriteException {
        labels[0].setCellFormat(defaultCellStyleWithBorder(25));
        labels[1].setCellFormat(defaultCellStyleWithBorder(15));
        labels[2].setCellFormat(defaultCellStyleWithBorder(14));
        labels[3].setCellFormat(defaultCellStyleWithBorder(14));
    }

    /**
     * Sets a default style on each column
     *
     * @param textSize the textsize to use
     * @return the cellstyle
     */
    private WritableCellFormat defaultCellStyleWithBorder(int textSize) throws WriteException {
        WritableCellFormat format = new WritableCellFormat();
        format.setBorder(Border.ALL, BorderLineStyle.THICK);
        format.setFont(new WritableFont(WritableFont.ARIAL, textSize));
        format.setWrap(true);
        format.setAlignment(Alignment.CENTRE);

        return format;
    }
}
