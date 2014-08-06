package no.agricolas.scrumnotes.jira.service;

import no.agricolas.scrumnotes.domain.SubtaskNote;
import no.agricolas.scrumnotes.jira.utils.JiraRestClient;
import no.agricolas.scrumnotes.jira.utils.JqlOperation;
import no.agricolas.scrumnotes.jira.utils.JsonParseClient;

import java.io.InputStream;
import java.util.List;

/**
 * Jira service
 *
 * @author Øyvind Strømmen
 */
public class DefaultJiraService implements SimpleJiraService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubtaskNote> getSubIssues(String jiraId) {
        InputStream stream = JiraRestClient.executeJqlQuery(jiraId, JqlOperation.GET_SUB_ISSUES);
        return JsonParseClient.getIssues(stream);
    }
}
