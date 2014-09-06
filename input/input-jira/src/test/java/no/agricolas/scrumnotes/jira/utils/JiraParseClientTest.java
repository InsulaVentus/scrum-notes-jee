package no.agricolas.scrumnotes.jira.utils;

import no.agricolas.scrumnotes.jira.utils.mock_entities.SimpleJiraEntity;
import org.junit.Ignore;
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

    @Ignore
    @Test
    public void assertThatWhenEmptyResultObjectIsEmpty() {
        List<SimpleJiraEntity> excpectedList = asList(
                new SimpleJiraEntity()
        );
        assertThat(parseClient.getIssues(mockInputStream(), SimpleJiraEntity.class), is(excpectedList));
    }

    private InputStream mockInputStream() {
        return null;
    }
}