package com.skuniv.bigdata.batch.items;

import com.google.gson.Gson;
import com.skuniv.bigdata.domain.dto.YamlDto;
import com.skuniv.bigdata.domain.dto.open_api.BargainBodyDto;
import com.skuniv.bigdata.domain.dto.open_api.BargainItemDto;
import com.skuniv.bigdata.domain.dto.open_api.CharterWithRentBodyDto;
import com.skuniv.bigdata.domain.dto.open_api.CharterWithRentItemDto;
import com.skuniv.bigdata.util.OpenApiConstants;
import lombok.RequiredArgsConstructor;
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
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
@StepScope
@Import(YamlDto.class)
@RequiredArgsConstructor
public class ExtractDiffDataTasklet implements Tasklet, StepExecutionListener {
    private static final Gson gson = new Gson();
    private final YamlDto yamlDto;

    private String fileName;
    private String dealType;
    private List oldDataList;
    private List newDataList;

    private void loadDataList(BufferedReader br, List list) throws IOException {
        String line = null;
        if (StringUtils.equals(dealType, OpenApiConstants.BARGAIN_NUM)) {
            while ((line = br.readLine()) != null) {
                list.add(gson.fromJson(line, BargainBodyDto.class));
            }
            return;
        }
        while ((line = br.readLine()) != null) {
            list.add(gson.fromJson(line, CharterWithRentBodyDto.class));
        }
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext ctx = stepExecution.getExecutionContext();
        fileName = (String) ctx.get(OpenApiConstants.API_KIND);
        dealType = (String) ctx.get(OpenApiConstants.DEAL_TYPE);

        oldDataList = new ArrayList<CharterWithRentItemDto>();
        newDataList = new ArrayList<CharterWithRentItemDto>();
        if (StringUtils.equals(dealType, OpenApiConstants.BARGAIN_NUM)) {
            oldDataList = new ArrayList<BargainItemDto>();
            newDataList = new ArrayList<BargainItemDto>();
        }

        String newFileFullPath = yamlDto.getFilePath() + OpenApiConstants.FILE_DELEMETER + fileName;
        String oldFileFullPath = yamlDto.getFilePath() + OpenApiConstants.FILE_DELEMETER + fileName + OpenApiConstants.OLD;

        try (
                BufferedReader oldBr = new BufferedReader(new FileReader(new File(oldFileFullPath)));
                BufferedReader newBr = new BufferedReader(new FileReader(new File(newFileFullPath)));
        ) {
            loadDataList(oldBr, oldDataList);
            loadDataList(newBr, newDataList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // 두개 List 의 변경분만 추출.!!!
        newDataList.removeAll(oldDataList);

        for (Object o : newDataList) {
            // db insert
        }
        return RepeatStatus.FINISHED;
    }
}
