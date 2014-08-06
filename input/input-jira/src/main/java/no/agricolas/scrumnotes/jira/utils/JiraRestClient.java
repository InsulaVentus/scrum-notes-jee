package no.agricolas.scrumnotes.jira.utils;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Øyvind Strømmen
 */
public abstract class JiraRestClient {

    private static final String JIRA_DOMAIN = "http://nav.adeo.jira.no/";

    private static final String REST_API = "rest/api/2/";

    public static InputStream executeJqlQuery(String jiraId, JqlOperation operation) {

        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet request = new HttpGet(String.format("%s%s%s%s", JIRA_DOMAIN, REST_API, operation.getJqlQuery(), jiraId));

        BasicScheme scheme = new BasicScheme();
        Header header = null;

        try {
            header = scheme.authenticate(new UsernamePasswordCredentials("user", "password"), request, new BasicHttpContext()); //TODO: Determine if BasicHttpContext is the best option
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        request.addHeader(header);

        InputStream inputStream = null;
        try {
            HttpResponse response = httpClient.execute(request);
            inputStream = response.getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputStream;
    }

}
