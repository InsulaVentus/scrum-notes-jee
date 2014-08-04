package no.agricolas.scrumnotes.common;

import no.agricolas.scrumnotes.entities.SubtaskNote;

import java.util.List;

/**
 * Methods that redirect calls to the appropiate fetching services whitin the input module
 *
 * @author Øyvind Strømmen
 */
public class FetchService implements SimpleFetchService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SubtaskNote> fetchFromJira(String url) {
        return null; //TODO: Add call to JiraFetchService
    }
}
