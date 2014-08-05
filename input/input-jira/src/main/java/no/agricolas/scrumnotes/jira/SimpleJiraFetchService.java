package no.agricolas.scrumnotes.jira;

import no.agricolas.scrumnotes.domain.SubtaskNote;

import java.util.List;

/**
 * Methods exposing functionality in the jira module
 *
 * @author Øyvind Strømmen
 */
public interface SimpleJiraFetchService {

    /**
     * Fetch all subtasks from a task, given the url of the task
     *
     * @param url the url of the parent task
     * @return the subtasks as a List of {@link SubtaskNote}
     */
    public List<SubtaskNote> fetchSubtasks(String url);

}
