package com.max.organization.service;

import com.max.organization.dto.OrganizationDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrganizationService {

    private static final Map<String, OrganizationDto> ALL_ORGANIZATIONS = new HashMap<>();

    static {
        ALL_ORGANIZATIONS.put("1", new OrganizationDto("1", "Microsoft com"));
        ALL_ORGANIZATIONS.put("2", new OrganizationDto("2", "Amazon inc."));
        ALL_ORGANIZATIONS.put("3", new OrganizationDto("3", "IBM"));
        ALL_ORGANIZATIONS.put("4", new OrganizationDto("4", "Facebook"));
        ALL_ORGANIZATIONS.put("5", new OrganizationDto("5", "Google"));
    }

    public OrganizationDto getById(String organizationId) {
        return ALL_ORGANIZATIONS.get(organizationId);
    }

    public boolean deleteById(String organizationId){
        return ALL_ORGANIZATIONS.remove(organizationId) != null;
    }

}
