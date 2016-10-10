package com.newsweaver.lottery;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


/**
 * Created by gary on 08/10/2016.
 */
@Configuration
@ComponentScan(
        basePackages = {"com.newsweaver.lottery"},
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(value = Repository.class, type = FilterType.ANNOTATION),
                @ComponentScan.Filter(value = Service.class, type = FilterType.ANNOTATION),
                @ComponentScan.Filter(value = Component.class, type = FilterType.ANNOTATION)
        })
public class ServiceConfiguration {

    @Configuration
    @Profile("default")
    @PropertySource("classpath:service.properties")
    static class Defaults {}

}
