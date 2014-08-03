package no.agricolas.scrumnotes.excel;

import no.agricolas.scrumnotes.entities.SubtaskNote;
import org.junit.Before;
import org.junit.Test;
import utils.SubtaskType;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Simen Søhol
 */
public class ExcelGeneratorTest {
    private ExcelGenerator excelGenerator;

    @Before
    public void setUp() {
        excelGenerator = new ExcelGenerator();
    }

    @Test
    public void generateSingleNoteFromList() {
        List<SubtaskNote> subtaskNoteList = new ArrayList<SubtaskNote>();
        SubtaskNote subtaskNote;
        for (int i = 1; i < 20;  i++) {
            subtaskNote = new SubtaskNote("PKKU-" + i, "PK-" + i + "00", "This is subtaks " + i, i + ".5",
                    SubtaskType.UTVIKLING);
            subtaskNoteList.add(subtaskNote);
        }

        boolean generatedExcelFile = excelGenerator.createNotesFromSubtask(subtaskNoteList);

        assertThat(generatedExcelFile, is(true));
    }
}
