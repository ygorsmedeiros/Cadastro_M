package com.ygor.cadastromestre.model;

public record EscalationContact(
        PersonContact head,
        PersonContact leader,
        PersonContact pointOfContact,
        String fastcomnsGroup,
        String assignmentTower
) {
}
