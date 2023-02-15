package cz.cvut.fit.warzeada.thesis.rest.dto;

public class ProjectItemUpdateDto {

    private String action;
    private ProjectsV2ItemDto projectsV2Item;
    private ChangesDto changes;
    private SenderDto sender;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ProjectsV2ItemDto getProjectsV2Item() {
        return projectsV2Item;
    }

    public void setProjectsV2Item(ProjectsV2ItemDto projectsV2Item) {
        this.projectsV2Item = projectsV2Item;
    }

    public ChangesDto getChanges() {
        return changes;
    }

    public void setChanges(ChangesDto changes) {
        this.changes = changes;
    }

    @Override
    public String toString() {
        return "WebHookDto [action=" + action + ", projectsV2Item=" + projectsV2Item + "]";
    }

    public SenderDto getSender() {
        return sender;
    }

    public void setSender(SenderDto sender) {
        this.sender = sender;
    }

}

