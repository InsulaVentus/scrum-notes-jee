package no.agricolas.scrumnotes.jira.utils;

import com.atlassian.jira.rest.client.IssueRestClient;
import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.JiraRestClientFactory;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Øyvind Strømmen
 */
public class JiraTaskCollector {

    JiraRestClientFactory clientFactory = new AsynchronousJiraRestClientFactory();

    public void collectSubtasks(String url) throws URISyntaxException {


        JiraRestClient client = clientFactory.createWithBasicHttpAuthentication(new URI(""), "", "");

        IssueRestClient issueClient = client.getIssueClient();

        Issue issue = (Issue) issueClient.getIssue("PKDRAGE-2151");

//        if (issue != null) {
//            for (Subtask subtask : issue.getSubtasks()) {
//                subtask.
//            }
//        }

    }

}
