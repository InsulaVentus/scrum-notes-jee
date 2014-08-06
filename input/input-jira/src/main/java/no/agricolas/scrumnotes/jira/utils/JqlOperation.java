package no.agricolas.scrumnotes.jira.utils;

/**
 * @author Øyvind Strømmen
 */
public enum JqlOperation {

    GET_SUB_ISSUES("search?jql=parent=");

    private String jqlQuery;

    private JqlOperation(String jqlQuery) {
        this.jqlQuery = jqlQuery;
    }

    public String getJqlQuery() {
        return jqlQuery;
    }
}
