package no.agricolas.scrumnotes.jira.service;

import no.agricolas.scrumnotes.domain.SubtaskNote;
import no.agricolas.scrumnotes.jira.utils.FieldReflectionClient;
import no.agricolas.scrumnotes.jira.utils.JiraParseClient;
import no.agricolas.scrumnotes.jira.utils.JiraRestClient;
import no.agricolas.scrumnotes.jira.utils.JqlOperation;

import java.io.InputStream;
import java.util.List;

/**
 * Jira service
 *
 * @author Øyvind Strømmen
 */
public class SimpleJiraService implements JiraService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubtaskNote> getSubIssues(String jiraId) {
        InputStream stream = JiraRestClient.executeJqlQuery(jiraId, JqlOperation.GET_SUB_ISSUES);
        JiraParseClient parseClient = new JiraParseClient();
        parseClient.setReflectionClient(new FieldReflectionClient());
        return parseClient.getIssues(stream, SubtaskNote.class);
    }
}
