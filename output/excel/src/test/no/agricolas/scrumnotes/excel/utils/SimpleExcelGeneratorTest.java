package no.agricolas.scrumnotes.excel.utils;

import no.agricolas.scrumnotes.entities.SubtaskNote;
import org.junit.Before;
import org.junit.Test;


import java.util.Arrays;

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
        SubtaskNote subtaskNote = new SubtaskNote("PKKU-123", "PKKU-1", "Dette er en subtask", "5.5");

        boolean generatedExcelFile = simpleExcelGenerator.createNotesFromSubtask(Arrays.asList(subtaskNote));

        assertThat(generatedExcelFile, is(true));
    }
}
