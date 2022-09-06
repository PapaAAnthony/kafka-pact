package com.kafkapact.consumer;

import org.springframework.stereotype.Component;

@Component
public class HeadlineGenerator {
    public String generateHeadLine(ContractDetails contractDetails) {
        return "BREAKING: "
                .concat(contractDetails.getFirstName())
                .concat(" ")
                .concat(contractDetails.getLastName())
                .concat(" has just signed a ")
                .concat(contractDetails.getDocumentType())
                .concat(" worth ")
                .concat(contractDetails.getSalary())
                .concat(" over ")
                .concat(contractDetails.getDuration())
                .concat(" with the " )
                .concat(contractDetails.getTeam());
    }
}
