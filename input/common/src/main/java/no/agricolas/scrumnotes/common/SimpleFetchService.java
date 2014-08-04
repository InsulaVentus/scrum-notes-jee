package no.agricolas.scrumnotes.common;

import no.agricolas.scrumnotes.entities.SubtaskNote;

import java.util.List;

/**
 * Interface definition for methods to redirect calls to the appropiate fetching services whitin the input module
 *
 * @author Øyvind Strømmen
 */
public interface SimpleFetchService {

    /**
     * Retrieve the subtasks from a jira tasks
     *
     * @param url the url of the jira parent task
     * @return the retrieved subtasks as a List of {@link SubtaskNote}'s
     */
    public List<SubtaskNote> fetchFromJira(String url);

}
