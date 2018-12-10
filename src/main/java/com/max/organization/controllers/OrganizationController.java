package com.max.organization.controllers;

import com.max.organization.dto.OrganizationDto;
import com.max.organization.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@PreAuthorize("hasRole('ROLE_ADMIN')")
//@PreAuthorize("#oauth2.hasScope('web')")
@EnableGlobalMethodSecurity(prePostEnabled = true)
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

        LOG.info("availableProcessors: " + Runtime.getRuntime().availableProcessors() +
                         ", java: " + System.getProperty("java.version") +
                         "<<<============================");

        LOG.info("getOrganizationById called");

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

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.DELETE)
    public ResponseEntity<OrganizationDto> deleteOrganization(@PathVariable("organizationId") String organizationId) {

        boolean wasDeleted = organizationService.delete(organizationId);

        if (!wasDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.POST)
    public ResponseEntity<String> deleteOrganization(@PathVariable("organizationId") String orgId,
                                                     @RequestBody OrganizationDto organizationDto) {

        boolean wasAdded = organizationService.add(orgId, organizationDto);

        if (!wasAdded) {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
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
