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
            SubtaskNote subtaskNoteUtvikling = buildSubtask(i, SubtaskType.UTVIKLING);
            SubtaskNote subtaskNoteTest = buildSubtask(i * 10, SubtaskType.TEST);
            SubtaskNote subtaskNoteAnnet = buildSubtask(i * 20, SubtaskType.ANNET);
            subtaskNoteList.add(subtaskNoteUtvikling);
            subtaskNoteList.add(subtaskNoteTest);
            subtaskNoteList.add(subtaskNoteAnnet);
        }

        boolean generatedExcelFile = excelGenerator.createNotesFromSubtask(subtaskNoteList);

        assertThat(generatedExcelFile, is(true));
    }

    private SubtaskNote buildSubtask(int i, SubtaskType subtaskType) {
        SubtaskNote subtaskNote = new SubtaskNote();
        subtaskNote.setHeader("PKKU-" + i);
        subtaskNote.setParentTask("PK-" + i + "00");
        subtaskNote.setNote("This is subtaks " + i);
        subtaskNote.setEtc(i + ".5");
        subtaskNote.setSubtaskType(subtaskType);

        return subtaskNote;
    }
}
