package com.kafkapact.producer;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContractDetails {
    private String documentType;
    private String firstName;
    private String lastName;
    private String team;
    private String duration;
    private String salary;
}
