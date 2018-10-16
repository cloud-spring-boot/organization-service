package com.max.organization.controllers;

import com.max.correlation.UserContextHolder;
import com.max.organization.dto.OrganizationDto;
import com.max.organization.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "v1/organizations/")
public class OrganizationController {

    private static final Logger LOG = LoggerFactory.getLogger(OrganizationController.class);

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.GET)
    public ResponseEntity<OrganizationDto> getOrganizationById(@PathVariable("organizationId") String organizationId) {

        LOG.info("Correlation-id: {}", UserContextHolder.getUserContext().getCorrelationId());

        if (organizationId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        if ("3".equals(organizationId)) {
            sleep(17);
        }

        OrganizationDto organization = organizationService.getById(organizationId);

        if (organization == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(organizationService.getById(organizationId));
    }

    private static void sleep(int timeInSeconds) {
        try {
            TimeUnit.SECONDS.sleep(timeInSeconds);
        }
        catch (InterruptedException interEx) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException();
        }
    }

}
