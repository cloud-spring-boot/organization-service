package com.max.organization.controllers;


import com.max.organization.dto.OrganizationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/organizations/")
public class OrganizationController {

    @RequestMapping(value = "/{organizationId}", method = RequestMethod.GET)
    public ResponseEntity<OrganizationDto> getLicenseById(@PathVariable("organizationId") String organizationId) {

        OrganizationDto data = new OrganizationDto("1", "Microsoft com");

        return ResponseEntity.status(HttpStatus.OK).body(data);
    }

}
