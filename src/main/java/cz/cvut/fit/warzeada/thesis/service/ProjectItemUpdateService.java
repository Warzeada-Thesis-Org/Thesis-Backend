package cz.cvut.fit.warzeada.thesis.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.cvut.fit.warzeada.thesis.graphql.GraphQlService;
import cz.cvut.fit.warzeada.thesis.rest.dto.ProjectItemUpdateDto;

@Service
public class ProjectItemUpdateService {

    private static final String ISSUE = "Issue";
    private static final String EDITED = "edited";
    private static final String SINGLE_SELECT = "single_select";
    private static final String IN_PROGRESS = "In Progress";

    @Autowired
    private Logger logger;

    @Autowired
    private GraphQlService graphQlService;

    public void processItemUpdate(ProjectItemUpdateDto update) {
        if (isIssueMovedToInProgress(update)) {
            logger.info("Detected an issue that has been moved to In Progress");
            logger.info("Assigning the issue to user '{}'", update.getSender().getLogin());

            var assigneeId = update.getSender().getNodeId();
            var itemId = update.getProjectsV2Item().getContentNodeId();
            graphQlService.addAssigneeToIssue(assigneeId, itemId);
        }
    }

    private boolean isIssueMovedToInProgress(ProjectItemUpdateDto update) {
        if (ISSUE.equals(update.getProjectsV2Item().getContentType())
                && EDITED.equals(update.getAction())
                && SINGLE_SELECT.equals(update.getChanges().getFieldValue().getFieldType())) {

            // retrieving issue status via another GraphQL call as the status is not in the payload send via the webhook
            String status = graphQlService.getIssueStatus(update.getProjectsV2Item().getNodeId());
            return IN_PROGRESS.equals(status);
        }
        return false;
    }

}
