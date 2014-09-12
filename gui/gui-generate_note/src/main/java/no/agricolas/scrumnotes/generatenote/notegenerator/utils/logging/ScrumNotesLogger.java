package no.agricolas.scrumnotes.generatenote.notegenerator.utils.logging;

import no.agricolas.scrumnotes.domain.SubtaskNote;
import no.agricolas.scrumnotes.domain.SubtaskType;

import java.util.Arrays;
import java.util.List;

import static no.agricolas.scrumnotes.domain.SubtaskType.DEVELOPMENT;
import static no.agricolas.scrumnotes.domain.SubtaskType.TESTING;

/**
 * @author Simen Søhol
 */
public class ScrumNotesLogger {
    private static final String LOG_TOTAL_DEVELOPMENT = "Total generated developmenttasks: %s";
    private static final String LOG_TOTAL_TEST = "Total generated testtasks: %s";
    private static final String LOG_TOTAL_OTHER = "Total unspecified tasks generated: %s";

    public List<String> getScrumnotesStats(List<SubtaskNote> subtaskNotes) {
        int developmentTasks = 0;
        int testingTasks = 0;
        int otherTaskes = 0;

        for (SubtaskNote note : subtaskNotes) {
            if (note.getSubtaskType().equals(DEVELOPMENT)) {
                developmentTasks++;
            } else if (note.getSubtaskType().equals(TESTING)) {
                testingTasks++;
            } else {
                otherTaskes++;
            }
        }

        return Arrays.asList(String.format(LOG_TOTAL_DEVELOPMENT, developmentTasks),
                String.format(LOG_TOTAL_TEST, testingTasks),
                String.format(LOG_TOTAL_OTHER, otherTaskes));
    }
}
