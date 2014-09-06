package no.agricolas.srumnotes.common;

import no.agricolas.scrumnotes.domain.SubtaskNote;

import java.util.List;

/**
 * @author Simen Søhol
 */
public interface GeneratorService {
    public boolean createNotesFromSubtask(List<SubtaskNote> subtaskList, String path);
}
