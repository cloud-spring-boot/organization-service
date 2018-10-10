package com.max.organization.dto;

public class OrganizationDto {

    private final String id;

    private final String name;

    public OrganizationDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
