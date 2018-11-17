package com.skuniv.bigdata.batch.open_api;

import com.google.gson.Gson;
import com.skuniv.bigdata.domain.dto.BargainOpenApiDto;
import com.skuniv.bigdata.domain.dto.CharterWithRentOpenApiDto;
import com.skuniv.bigdata.domain.dto.open_api.BuildingDealDto;
import com.skuniv.bigdata.util.OpenApiConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
@StepScope
public class OpenApiReader implements ItemReader<String>, StepExecutionListener {

    @Autowired
    private Gson gson;

    private String url, buildingType, dealType;
    private Iterator<URI> iter;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void beforeStep(StepExecution stepExecution) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        Date currentTime = new Date();
        String date = formatter.format(currentTime);

        if (iter == null) {
            ExecutionContext ctx = stepExecution.getExecutionContext();
            url = (String) ctx.get(OpenApiConstants.URL);
            buildingType = (String) ctx.get(OpenApiConstants.BUILDING_TYPE);
            dealType = (String) ctx.get(OpenApiConstants.DEAL_TYPE);

            List<URI> urlList = new ArrayList<>();
            Iterator<String> groupIter = OpenApiConstants.regionMap.keySet().iterator();
            StringBuilder sb = new StringBuilder();

            while (groupIter.hasNext()) {
                try {
                    urlList.add(new URI(sb
                            .append(String.format(url, groupIter.next(), date))
                            .append(OpenApiConstants.SERVER_KEY)
                            .toString()));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            iter = urlList.iterator();
        }
    }

    private void setBuildingWithDeal(BuildingDealDto buildingDealDto){
        buildingDealDto.setDealType(Integer.parseInt(dealType));
        buildingDealDto.setBuildingType(Integer.parseInt(buildingType));
    }

    @Override
    public String read() throws Exception {
        while (iter.hasNext()) {
            if (StringUtils.equals(dealType, OpenApiConstants.BARGAIN_NUM)) {
                BargainOpenApiDto bargainOpenApiDto = restTemplate.getForObject(iter.next(), BargainOpenApiDto.class);
                setBuildingWithDeal(bargainOpenApiDto);
                return gson.toJson(bargainOpenApiDto);
            }
            CharterWithRentOpenApiDto charterWithRentOpenApiDto = restTemplate.getForObject(iter.next(), CharterWithRentOpenApiDto.class);
            setBuildingWithDeal(charterWithRentOpenApiDto);
            return gson.toJson(charterWithRentOpenApiDto);
        }
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
