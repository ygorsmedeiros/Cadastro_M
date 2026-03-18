package com.ygor.cadastromestre.model;

public record ServiceContact(
        String service,
        PersonContact specialist,
        String fastcomnsGroup,
        String assignmentTower,
        String notes
) {
}
