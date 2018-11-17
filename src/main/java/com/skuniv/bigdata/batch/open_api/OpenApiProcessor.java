package com.skuniv.bigdata.batch.open_api;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@StepScope
@Component
public class OpenApiProcessor implements ItemProcessor<String,String> {

    @Override
    public String process(String item) throws Exception {

        return "";
    }
}
