package cz.cvut.fit.warzeada.thesis.rest.dto;

public class ProjectsV2ItemDto {

    private String nodeId;
    private String projectNodeId;
    private String contentNodeId;
    private String contentType;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getProjectNodeId() {
        return projectNodeId;
    }

    public void setProjectNodeId(String projectNodeId) {
        this.projectNodeId = projectNodeId;
    }

    public String getContentNodeId() {
        return contentNodeId;
    }

    public void setContentNodeId(String contentNodeId) {
        this.contentNodeId = contentNodeId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "ProjectsV2ItemDto [nodeId=" + nodeId + ", projectNodeId=" + projectNodeId + ", contentNodeId="
                + contentNodeId + ", contentType=" + contentType + "]";
    }

}
