package no.agricolas.scrumnotes.excel;


import no.agricolas.scrumnotes.domain.SubtaskNote;
import no.agricolas.scrumnotes.domain.SubtaskType;
import org.junit.Before;
import org.junit.Test;

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

        for (int i = 1; i < 10; i++) {
            SubtaskNote subtaskNoteUtvikling = buildSubtask(i, SubtaskType.DEVELOPMENT);
            SubtaskNote subtaskNoteTest = buildSubtask(i * 10, SubtaskType.TESTING);
            SubtaskNote subtaskNoteAnnet = buildSubtask(i * 20, SubtaskType.OTHER);
            subtaskNoteList.add(subtaskNoteUtvikling);
            subtaskNoteList.add(subtaskNoteTest);
            subtaskNoteList.add(subtaskNoteAnnet);
        }

        boolean generatedExcelFile = excelGenerator.createNotesFromSubtask(subtaskNoteList);

        assertThat(generatedExcelFile, is(true));
    }

    private SubtaskNote buildSubtask(int i, SubtaskType subtaskType) {
        SubtaskNote subtaskNote = new SubtaskNote("PKKU-" + i, "PKKU-" + i + "00",
                "PK-1234" + i + ": This is a subtak number" + i, i + ".5",
                subtaskType);

        return subtaskNote;
    }
}
