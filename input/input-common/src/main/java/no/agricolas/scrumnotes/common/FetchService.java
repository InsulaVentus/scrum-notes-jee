package no.agricolas.scrumnotes.common;

import no.agricolas.scrumnotes.domain.SubtaskNote;
import no.agricolas.scrumnotes.jira.JiraFetchService;
import no.agricolas.scrumnotes.jira.SimpleJiraFetchService;

import java.util.List;

/**
 * Methods that redirect calls to the appropiate fetching services whitin the input module
 *
 * @author Øyvind Strømmen
 */
public class FetchService implements SimpleFetchService {

    private SimpleJiraFetchService jiraFetchService = new JiraFetchService();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubtaskNote> fetchFromJira(String url) {
        return jiraFetchService.fetchSubtasks(url);
    }
}
