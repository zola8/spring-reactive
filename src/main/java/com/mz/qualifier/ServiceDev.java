package com.mz.qualifier;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class ServiceDev implements MyService {

    @Override
    public String getService() {
        return "ServiceDev";
    }

}
