package com.mz;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class EchoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EchoController.class);
    private static final Random RANDOM = new Random();


    @GetMapping("/sync")
    public String sync(@RequestParam String str) throws InterruptedException {
        int waiting = RANDOM.nextInt(3) + 3;

        LOGGER.info("... in:  {}, waiting: {} sec", str, waiting);
        Thread.sleep(waiting * 1000);
        LOGGER.info("... end: {}", str, waiting);

        return "sync: " + str;
    }

}
