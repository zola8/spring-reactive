package com.mz.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Toyota")
public class Toyota implements Car {

    @Override
    public String getBrand() {
        return "Toyota";
    }
}
