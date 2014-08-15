package no.agricolas.scrumnotes.generatenote;

import no.agricolas.scrumnotes.domain.SubtaskNote;
import no.agricolas.scrumnotes.domain.SubtaskType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simen Søhol
 */
public class ScrumNotesStub {

    public ScrumNotesStub() {

    }

    public List<SubtaskNote> getSubtasks(String parent) {
        List<SubtaskNote> subtaskNoteList = new ArrayList<SubtaskNote>();

        for (int i = 1; i < 10; i++) {
            SubtaskNote subtaskNoteUtvikling = buildSubtask(i, SubtaskType.DEVELOPMENT, parent);
            SubtaskNote subtaskNoteTest = buildSubtask(i * 10, SubtaskType.TESTING, parent);
            SubtaskNote subtaskNoteAnnet = buildSubtask(i * 20, SubtaskType.OTHER, parent);
            subtaskNoteList.add(subtaskNoteUtvikling);
            subtaskNoteList.add(subtaskNoteTest);
            subtaskNoteList.add(subtaskNoteAnnet);
        }

        return subtaskNoteList;
    }

    private SubtaskNote buildSubtask(int i, SubtaskType subtaskType, String parent) {
        SubtaskNote subtaskNote = new SubtaskNote(parent + "-" + i, parent + i + "00",
                "PK-1234" + i + ": This is a subtak number" + i, i + ".5",
                subtaskType);

        return subtaskNote;
    }
}
