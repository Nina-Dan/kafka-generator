package com.demo.kafkagenerator.service;

import com.demo.kafkagenerator.model.DataTestOptions;

public interface TestDataService {

    void sendMessages(DataTestOptions dataTestOptions);

}
