package no.agricolas.scrumnotes.jira.utils;

import no.agricolas.scrumnotes.jira.utils.mock_entities.SimpleJiraEntity;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * @author Øyvind Strømmen
 */
public class JiraParseClientTest {

    private JiraParseClient parseClient = new JiraParseClient();

    private static final String ACTUAL = "/actual-result.json";
    private static final String IDEAL = "/ideal-result.json";
    private static final String MAX_ONE = "/max-one-result.json";
    private static final String NO_RESULT = "/no-result.json";

    @Ignore
    @Test
    public void assertThatWhenEmptyResultObjectIsEmpty() throws Exception {
        mockReflectionClient(createSearchTree());
        List<SimpleJiraEntity> excpectedList = asList(
                new SimpleJiraEntity()
        );
        assertThat(parseClient.getIssues(createInputStream(ACTUAL), SimpleJiraEntity.class), is(excpectedList));
    }

    private InputStream createInputStream(String fileName) throws FileNotFoundException {
        return getClass().getResourceAsStream(fileName);
    }

    private void mockReflectionClient(TreeNode<String> searchTree) {
        FieldReflectionClient fieldReflectionClient = mock(FieldReflectionClient.class);
        when(fieldReflectionClient.extractFields(SimpleJiraEntity.class)).thenReturn(searchTree);
    }

    private TreeNode<String> createSearchTree() {
        return null;
    }
}