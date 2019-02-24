package com.skuniv.bigdata.batch.items;

import com.google.gson.Gson;
import com.skuniv.bigdata.domain.dto.YamlDto;
import com.skuniv.bigdata.domain.dto.open_api.BargainItemDto;
import com.skuniv.bigdata.domain.dto.open_api.CharterWithRentItemDto;
import com.skuniv.bigdata.domain.dto.open_api.GoogleLocationDto;
import com.skuniv.bigdata.domain.entity.BargainDate;
import com.skuniv.bigdata.domain.entity.Building;
import com.skuniv.bigdata.domain.entity.CharterDate;
import com.skuniv.bigdata.domain.entity.RentDate;
import com.skuniv.bigdata.repository.BuildingRepository;
import com.skuniv.bigdata.service.GoogleLocationApiService;
import com.skuniv.bigdata.util.OpenApiConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

@Slf4j
@Service
@StepScope
@Import({YamlDto.class, GoogleLocationApiService.class})
@RequiredArgsConstructor
public class ExtractDiffDataTasklet implements Tasklet, StepExecutionListener, InitializingBean {
    private static final Gson gson = new Gson();
    private final YamlDto yamlDto;
    private final BuildingRepository buildingRepository;
    private final GoogleLocationApiService googleLocationApiService;

    private String fileName;
    private String dealType;
//    private List oldDataList;
    private List newDataList;
    private int buildingType;

    private void loadDataList(BufferedReader br, List list) throws IOException {
        String line = null;
        if (StringUtils.equals(dealType, OpenApiConstants.BARGAIN_NUM)) {
            while ((line = br.readLine()) != null) {
                log.warn("line => {}", line.trim());
                list.add(gson.fromJson(line.trim(), BargainItemDto.class));
            }
            return;
        }
        while ((line = br.readLine()) != null) {
            log.warn("line => {}", line.trim());
            CharterWithRentItemDto charterWithRentItemDto = gson.fromJson(line.trim(), CharterWithRentItemDto.class);
            list.add(gson.fromJson(line.trim(), CharterWithRentItemDto.class));
        }
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.warn("beforeStep !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        ExecutionContext ctx = stepExecution.getExecutionContext();
        fileName = (String) ctx.get(OpenApiConstants.API_KIND);
        log.warn("fileName => {}", fileName);
        dealType = (String) ctx.get(OpenApiConstants.DEAL_TYPE);
        log.warn("dealType => {}", dealType);
        buildingType = Integer.parseInt((String) ctx.get(OpenApiConstants.BUILDING_TYPE));
        log.warn("buildingType => {}", buildingType);

//        oldDataList = new ArrayList<CharterWithRentItemDto>();
        newDataList = new ArrayList<CharterWithRentItemDto>();
        if (StringUtils.equals(dealType, OpenApiConstants.BARGAIN_NUM)) {
//            oldDataList = new ArrayList<BargainItemDto>();
            newDataList = new ArrayList<BargainItemDto>();
        }

        String newFileFullPath = yamlDto.getFilePath() + OpenApiConstants.FILE_DELEMETER + fileName;
        String oldFileFullPath = yamlDto.getFilePath() + OpenApiConstants.FILE_DELEMETER + fileName + OpenApiConstants.OLD;

        try (
                BufferedReader oldBr = new BufferedReader(new FileReader(new File(oldFileFullPath)));
                BufferedReader newBr = new BufferedReader(new FileReader(new File(newFileFullPath)))
        ) {
//            loadDataList(oldBr, oldDataList);
            loadDataList(newBr, newDataList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        log.warn("oldDataList => {}", oldDataList);
        log.warn("newDataList => {}", newDataList);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        // old 파일 삭제.
        StringBuilder sb = new StringBuilder();
        sb.append("rm ").append(yamlDto.getFilePath()).append(OpenApiConstants.FILE_DELEMETER).append(fileName).append(OpenApiConstants.OLD);
        try {
            Runtime.getRuntime().exec(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    void batchInsertData(List newDataList) {
        newDataList.forEach(item -> {
            log.warn("insert item => {}", item.toString());
            // 매매의 경우
            if (StringUtils.equals(dealType, OpenApiConstants.BARGAIN_NUM)) {
                BargainItemDto bargainItemDto = (BargainItemDto) item;
                int city = Integer.parseInt(bargainItemDto.getRegionCode().substring(0, 2));
                int groop = Integer.parseInt(bargainItemDto.getRegionCode().substring(2));
                Building building = buildingRepository.findByCityAndGroopAndBuildingNumAndFloor(city, groop, bargainItemDto.getBuildingNum(), bargainItemDto.getFloor());
                if (building == null) {
                    building = new Building.Builder().city(city).groop(groop).dong(bargainItemDto.getDong()).name(bargainItemDto.getName()).area(bargainItemDto.getArea()).floor(bargainItemDto.getFloor())
                            .type(buildingType).buildingNum(bargainItemDto.getBuildingNum()).constructYear(String.valueOf(bargainItemDto.getConstructYear())).build();
                    String address = bargainItemDto.getDong() + bargainItemDto.getName();
                    GoogleLocationDto googleLocationDto = googleLocationApiService.googleLocationApiCall(address.replaceAll(" ",""));
                    if (googleLocationDto != null){
                        building.setLatitude(googleLocationDto.getLatitude());
                        building.setLongitude(googleLocationDto.getLongitude());
                    }
                }
                String[] splitDays = bargainItemDto.getDays().split(OpenApiConstants.DELETEMETER_DATE);
                int startDay = Integer.parseInt(splitDays[0]);
                int endDay = Integer.parseInt(splitDays[1]);
                for (int i = startDay; i <= endDay; i++) {
                    Date date = new GregorianCalendar(bargainItemDto.getYear(), bargainItemDto.getMonthly() - 1, i).getTime();
                    BargainDate bargainDate = new BargainDate.Builder().building(building).date(date)
                            .price(bargainItemDto.getDealPrice().trim())
                            .build();
                    building.getBargainDates().add(bargainDate);
                }
                log.warn("insert building trade info => {}", building.toString());
                buildingRepository.save(building);
                return;
            }
            // 전월세의 경우
            CharterWithRentItemDto charterWithRentItemDto = (CharterWithRentItemDto) item;
            int city = Integer.parseInt(charterWithRentItemDto.getRegionCode().substring(0, 2));
            int groop = Integer.parseInt(charterWithRentItemDto.getRegionCode().substring(2));
            Building building = buildingRepository.findByCityAndGroopAndBuildingNumAndFloor(city, groop, charterWithRentItemDto.getBuildingNum(), charterWithRentItemDto.getFloor());
            if (building == null) {
                building = new Building.Builder().city(city).groop(groop).dong(charterWithRentItemDto.getDong())
                        .name(charterWithRentItemDto.getName()).area(charterWithRentItemDto.getArea()).floor(charterWithRentItemDto.getFloor()).type(buildingType)
                        .buildingNum(charterWithRentItemDto.getBuildingNum()).constructYear(String.valueOf(charterWithRentItemDto.getConstructYear()))
                        .bargainDates(new HashSet<BargainDate>())
                        .charterDates(new HashSet<CharterDate>())
                        .rentDates(new HashSet<RentDate>())
                        .build();
                String address = charterWithRentItemDto.getDong() + charterWithRentItemDto.getName();
                GoogleLocationDto googleLocationDto = googleLocationApiService.googleLocationApiCall(address.replaceAll(" ",""));
                if (googleLocationDto != null){
                    building.setLatitude(googleLocationDto.getLatitude());
                    building.setLongitude(googleLocationDto.getLongitude());
                }
            }
            log.warn("building create => {}", building.toString());
            String[] splitDays = charterWithRentItemDto.getDays().split(OpenApiConstants.DELETEMETER_DATE);
            int startDay = Integer.parseInt(splitDays[0]);
            int endDay = Integer.parseInt(splitDays[1]);
            log.warn("start day => {}, end day => {}", startDay, endDay);
            if (Integer.parseInt(charterWithRentItemDto.getMonthlyPrice().trim()) != 0) {
                log.warn("월세!!!");
                // 월세
                for (int i = startDay; i <= endDay; i++) {
                    Date date = new GregorianCalendar(charterWithRentItemDto.getYear(), charterWithRentItemDto.getMonthly() - 1, i).getTime();
                    RentDate rentDate = new RentDate.Builder().building(building).date(date)
                            .guaranteePrice(charterWithRentItemDto.getGuaranteePrice().trim())
                            .monthlyPrice(charterWithRentItemDto.getMonthlyPrice().trim())
                            .build();
                    log.warn("rentDate => {}", rentDate.toString());
                    building.getRentDates().add(rentDate);
                }
                log.warn("insert building trade info => {}", building.toString());
                buildingRepository.save(building);
                return;
            }
            // 전세
            log.warn("전세!!!");
            log.warn("building.getCharterDates() => {}", building.getCharterDates());
            for (int i = startDay; i <= endDay; i++) {
                Date date = new GregorianCalendar(charterWithRentItemDto.getYear(), charterWithRentItemDto.getMonthly() - 1, i).getTime();
                CharterDate charterDate = new CharterDate.Builder().building(building).date(date)
                        .price(charterWithRentItemDto.getGuaranteePrice().trim())
                        .build();
                log.warn("charterDate => {}", charterDate.toString());
                building.getCharterDates().add(charterDate);
            }
            log.warn("insert building trade info => {}", building.toString());
            buildingRepository.save(building);
        });
        buildingRepository.flush();
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // 두개 List 의 변경분만 추출.!!!
//        newDataList.removeAll(oldDataList);
        log.warn("insert DataList => {}", newDataList);
        batchInsertData(newDataList);
        return RepeatStatus.FINISHED;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.warn("init diff tasklet bean => {}", this.getClass().getName());
    }
}
