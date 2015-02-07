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
        List<SubtaskNote> subtaskNoteList = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            SubtaskNote subtaskNoteUtvikling = buildSubtask(i, SubtaskType.DEVELOPMENT);
            SubtaskNote subtaskNoteTest = buildSubtask(i * 13, SubtaskType.TESTING);
            SubtaskNote subtaskNoteAnnet = buildSubtask(i * 17, SubtaskType.OTHER);
            SubtaskNote subtaskNoteSysDoc = buildSubtask(i * 22, SubtaskType.DOCUMENTATION);
            SubtaskNote subtaskNoteError = buildSubtask(i * 23, SubtaskType.ERROR);
            subtaskNoteList.add(subtaskNoteUtvikling);
            subtaskNoteList.add(subtaskNoteTest);
            subtaskNoteList.add(subtaskNoteAnnet);
            subtaskNoteList.add(subtaskNoteSysDoc);
            subtaskNoteList.add(subtaskNoteError);
        }

        boolean generatedExcelFile = excelGenerator.createNotesFromSubtask(subtaskNoteList, "Note");

        assertThat(generatedExcelFile, is(true));
    }

    private SubtaskNote buildSubtask(int i, SubtaskType subtaskType) {
        return new SubtaskNote("PKKOALA-" + i * 1000, "PKKU-" + i + "00",
                "PK-1234" + i + ": This is subtak number " + i, i + ".5",
                subtaskType);
    }
}
