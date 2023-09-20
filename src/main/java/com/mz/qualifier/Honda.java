package com.mz.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Honda")
public class Honda implements Car {

    @Override
    public String getBrand() {
        return "Honda";
    }
}
