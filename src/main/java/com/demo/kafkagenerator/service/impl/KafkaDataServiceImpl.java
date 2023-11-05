package com.demo.kafkagenerator.service.impl;

import com.demo.kafkagenerator.model.Data;
import com.demo.kafkagenerator.service.KafkaDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Service
@RequiredArgsConstructor
public class KafkaDataServiceImpl implements KafkaDataService {

    private final KafkaSender<String, Object> kafkaSender;

    @Override
    public void send(Data data) {
        System.out.println("KAFKA SEND MESSAGES");
        String topic = switch (data.getMeasurementType()) {
            case TEMPERATURE -> "data-temperature";
            case VOLTAGE -> "data-voltage";
            case POWER -> "data-power";
        };

        kafkaSender.send(Mono.just(SenderRecord.create(
                    topic,
                    0,
                    System.currentTimeMillis(),
                    String.valueOf(data.hashCode()),
                    data,
                    null
                )
            )
        ).subscribe();

    }

}
