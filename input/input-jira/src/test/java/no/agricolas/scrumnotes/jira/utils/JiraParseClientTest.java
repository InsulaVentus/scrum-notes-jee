package no.agricolas.scrumnotes.jira.utils;

import no.agricolas.scrumnotes.domain.SubtaskNote;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * @author Øyvind Strømmen
 */
public class JiraParseClientTest {

    private JiraParseClient parseClient = new JiraParseClient();

    @Test
    public void assertThatWhenEmptyResultObjectIsEmpty() {
        List<SubtaskNote> excpectedList = asList(
                new SubtaskNote(null, null, null, null, null)
        );
        assertThat(parseClient.getIssues(mockInputStream()), is(excpectedList));
    }

    private InputStream mockInputStream() {
        return null;
    }
}