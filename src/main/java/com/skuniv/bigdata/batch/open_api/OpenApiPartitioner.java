package com.skuniv.bigdata.batch.open_api;

import com.skuniv.bigdata.util.OpenApiConstants;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

import java.util.HashMap;
import java.util.Map;

public class OpenApiPartitioner implements Partitioner {

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> map = new HashMap<>();
        int i = 0;
        for (OpenApiConstants.OpenApiRequest myEnum : OpenApiConstants.OpenApiRequest.values()) {
            ExecutionContext context = new ExecutionContext();
            context.putString(OpenApiConstants.URL,myEnum.getUrl());
            context.putString(OpenApiConstants.BUILDING_TYPE, myEnum.getBuildingType());
            context.putString(OpenApiConstants.DEAL_TYPE, myEnum.getDealType());
            map.put(OpenApiConstants.PARTITION_KEY + i, context);
            i++;
        }
        return map;
    }
}
