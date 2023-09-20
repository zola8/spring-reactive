package com.mz.qualifier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class ServiceTest implements MyService {

    @Override
    public String getService() {
        return "ServiceTest";
    }

}
