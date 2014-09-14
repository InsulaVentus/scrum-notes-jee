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

    public JiraParseClient() {
        this.elements = new ArrayList<T>();
    }

    public List<T> getElements(InputStream inputStream, final Class<T> clazz) {
        setClazz(clazz);

        TreeNode<String> topNode = reflectionClient.extractFields(clazz);

        JsonParserFactory factory = Json.createParserFactory(null);
        parser = factory.createParser(inputStream);

        String baseName = topNode.getContent().toLowerCase();

        boolean baseIsFound = false;
        while (parser.hasNext()) {
            Event event = parser.next();
            switch (event) {
                case KEY_NAME:
                    baseIsFound = baseName.equals(parser.getString());
                    break;
                case START_OBJECT:
                    baseIsFound = createObjectInstanceIfBaseIsFound(baseIsFound, topNode);
                    break;
                case START_ARRAY:
                    baseIsFound = createObjectInstanceIfBaseIsFound(baseIsFound, topNode);
                    break;
            }
        }
        return elements;
    }

    private boolean createObjectInstanceIfBaseIsFound(boolean baseIsFound, TreeNode<String> topNode) {
        if (baseIsFound) {
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
        return false;
    }

    private void traverse(Map<TreeNode<String>, TreeNode<String>> children, T instance) {
        Map<TreeNode<String>, TreeNode<String>> copy = new HashMap<TreeNode<String>, TreeNode<String>>(children);

        while (parser.hasNext() && !copy.isEmpty()) { //Loop through children until we find them all
            Event event = parser.next();

            switch (event) {
                case KEY_NAME:
                    String fieldKey = parser.getString();
                    TreeNode<String> node = copy.remove(new TreeNode<String>(fieldKey));

                    if (node != null) {
                        if (node.isTarget() && node.hasChildren()) { //TODO: Consider removing this
                            addTargetToObjectInstance(instance, fieldKey);
                            traverse(node.getChildren(), instance);
                        } else if (node.isTarget()) {
                            addTargetToObjectInstance(instance, fieldKey);
                        } else {
                            traverse(node.getChildren(), instance);
                        }
                    }
                    break;
            }
        }
    }

    private void addTargetToObjectInstance(T instance, String fieldKey) {
        parser.next();
        try {
            Field field = instance.getClass().getDeclaredField(fieldKey); //TODO: Find based on annotation, not field name


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
}
