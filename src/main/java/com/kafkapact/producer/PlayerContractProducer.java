package com.kafkapact.producer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PlayerContractProducer {
    private final KafkaTemplate<String, ContractDetails> template;
    private final PlayerContractMapper contractMapper;
    private final Logger logger = LoggerFactory.getLogger(PlayerContractProducer.class);

    public void sendContractDetails(PlayerContract playerContract) {
        template.send("contract-details", contractMapper.mapContractDetails(playerContract));
        logger.info("Contract produced to topic!");
    }
}
