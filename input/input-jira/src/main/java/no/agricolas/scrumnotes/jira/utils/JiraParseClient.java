package no.agricolas.scrumnotes.jira.utils;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.json.stream.JsonParser.Event;

/**
 * @author Øyvind Strømmen
 */
public class JiraParseClient {

    private FieldReflectionClient reflectionClient;

    private JsonParser parser;

    public <T> List<T> getIssues(InputStream inputStream, Class<T> clazz) {

        TreeNode<String> searchTree = reflectionClient.extractFields(clazz);

        JsonParserFactory factory = Json.createParserFactory(null);
        parser = factory.createParser(inputStream);

        traverse(searchTree.getChildren());

        return null;
    }

    private void traverse(Map<TreeNode<String>, TreeNode<String>> children) {
        Map<TreeNode<String>, TreeNode<String>> copy = new HashMap<TreeNode<String>, TreeNode<String>>(children);

        while (parser.hasNext() && !copy.isEmpty()) { //Loop through children until we find them all

            Event event = parser.next();

            switch (event) {
                case KEY_NAME:
                    TreeNode<String> node = copy.remove(new TreeNode<String>(parser.getString()));

                    if (node != null) {

                        if (node.isTarget() && node.hasChildren()) { //Found a node we were looking for
                            addTarget();
                            traverse(node.getChildren());

                        } else if (node.isTarget()) {
                            addTarget();
                        } else {
                            traverse(node.getChildren());
                        }
                    }
                    break;
            }
        }
    }

    private void addTarget() {
        Event event = parser.next();

        String value = parser.getString();
    }

    public void setReflectionClient(FieldReflectionClient reflectionClient) {
        this.reflectionClient = reflectionClient;
    }
}
