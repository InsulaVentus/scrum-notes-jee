package no.agricolas.scrumnotes.jira.utils;

import no.agricolas.scrumnotes.jira.utils.mock_entities.SimpleJiraEntity;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Øyvind Strømmen
 */
public class FieldReflectionClientTest {

    private FieldReflectionClient client = new FieldReflectionClient();

    private static final boolean TARGET = true;

    @Test
    public void assertSearchTreeExtractedFromSimpleJiraEntity() {
        TreeNode<String> actualSearchTree = client.extractFields(SimpleJiraEntity.class);
        assertEqualsTreeNode(expectedSimpleJiraEntitySearchTree(), actualSearchTree);
    }

    private TreeNode<String> expectedSimpleJiraEntitySearchTree() {
        TreeNode<String> issues = new TreeNode<String>("issues");
        TreeNode<String> key = new TreeNode<String>("key", TARGET, "field1");
        TreeNode<String> fields = new TreeNode<String>("fields");
        TreeNode<String> summary = new TreeNode<String>("summary", TARGET, "field2");
        TreeNode<String> issuetype = new TreeNode<String>("issuetype");
        TreeNode<String> name = new TreeNode<String>("name", TARGET, "field3");

        issues.addChild(key);
        issues.addChild(fields);

        fields.addChild(summary);
        fields.addChild(issuetype);

        issuetype.addChild(name);

        return issues;
    }

    private static void assertEqualsTreeNode(TreeNode<String> expectedTreeNode, TreeNode<String> actualTreeNode) {
        assertEquals(expectedTreeNode, actualTreeNode);

        Map<TreeNode<String>, TreeNode<String>> expectedChildren = expectedTreeNode.getChildren();
        Map<TreeNode<String>, TreeNode<String>> actualChildren = actualTreeNode.getChildren();
        assertEquals(expectedChildren, actualChildren);

        for (Map.Entry<TreeNode<String>, TreeNode<String>> entry : expectedChildren.entrySet()) {
            TreeNode<String> expectedKey = entry.getKey();
            TreeNode<String> actualKey = actualChildren.get(expectedKey);
            assertNotNull(actualKey);
            assertEqualsTreeNode(expectedKey, actualKey);
        }
    }
}