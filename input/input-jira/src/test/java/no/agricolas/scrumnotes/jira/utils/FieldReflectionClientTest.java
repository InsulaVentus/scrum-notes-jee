package no.agricolas.scrumnotes.jira.utils;

import no.agricolas.scrumnotes.domain.SubtaskNote;
import org.junit.Test;

/**
 * @author Øyvind Strømmen
 */
public class FieldReflectionClientTest {

    private FieldReflectionClient client = new FieldReflectionClient();

    @Test
    public void testTest() {
        TreeNode<String> treeNode = client.extractFields(SubtaskNote.class);
        return;
    }
}