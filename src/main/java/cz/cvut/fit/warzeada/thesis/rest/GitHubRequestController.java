package cz.cvut.fit.warzeada.thesis.rest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.cvut.fit.warzeada.thesis.rest.dto.ProjectItemUpdateDto;
import cz.cvut.fit.warzeada.thesis.service.ProjectItemUpdateService;

@RestController
@RequestMapping("update")
public class GitHubRequestController {

    @Autowired
    private Logger logger;

    @Autowired
    private ProjectItemUpdateService projectItemUpdateService;

    @PostMapping
    public void recieveWebhookRequest(@RequestBody ProjectItemUpdateDto update) {
        logger.info("Recieved project item update");
        projectItemUpdateService.processItemUpdate(update);
    }
}
