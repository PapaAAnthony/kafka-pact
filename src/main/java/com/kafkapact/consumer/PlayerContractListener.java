package com.kafkapact.consumer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerContractListener {
    private final Logger logger = LoggerFactory.getLogger(PlayerContractListener.class);
    private final HeadlineGenerator headlineGenerator;

    @KafkaListener(id = "playerContractConsumer", topics = "contract-details")
    public void listen(@Payload ContractDetails details) {
        logger.info("Contract consumed from topic!");
        logger.info(headlineGenerator.generateHeadLine(details));
    }
}
