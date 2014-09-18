package no.agricolas.scrumnotes.jira.utils.mock_entities;

import no.agricolas.scrumnotes.domain.JSONBase;
import no.agricolas.scrumnotes.domain.JSONField;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    public SimpleJiraEntity() {
        this(null, null, null);
    }

    public SimpleJiraEntity(String field1, String field2, String field3) {
        setField1(field1);
        setField2(field2);
        setField3(field3);
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(field1)
                .append(field2)
                .append(field3)
                .toHashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null) {
            return false;
        }
        if (object instanceof SimpleJiraEntity) {
            final SimpleJiraEntity simpleJiraEntity = (SimpleJiraEntity) object;
            return new EqualsBuilder()
                    .append(field1, simpleJiraEntity.field1)
                    .append(field2, simpleJiraEntity.field2)
                    .append(field3, simpleJiraEntity.field3)
                    .isEquals();
        }
        return true;
    }
}
