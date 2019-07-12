package com.rainbow.machinelearning.controller;

import com.rainbow.machinelearning.demo.SparkTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ${xiami}
 * @version $Id: as.java, v 0.1 2019年07月12日 17:04 Exp $
 */
@RestController
public class DemoController {

    @Autowired
    private SparkTestService sparkTestService;

    @RequestMapping("/demo/top10")
    public Map<String, Object> calculateTopTen() {
        return sparkTestService.calculateTopTen();
    }

    @RequestMapping("/demo/exercise")
    public void exercise() {
        sparkTestService.sparkExerciseDemo();
    }

    @RequestMapping("/demo/stream")
    public void streamingDemo() throws InterruptedException {
        sparkTestService.sparkStreaming();
    }
}
