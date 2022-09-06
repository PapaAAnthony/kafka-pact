package com.kafkapact.consumer;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
@EqualsAndHashCode
public class ContractDetails {
    private String documentType;
    private String firstName;
    private String lastName;
    private String team;
    private String duration;
    private String salary;
}
