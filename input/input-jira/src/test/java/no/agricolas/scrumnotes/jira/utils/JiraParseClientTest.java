package no.agricolas.scrumnotes.jira.utils;

import no.agricolas.scrumnotes.jira.utils.mock_entities.SimpleJiraEntity;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * @author Øyvind Strømmen
 */
public class JiraParseClientTest {

    @InjectMocks
    private JiraParseClient parseClient;

    @Mock
    private FieldReflectionClient reflectionClient;

    private static final boolean TARGET = true;

    private static final String ACTUAL = "/actual-result.json";
    private static final String IDEAL = "/ideal-result.json";
    private static final String MAX_ONE = "/max-one-result.json";
    private static final String NO_RESULT = "/no-result.json";

    @Before
    public void setup() {
        initMocks(this);
    }

    @Ignore
    @Test
    public void threeTasksReturnedWhenActualAndSimpleJiraEntity() throws Exception {
        mockReflectionClient(createSimpleJiraEntitySearchTree());
        List<SimpleJiraEntity> excpectedList = asList(
                new SimpleJiraEntity("PKDRAGE-1347", "Systemdokumentasjon - PFS501 Krav", "Sub-task"),
                new SimpleJiraEntity("PKDRAGE-1346", "Leverandørtest - Testsammendrag", "Testsammendrag"),
                new SimpleJiraEntity("PKDRAGE-1345", "Leverandørtest - Testplanlegging", "Testplanlegging")
        );

        List<SimpleJiraEntity> list = parseClient.getElements(createInputStream(ACTUAL), SimpleJiraEntity.class);

        assertEquals(excpectedList, parseClient.getElements(createInputStream(ACTUAL), SimpleJiraEntity.class));
    }

    private InputStream createInputStream(String fileName) throws FileNotFoundException {
        return getClass().getResourceAsStream(fileName);
    }

    private void mockReflectionClient(TreeNode<String> searchTree) {
        when(reflectionClient.extractFields(SimpleJiraEntity.class)).thenReturn(searchTree);
    }

    private TreeNode<String> createSimpleJiraEntitySearchTree() {
        TreeNode<String> issues = new TreeNode<String>("issues");
        TreeNode<String> key = new TreeNode<String>("key", TARGET);
        TreeNode<String> fields = new TreeNode<String>("fields");
        TreeNode<String> summary = new TreeNode<String>("summary", TARGET);
        TreeNode<String> issuetype = new TreeNode<String>("issuetype");
        TreeNode<String> name = new TreeNode<String>("name", TARGET);

        issues.addChild(key);
        issues.addChild(fields);

        fields.addChild(summary);
        fields.addChild(issuetype);

        issuetype.addChild(name);

        return issues;
    }
}