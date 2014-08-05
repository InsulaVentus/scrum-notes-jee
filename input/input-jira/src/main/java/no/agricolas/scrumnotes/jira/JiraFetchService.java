package no.agricolas.scrumnotes.jira;

import no.agricolas.scrumnotes.domain.SubtaskNote;
import org.apache.http.Header;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;

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

        String parentTask = "PKDRAGE-1334";

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(String.format("http://nav.adeo.jira.no/rest/api/2/search?jql=parent=%s", parentTask));

        BasicScheme scheme = new BasicScheme();
        Header header = null;
        try {
            header = scheme.authenticate(new UsernamePasswordCredentials("user", "password"), request, new BasicHttpContext());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        request.addHeader(header);


        return null;
    }
}
