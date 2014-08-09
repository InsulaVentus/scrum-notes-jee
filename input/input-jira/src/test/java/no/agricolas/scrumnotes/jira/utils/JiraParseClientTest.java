package no.agricolas.scrumnotes.jira.utils;

import org.junit.Test;

import java.io.InputStream;


/**
 * @author Øyvind Strømmen
 */
public class JiraParseClientTest {

    private JiraParseClient parseClient = new JiraParseClient();

    @Test
    public void assertThatWhenEmptyResultObjectIsEmpty() {
       /** List<SubtaskNote> excpectedList = asList(
                new SubtaskNote(null, null, null, null, null)
        );
        assertThat(parseClient.getIssues(mockInputStream()), is(excpectedList));
        **/
    }

    private InputStream mockInputStream() {
        return null;
    }
}