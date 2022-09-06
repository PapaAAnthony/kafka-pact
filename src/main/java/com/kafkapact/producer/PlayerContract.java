package com.kafkapact.producer;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Data
@Builder
@Jacksonized
public class PlayerContract {
    private String documentType;
    private String firstName;
    private String lastName;
    private int age;
    private String position;
    private String team;
    private boolean signed;
    private LocalDate dateSigned;
    private String duration;
    private String salary;
}
