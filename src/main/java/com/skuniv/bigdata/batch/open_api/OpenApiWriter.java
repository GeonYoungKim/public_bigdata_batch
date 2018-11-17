package com.skuniv.bigdata.batch.open_api;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@StepScope
@Component
public class OpenApiWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> items) throws Exception {
        for(String item : items){
            System.out.println(item);
        }
    }
}
