package com.demo.kafkagenerator.service.impl;

import com.demo.kafkagenerator.model.Data;
import com.demo.kafkagenerator.model.DataTestOptions;
import com.demo.kafkagenerator.service.KafkaDataService;
import com.demo.kafkagenerator.service.TestDataService;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestDataServiceImpl implements TestDataService {

    private final ScheduledExecutorService scheduledExecutorService
        = Executors.newSingleThreadScheduledExecutor();

    private final KafkaDataService kafkaDataService;

    @Override
    public void sendMessages(DataTestOptions dataTestOptions) {
        System.out.println("SEND MESSAGES");

        if (dataTestOptions.getMeasurementTypes().length > 0) {
            scheduledExecutorService.scheduleAtFixedRate(
                () -> {
                    Data data = new Data();
                    data.setSensorId(
                        (long) getRandomNumber(1, 10)
                    );
                    data.setMeasurement(
                        getRandomNumber(1, 15)
                    );
                    data.setMeasurementType(
                        getRandomMeasurementType(dataTestOptions.getMeasurementTypes())
                    );
                    data.setTimestamp(LocalDateTime.now());
                    kafkaDataService.send(data);
                },
                0,
                dataTestOptions.getDelayInSeconds(),
                TimeUnit.SECONDS
            );
        }

    }

    private Data.MeasurementType getRandomMeasurementType(Data.MeasurementType[] measurementTypes) {
        int randomTypeId = (int) (Math.random() * measurementTypes.length);
        return measurementTypes[randomTypeId];
    }

    private double getRandomNumber(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }
}
