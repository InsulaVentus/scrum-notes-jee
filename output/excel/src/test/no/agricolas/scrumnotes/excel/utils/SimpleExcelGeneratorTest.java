package no.agricolas.scrumnotes.excel.utils;

import no.agricolas.scrumnotes.entities.SubtaskNote;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Simen Søhol
 */
public class SimpleExcelGeneratorTest {
    private SimpleExcelGenerator simpleExcelGenerator;

    @Before
    public void setUp() {
        simpleExcelGenerator = new SimpleExcelGenerator();
    }

    @Test
    public void generateSingleNoteFromList() {
        List<SubtaskNote> subtaskNoteList = new ArrayList<SubtaskNote>();
        SubtaskNote subtaskNote;
        for (int i = 1; i < 20;  i++) {
            subtaskNote = new SubtaskNote("PKKU-" + i, "PK-" + i + "00", "Dette er subtask nummer " + i, i + ".5");
            subtaskNoteList.add(subtaskNote);
        }

        boolean generatedExcelFile = simpleExcelGenerator.createNotesFromSubtask(subtaskNoteList);

        assertThat(generatedExcelFile, is(true));
    }
}
