package cz.cvut.fit.warzeada.thesis.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import cz.cvut.fit.warzeada.thesis.graphql.GraphQlService;
import cz.cvut.fit.warzeada.thesis.rest.dto.ChangesDto;
import cz.cvut.fit.warzeada.thesis.rest.dto.FieldValueDto;
import cz.cvut.fit.warzeada.thesis.rest.dto.ProjectItemUpdateDto;
import cz.cvut.fit.warzeada.thesis.rest.dto.ProjectsV2ItemDto;
import cz.cvut.fit.warzeada.thesis.rest.dto.SenderDto;

import static org.mockito.Mockito.*;

import java.util.stream.Stream;

@ExtendWith(SpringExtension.class)
class ProjectItemUpdateServiceTest {

    private static final String ISSUE = "Issue";
    private static final String EDITED = "edited";
    private static final String SINGLE_SELECT = "single_select";
    private static final String IN_PROGRESS = "In Progress";
    private static final String SENDER_NODE_ID = "sender";
    private static final String CONTENT_NODE_ID = "content";

    @Mock
    private GraphQlService graphQlService;

    @Mock
    private Logger logger;

    @InjectMocks
    private ProjectItemUpdateService projectItemUpdateService;

    @ParameterizedTest
    @MethodSource("provideTestData")
    void processItemUpdate(String contentType, String action, String fieldType, String status, int statusVerified,
                           int assigneeUpdated) {
        // prepare data
        var nodeId = "nodeId";
        var projectItemUpdateDto = new ProjectItemUpdateDto();
        projectItemUpdateDto.setAction(action);
        projectItemUpdateDto.setProjectsV2Item(new ProjectsV2ItemDto());
        projectItemUpdateDto.getProjectsV2Item().setContentType(contentType);
        projectItemUpdateDto.getProjectsV2Item().setNodeId(nodeId);
        projectItemUpdateDto.getProjectsV2Item().setContentNodeId(CONTENT_NODE_ID);
        projectItemUpdateDto.setChanges(new ChangesDto());
        projectItemUpdateDto.getChanges().setFieldValue(new FieldValueDto());
        projectItemUpdateDto.getChanges().getFieldValue().setFieldType(fieldType);
        projectItemUpdateDto.setSender(new SenderDto());
        projectItemUpdateDto.getSender().setNodeId(SENDER_NODE_ID);
        //prepare mock
        when(graphQlService.getIssueStatus(nodeId)).thenReturn(status);

        // call tested method
        projectItemUpdateService.processItemUpdate(projectItemUpdateDto);

        // verify results
        verify(graphQlService, times(statusVerified)).getIssueStatus(nodeId);
        verify(graphQlService, times(assigneeUpdated)).addAssigneeToIssue(SENDER_NODE_ID, CONTENT_NODE_ID);
    }

    static Stream<Arguments> provideTestData() {
        return Stream.of(
            Arguments.of(ISSUE, EDITED, SINGLE_SELECT, IN_PROGRESS, 1, 1), // everything matches
            Arguments.of(ISSUE, EDITED, SINGLE_SELECT, "TODO", 1, 0), // wrong status, getStatus called, addAssignee not
            Arguments.of("a", EDITED, SINGLE_SELECT, IN_PROGRESS, 0, 0), // parameters before status don't match
            Arguments.of("a", "b", SINGLE_SELECT, IN_PROGRESS, 0, 0),
            Arguments.of("a", "b", "c", IN_PROGRESS, 0, 0),
            Arguments.of(ISSUE, EDITED, "a", IN_PROGRESS, 0, 0),
            Arguments.of(ISSUE, "b", "c", IN_PROGRESS, 0, 0),
            Arguments.of("a", EDITED, "b", IN_PROGRESS, 0, 0),
            Arguments.of("a", EDITED, SINGLE_SELECT, IN_PROGRESS, 0, 0),
            Arguments.of("a", EDITED, "c", IN_PROGRESS, 0, 0),
            Arguments.of("a", "b", "c", IN_PROGRESS, 0, 0)
        );
    }

}
