package no.agricolas.scrumnotes.jira.utils;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParserFactory;
import java.io.InputStream;
import java.util.List;

import static javax.json.stream.JsonParser.Event;

/**
 * @author Øyvind Strømmen
 */
public class JiraParseClient {

    public <T> List<T> getIssues(InputStream inputStream, Class<T> clazz) {

        JsonParserFactory factory = Json.createParserFactory(null);
        JsonParser parser = factory.createParser(inputStream);

        while (parser.hasNext()) {

            Event event = parser.next();

            switch (event) {
                case START_OBJECT:
                    break;
                case END_OBJECT:
                    break;
            }
        }
        return null;
    }
}
