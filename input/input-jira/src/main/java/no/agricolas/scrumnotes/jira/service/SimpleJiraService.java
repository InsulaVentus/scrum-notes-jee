package no.agricolas.scrumnotes.jira.service;

import no.agricolas.scrumnotes.domain.SubtaskNote;

import java.util.List;

/**
 * Methods exposing functionality in the jira module
 *
 * @author Øyvind Strømmen
 */
public interface SimpleJiraService {

    /**
     * Fetch all sub issues from an issue, given the issue id
     *
     * @param jiraId the id of the parent issue
     * @return the sub issues as a List of {@link SubtaskNote}
     */
    public List<SubtaskNote> getSubIssues(String jiraId);

}
