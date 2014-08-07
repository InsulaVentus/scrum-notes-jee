package no.agricolas.scrumnotes.common;

import no.agricolas.scrumnotes.domain.SubtaskNote;
import no.agricolas.scrumnotes.jira.service.SimpleJiraService;
import no.agricolas.scrumnotes.jira.service.JiraService;

import java.util.List;

/**
 * Methods that redirect calls to the appropiate fetching services whitin the input module
 *
 * @author Øyvind Strømmen
 */
public class FetchService implements SimpleFetchService {

    private JiraService jiraService = new SimpleJiraService();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubtaskNote> fetchSubIssuesFromJira(String jiraId) {
        return jiraService.getSubIssues(jiraId);
    }
}
