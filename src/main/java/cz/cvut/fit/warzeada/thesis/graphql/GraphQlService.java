package cz.cvut.fit.warzeada.thesis.graphql;

import java.time.Duration;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Service;

@Service
public class GraphQlService {

    @Autowired
    private Logger logger;

    @Autowired
    private HttpGraphQlClient graphQlClient;

    @Value("${graphql.maxBlockTime}")
    private Long maxBlockTime;

    public String getIssueStatus(String issueId) {
        logger.info("Retrieving status of issue '{}'", issueId);

        return graphQlClient.documentName("getIssueStatus")
            .variable("issueId", issueId)
            .retrieve("node.fieldValueByName.name")
            .toEntity(String.class)
            .block(Duration.ofSeconds(maxBlockTime));
    }

    public String addAssigneeToIssue(String assigneeId, String issueId) {
        logger.info("Adding assignee '{}' to issue '{}'", assigneeId, issueId);

        return graphQlClient.documentName("addAssignee")
            .variable("issueId", issueId)
            .variable("assigneeId", assigneeId)
            .variable("clientMutationId", assigneeId)
            .retrieve("addAssigneesToAssignable.clientMutationId")
            .toEntity(String.class)
            .block(Duration.ofSeconds(maxBlockTime));
    }

}
