package com.skuniv.bigdata.batch.items;

import com.skuniv.bigdata.util.OpenApiConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class OpenApiPartitioner implements Partitioner {

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> map = new HashMap<>();
        int i = 0;
        for (OpenApiConstants.OpenApiRequest myEnum : OpenApiConstants.OpenApiRequest.values()) {
            log.warn("enum name => {}", myEnum.name());
            ExecutionContext context = new ExecutionContext();
            context.putString(OpenApiConstants.API_KIND, myEnum.name());
            context.putString(OpenApiConstants.URL, myEnum.getUrl());
            context.putString(OpenApiConstants.BUILDING_TYPE, myEnum.getBuildingType());
            context.putString(OpenApiConstants.DEAL_TYPE, myEnum.getDealType());
            map.put(OpenApiConstants.PARTITION_KEY + i, context);
            i++;
        }
        return map;
    }
}
