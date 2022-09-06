package com.kafkapact.consumer;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.consumer.junit5.ProviderType;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.messaging.Message;
import au.com.dius.pact.core.model.messaging.MessagePact;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(value = {PactConsumerTestExt.class, MockitoExtension.class})
@PactTestFor(providerName = "playerContractProducer", providerType = ProviderType.ASYNCH, pactVersion = PactSpecVersion.V3)
class PlayerContractListenerTest {

    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String KEY_CONTENT_TYPE = "contentType";

    @Mock
    private HeadlineGenerator headlineGenerator;
    @InjectMocks
    private PlayerContractListener playerContractListener;

    @Pact(consumer = "playerContractConsumer")
    MessagePact contractDetailPact(MessagePactBuilder builder) {
        PactDslJsonBody jsonBody = new PactDslJsonBody();

        jsonBody.stringType("documentType", "contract")
                .stringType("firstName", "Lebron")
                .stringType("lastName", "James")
                .stringType("team", "LA Lakers")
                .stringType("duration", "5 years")
                .stringType("salary", "158 million USD");

        return builder.expectsToReceive("A player contract")
                .withMetadata(Map.of(JSON_CONTENT_TYPE, KEY_CONTENT_TYPE))
                .withContent(jsonBody)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "contractDetailPact", providerType = ProviderType.ASYNCH)
    void successfullyGenerateHeadlineGivenValidMessage(List<Message> messages) {
        ContractDetails contractDetails = ContractDetails.builder()
                .documentType("contract")
                .firstName("Lebron")
                .lastName("James")
                .team("LA Lakers")
                .duration("5 years")
                .salary("158 million USD")
                .build();

            when(headlineGenerator.generateHeadLine(contractDetails)).thenReturn("A new headline");

        messages.forEach(message -> {
            assertDoesNotThrow(() -> playerContractListener.listen(
                    new ObjectMapper().readValue(message.contentsAsBytes(), ContractDetails.class)));

            verify(headlineGenerator, times(1)).generateHeadLine(contractDetails);
        });
    }
}