package no.agricolas.scrumnotes.jira.utils.mock_entities;

import no.agricolas.scrumnotes.domain.JSONBase;
import no.agricolas.scrumnotes.domain.JSONField;

/**
 * @author Øyvind Strømmen
 */
@JSONBase("issues")
public class SimpleJiraEntity {

    @JSONField("key")
    private String field1;

    @JSONField("fields:summary")
    private String field2;

    @JSONField("fields:issuetype:name")
    private String field3;

    public SimpleJiraEntity(String field1, String field2, String field3) {
        setField1(field1);
        setField2(field2);
        setField3(field3);
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

}
