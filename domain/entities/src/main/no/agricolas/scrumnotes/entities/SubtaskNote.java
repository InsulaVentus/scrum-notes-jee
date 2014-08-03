package no.agricolas.scrumnotes.entities;

/**
 * @author Simen Søhol
 */
public class SubtaskNote {
    private String header;
    private String parentTask;
    private String note;
    private String etc;

    public SubtaskNote(String header, String parentTask, String note, String etc) {
        setHeader(header);
        setParentTask(parentTask);
        setNote(note);
        setEtc(etc);
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
}
