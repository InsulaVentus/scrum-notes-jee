package no.agricolas.scrumnotes.jira.utils;

import no.agricolas.scrumnotes.jira.utils.mock_entities.SimpleJiraEntity;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Øyvind Strømmen
 */
public class FieldReflectionClientTest {

    private FieldReflectionClient client = new FieldReflectionClient();

    private static final boolean TARGET = true;

    @Test
    public void assertSearchTreeExtractedFromSimpleJiraEntity() {
        TreeNode<String> actualSearchTree = client.extractFields(SimpleJiraEntity.class);
        assertEquals(expectedSimpleJiraEntitySearchTree(), actualSearchTree);
    }

    private TreeNode<String> expectedSimpleJiraEntitySearchTree() {
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