package com.skuniv.bigdata.batch.items;

import com.google.gson.Gson;
import com.skuniv.bigdata.configuration.TemplateConfiguration;
import com.skuniv.bigdata.domain.dto.BargainOpenApiDto;
import com.skuniv.bigdata.domain.dto.CharterWithRentOpenApiDto;
import com.skuniv.bigdata.domain.dto.YamlDto;
import com.skuniv.bigdata.domain.dto.open_api.BuildingDealDto;
import com.skuniv.bigdata.util.DateUtil;
import com.skuniv.bigdata.util.OpenApiConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Component
@StepScope
@RequiredArgsConstructor
@Import({YamlDto.class, TemplateConfiguration.class})
public class OpenApiReader implements ItemReader<BuildingDealDto>, StepExecutionListener {

    private static final Gson gson = new Gson();
    private final RestTemplate restTemplate;
    private final YamlDto yamlDto;

    private String url, buildingType, dealType;
    private Iterator<URI> iter;


    @Override
    public void beforeStep(StepExecution stepExecution) {
        String date = DateUtil.createCurrent();

        if (iter == null) {
            ExecutionContext ctx = stepExecution.getExecutionContext();
            url = (String) ctx.get(OpenApiConstants.URL);
            buildingType = (String) ctx.get(OpenApiConstants.BUILDING_TYPE);
            dealType = (String) ctx.get(OpenApiConstants.DEAL_TYPE);

            List<URI> urlList = new ArrayList<>();
            Iterator<String> groupIter = OpenApiConstants.regionMap.keySet().iterator();
            yamlDto.setServiceKey(yamlDto.getServiceKey().replaceAll("\\\\", ""));

            while (groupIter.hasNext()) {
                StringBuilder sb = new StringBuilder();
                try {
                    urlList.add(new URI(sb
                            .append(String.format(url, groupIter.next(), date))
                            .append(OpenApiConstants.SERVICE_KEY_PARAM)
                            .append(yamlDto.getServiceKey())
                            .toString()));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            iter = urlList.iterator();
        }
    }

    private void setBuildingWithDeal(BuildingDealDto buildingDealDto) {
        buildingDealDto.setDealType(dealType);
        buildingDealDto.setBuildingType(buildingType);
    }

    @Override
    public BuildingDealDto read() throws Exception {
        while (iter.hasNext()) {
            if (StringUtils.equals(dealType, OpenApiConstants.BARGAIN_NUM)) {
                URI uri = iter.next();
                BargainOpenApiDto bargainOpenApiDto = restTemplate.getForObject(uri, BargainOpenApiDto.class);
                setBuildingWithDeal(bargainOpenApiDto);
                return bargainOpenApiDto;
            }
            URI uri = iter.next();
            CharterWithRentOpenApiDto charterWithRentOpenApiDto = restTemplate.getForObject(iter.next(), CharterWithRentOpenApiDto.class);
            setBuildingWithDeal(charterWithRentOpenApiDto);
            return charterWithRentOpenApiDto;
        }
        return null;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}
