package no.agricolas.scrumnotes.jira;

import no.agricolas.scrumnotes.domain.SubtaskNote;

import java.util.List;

/**
 * Fetch jira tasks from given sources
 *
 * @author Øyvind Strømmen
 */
public class JiraFetchService implements SimpleJiraFetchService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubtaskNote> fetchSubtasks(String url) {
        return null;
    }
}
