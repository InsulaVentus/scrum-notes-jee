package no.agricolas.scrumnotes.jira.utils;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.json.stream.JsonParser.Event;

/**
 * @author Øyvind Strømmen
 */
public class JiraParseClient<T> {

    private FieldReflectionClient reflectionClient;

    private JsonParser parser;

    private List<T> elements;

    private Class<T> clazz;

    private String objectIdentifierKey;

    public JiraParseClient() {
        this.elements = new ArrayList<T>();
    }

    public List<T> getElements(InputStream inputStream, final Class<T> clazz) {
        setClazz(clazz);

        TreeNode<String> topNode = reflectionClient.extractFields(clazz);

        JsonParserFactory factory = Json.createParserFactory(null);
        parser = factory.createParser(inputStream);

        String baseName = topNode.getContent().toLowerCase();

        boolean lookingForBase = true;
        while (parser.hasNext()) {
            Event event = parser.next();
            
            if (event.equals(Event.KEY_NAME)) {

                String keyName = parser.getString();
                if (lookingForBase && baseName.equals(keyName)) {
                    createObjectInstance(topNode, true);
                    lookingForBase = false;
                } else if (objectIdentifierKey != null && keyName.equals(objectIdentifierKey)) {
                    createObjectInstance(topNode, false);
                }

            }
        }
        return elements;
    }

    private void createObjectInstance(TreeNode<String> topNode, boolean firstObject) {
        if (firstObject) {
            findObjectIdentifierKey();
        }

        try {
            T instance = clazz.newInstance();
            traverse(topNode.getChildren(), instance);
            elements.add(instance);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void findObjectIdentifierKey() {
        while (parser.hasNext()) {
            Event event = parser.next();
            if (event.equals(Event.KEY_NAME)) {
                setObjectIdentifierKey(parser.getString());
                return;
            }
        }
    }

    /**
     * TODO: Consider adding a check for <code>isTarget && hasChildren()</code> if support for arrays and or objects is desired
     */
    private void traverse(Map<TreeNode<String>, TreeNode<String>> children, T instance) {
        Map<TreeNode<String>, TreeNode<String>> copy = new HashMap<TreeNode<String>, TreeNode<String>>(children);

        while (parser.hasNext() && !copy.isEmpty()) { //Loop through children until we find them all

            Event event = parser.next();

            if (event.equals(Event.KEY_NAME)) {
                TreeNode<String> node = removeElementBasedOnContent(copy, parser.getString());
                if (node != null) {
                    if (node.isTarget()) {
                        addTargetToObjectInstance(instance, node);
                    } else {
                        traverse(node.getChildren(), instance);
                    }
                }
            }
        }
    }

    private TreeNode<String> removeElementBasedOnContent(Map<TreeNode<String>, TreeNode<String>> map, String fieldKey) {
        for (Map.Entry<TreeNode<String>, TreeNode<String>> entry : map.entrySet()) {
            TreeNode<String> key = entry.getKey();
            if (key.getContent().equals(fieldKey)) {
                return map.remove(key);
            }
        }
        return null;
    }

    private void addTargetToObjectInstance(T instance, TreeNode<String> node) {
        parser.next();
        try {
            Field field = instance.getClass().getDeclaredField(node.getFieldName());
            field.setAccessible(true);
            field.set(instance, parser.getString());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setReflectionClient(FieldReflectionClient reflectionClient) {
        this.reflectionClient = reflectionClient;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void setObjectIdentifierKey(String objectIdentifierKey) {
        this.objectIdentifierKey = objectIdentifierKey;
    }
}
