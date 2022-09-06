package com.kafkapact.producer;

import au.com.dius.pact.provider.MessageAndMetadata;
import au.com.dius.pact.provider.PactVerifyProvider;
import au.com.dius.pact.provider.junit5.MessageTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Consumer;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.time.LocalDate;
import java.util.Map;

@Provider("playerContractProducer")
@Consumer("playerContractConsumer")
@PactBroker(url = "http://localhost:9292")
class PlayerContractMapperTest {

    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String KEY_CONTENT_TYPE = "contentType";
    private final PlayerContractMapper contractMapper = new PlayerContractMapper();

    @BeforeEach
    void before(PactVerificationContext context) {
        context.setTarget(new MessageTestTarget());
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @PactVerifyProvider("A player contract")
    MessageAndMetadata verifyMessage() {

        PlayerContract playerContract = PlayerContract.builder()
                .age(37)
                .dateSigned(LocalDate.of(2022, 4, 3))
                .documentType("contract")
                .firstName("Lebron")
                .lastName("James")
                .team("LA Lakers")
                .position("Power Forward")
                .duration("5 years")
                .salary("158 million USD")
                .build();

        JsonSerializer<ContractDetails> serializer = new JsonSerializer<>();

        return new MessageAndMetadata(serializer.serialize("kafka-pact", contractMapper.mapContractDetails(playerContract)),
                Map.of(KEY_CONTENT_TYPE, JSON_CONTENT_TYPE));
    }
}