package no.agricolas.scrumnotes.common;

import no.agricolas.scrumnotes.domain.SubtaskNote;
import no.agricolas.scrumnotes.jira.service.DefaultJiraService;
import no.agricolas.scrumnotes.jira.service.SimpleJiraService;

import java.util.List;

/**
 * Methods that redirect calls to the appropiate fetching services whitin the input module
 *
 * @author Øyvind Strømmen
 */
public class FetchService implements SimpleFetchService {

    private SimpleJiraService jiraService = new DefaultJiraService();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubtaskNote> fetchSubIssuesFromJira(String jiraId) {
        return jiraService.getSubIssues(jiraId);
    }
}
