package com.demo.kafkagenerator.service;

import com.demo.kafkagenerator.model.Data;

public interface KafkaDataService {

    void send(Data data);

}
