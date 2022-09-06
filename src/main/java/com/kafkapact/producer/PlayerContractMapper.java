package com.kafkapact.producer;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class PlayerContractMapper {
    @NotNull
    public ContractDetails mapContractDetails(PlayerContract playerContract) {
        return ContractDetails.builder()
                .documentType(playerContract.getDocumentType())
                .firstName(playerContract.getFirstName())
                .lastName(playerContract.getLastName())
                .team(playerContract.getTeam())
                .duration(playerContract.getDuration())
                .salary(playerContract.getSalary())
                .build();
    }
}
