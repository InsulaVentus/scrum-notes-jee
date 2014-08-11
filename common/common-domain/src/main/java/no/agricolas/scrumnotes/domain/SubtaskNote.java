package no.agricolas.scrumnotes.domain;

/**
 * @author Simen Søhol
 */
@JSONBase("issues")
public class SubtaskNote {

    private String parentTask;

    @JSONField("key")
    private String header;

    @JSONField("fields:summary")
    private String note;

    private String etc;

    @JSONField("fields:issuetype:name")
    private SubtaskType subtaskType;

    public SubtaskNote() {

    }

    public SubtaskNote(String header, String parentTask, String note, String etc, SubtaskType subtaskType) {
        setHeader(header);
        setParentTask(parentTask);
        setNote(note);
        setEtc(etc);
        setSubtaskType(subtaskType);
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getParentTask() {
        return parentTask;
    }

    public void setParentTask(String parentTask) {
        this.parentTask = parentTask;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }

    public SubtaskType getSubtaskType() {
        return subtaskType;
    }

    public void setSubtaskType(SubtaskType subtaskType) {
        this.subtaskType = subtaskType;
    }
}
