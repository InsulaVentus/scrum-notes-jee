package no.agricolas.scrumnotes.excel.utils;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import no.agricolas.scrumnotes.domain.SubtaskType;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Simen Søhol
 */
public class ExcelStylerTest {
    private static final int ROW = 0;
    private static final int COLUMN = 0;
    private static final String HEADERNAME = "Header";
    private static final String PARENTNAME = "Parent";
    private static final String NOTENAME = "Note";
    private static final String ETCNAME = "5.5";
    private static final String FONTSTYLE = "Arial";
    private static final String FILENAME = "Note.xls";
    private static final int COLUMN_WIDTH = 90 * 90;
    private static final int HEADER_HEIGHT = 40 * 20;
    private static final int PARENT_HEIGHT = 20 * 20;
    private static final int NOTE_HEIGHT = 90 * 20;
    private static final int ETC_HEIGHT = 20 * 20;

    private ExcelStyler excelStyler;


    @Before
    public void setUp() {
        excelStyler = new ExcelStyler();
    }

    @Test
    public void setSubtaksColor() throws WriteException {
        Label header = new Label(COLUMN, ROW, HEADERNAME);
        Label parent = new Label(COLUMN, ROW, PARENTNAME);
        Label note = new Label(COLUMN, ROW, NOTENAME);
        Label etc = new Label(COLUMN, ROW, ETCNAME);

        excelStyler.setCellstyle(header, parent, note, etc);

        assertLabel(header, parent, note, etc);
    }

    @Test
    public void setSubtaskColor() throws WriteException {
        Label label = new Label(COLUMN, ROW, HEADERNAME);

        excelStyler.setSubtaskColor(SubtaskType.DEVELOPMENT.getColour(), label);

        assertThat(label.getCellFormat().getBackgroundColour(), is(Colour.LIGHT_TURQUOISE));
    }

    @Test
    public void setNoteStyle() throws Exception {
        WritableWorkbook excelWorkbook = Workbook.createWorkbook(new File(FILENAME));
        WritableSheet sheet = excelWorkbook.createSheet(FILENAME, 0);

        excelStyler.setNoteSize(sheet, COLUMN, ROW);

        assertThat(sheet.getColumnView(COLUMN).getSize(), is(COLUMN_WIDTH));
        assertThat(sheet.getRowView(ROW).getSize(), is(HEADER_HEIGHT));
        assertThat(sheet.getRowView(ROW + 1).getSize(), is(PARENT_HEIGHT));
        assertThat(sheet.getRowView(ROW + 2).getSize(), is(NOTE_HEIGHT));
        assertThat(sheet.getRowView(ROW + 3).getSize(), is(ETC_HEIGHT));
    }

    @Test
    public void getRandomColor() {
        Colour colour = excelStyler.getRandomParentColor();
        List<Colour> colours = Arrays.asList(Colour.getAllColours());

        assertThat(colours.contains(colour), is(true));
    }

    /**
     * Asserts the fontstyle, textsize,  allignment and if the text is wraped
     *
     * @param labels the labels to check
     */
    private void assertLabel(Label... labels) {
        for (Label label : labels) {
            assertThat(label.getCellFormat().getFont().getPointSize() >= 14, is(true));
            assertThat(label.getCellFormat().getFont().getName(), is(FONTSTYLE));
            assertThat(label.getCellFormat().getAlignment(), is(Alignment.CENTRE));
            assertThat(label.getCellFormat().getWrap(), is(true));
        }
    }
}
