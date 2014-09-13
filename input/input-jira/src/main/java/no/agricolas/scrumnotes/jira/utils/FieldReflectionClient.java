package no.agricolas.scrumnotes.jira.utils;

import no.agricolas.scrumnotes.domain.JSONBase;
import no.agricolas.scrumnotes.domain.JSONField;

import java.lang.reflect.Field;

import static java.lang.String.format;

/**
 * @author Øyvind Strømmen
 */
public class FieldReflectionClient {

    private static final String MSG_MISSING_BASE_ANNOTATION = "The class %s does not have the required JSONBase annotation";
    private static final String MSG_MISSING_VALID_FIELD_ANNOTATION = "The class %s does not have any valid JSONField annotations";

    public <T> TreeNode<String> extractFields(Class<T> clazz) {
        JSONBase jsonBase = clazz.getAnnotation(JSONBase.class);
        if (jsonBase == null) {
            throw new RuntimeException(format(MSG_MISSING_BASE_ANNOTATION, clazz.getName()));
        }
        String base = jsonBase.value();
        TreeNode<String> treeRoot = new TreeNode<String>(base);

        Boolean validJsonFieldFound = null;
        for (Field field : clazz.getDeclaredFields()) {
            JSONField jsonField = field.getAnnotation(JSONField.class);

            String value = jsonField != null ? jsonField.value() : null;
            validJsonFieldFound = value != null ? Boolean.TRUE : null;

            if (value != null) {
                populateTree(treeRoot, value);
            }
        }

        if (Boolean.FALSE.equals(validJsonFieldFound)) {
            throw new RuntimeException(format(MSG_MISSING_VALID_FIELD_ANNOTATION, clazz.getName()));
        }
        return treeRoot;
    }

    private void populateTree(TreeNode<String> node, String pathString) {
        String[] path = pathString.split(":");
        int pathLength = path.length;
        int lastElement = pathLength - 1;

        TreeNode<String> currentNode = node;

        for (int i = 0; i < pathLength; i++) {
            String string = path[i];
            TreeNode<String> newNode = new TreeNode<String>(string, i == lastElement);

            if (currentNode.addChild(newNode)) {
                currentNode = newNode;
            } else {
                currentNode = currentNode.getChild(newNode);
            }
        }
    }
}
