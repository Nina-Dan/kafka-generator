package com.demo.kafkagenerator.controller;

import com.demo.kafkagenerator.dto.DataDto;
import com.demo.kafkagenerator.dto.DataTestOptionsDto;
import com.demo.kafkagenerator.mapper.DataMapper;
import com.demo.kafkagenerator.mapper.DataTestOptionsMapper;
import com.demo.kafkagenerator.model.Data;
import com.demo.kafkagenerator.model.DataTestOptions;
import com.demo.kafkagenerator.service.KafkaDataService;
import com.demo.kafkagenerator.service.TestDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/data")
public class DataController {

    private final DataMapper dataMapper;

    private final DataTestOptionsMapper dataTestOptionsMapper;

    private final KafkaDataService kafkaDataService;

    private final TestDataService testDataService;

    @PostMapping("/send")
    public void send(@RequestBody DataDto dataDto) {

        Data data = dataMapper.toEntity(dataDto);
        kafkaDataService.send(data);

    }

    @PostMapping("/test/send")
    public void testSend(@RequestBody DataTestOptionsDto dataTestOptionsDto) {

        DataTestOptions dataTestOptions = dataTestOptionsMapper.toEntity(dataTestOptionsDto);
        testDataService.sendMessages(dataTestOptions);

    }
}
