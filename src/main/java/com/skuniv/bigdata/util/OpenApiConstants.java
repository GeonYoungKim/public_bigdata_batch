package com.skuniv.bigdata.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public final class OpenApiConstants {
    public static String APART_NUM = "1";
    public static String OFFICETELS_NUM = "2";
    public static String HOUSING_NUM = "3";

    public static String BARGAIN_NUM = "1";
    public static String CHARTER_RENT_NUM = "2";

    public static final String OLD = "_old";
    public static final String FILE_DELEMETER = "/";
    public static final String PARTITION_KEY = "partition_";
    public static final String URL = "url";
    public static final String API_KIND = "apiKind";
    public static final String BUILDING_TYPE = "buildingType";
    public static final String DEAL_TYPE = "dealType";
    public static final String SERVICE_KEY_PARAM = "&serviceKey=";


    @Getter
    @AllArgsConstructor
    public enum OpenApiRequest {
        APART_BARGAIN("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTrade?LAWD_CD=%s&DEAL_YMD=%s", OpenApiConstants.APART_NUM, OpenApiConstants.BARGAIN_NUM),
        APART_CHARTER_RENT("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptRent?LAWD_CD=%s&DEAL_YMD=%s", OpenApiConstants.APART_NUM, OpenApiConstants.CHARTER_RENT_NUM)
//        OFFICETELS_BARGAIN("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcOffiTrade?LAWD_CD=%s&DEAL_YMD=%s", OpenApiConstants.OFFICETELS_NUM, OpenApiConstants.BARGAIN_NUM),
//        OFFICETELS_CHARTER_RENT("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcOffiRent?LAWD_CD=%s&DEAL_YMD=%s", OpenApiConstants.OFFICETELS_NUM, OpenApiConstants.CHARTER_RENT_NUM),
//        MUTILGENERATION_BARGAIN("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHTrade?LAWD_CD=%s&DEAL_YMD=%s", OpenApiConstants.HOUSING_NUM, OpenApiConstants.BARGAIN_NUM),
//        MUTILGENERATION_CHARTER_RENT("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcRHRent?LAWD_CD=%s&DEAL_YMD=%s", OpenApiConstants.HOUSING_NUM, OpenApiConstants.CHARTER_RENT_NUM),
//        SOLE_BARGAIN("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcSHTrade?LAWD_CD=%s&DEAL_YMD=%s", OpenApiConstants.HOUSING_NUM, OpenApiConstants.BARGAIN_NUM),
//        SOLE_CHARTER_RENT("http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcSHRent?LAWD_CD=%s&DEAL_YMD=%s", OpenApiConstants.HOUSING_NUM, OpenApiConstants.CHARTER_RENT_NUM);
        ;

        private String url;
        private String buildingType;
        private String dealType;
    }

    public static final Map<String, String> regionMap = Arrays.stream(new String[][]{
            {"11680", "11"},
            {"11740", "11"},
            {"11305", "11"},
            {"11500", "11"},
            {"11620", "11"},
            {"11215", "11"},
            {"11530", "11"},
            {"11545", "11"},
            {"11350", "11"},
            {"11320", "11"},
            {"11230", "11"},
            {"11590", "11"},
            {"11440", "11"},
            {"11410", "11"},
            {"11650", "11"},
            {"11200", "11"},
            {"11290", "11"},
            {"11710", "11"},
            {"11470", "11"},
            {"11560", "11"},
            {"11170", "11"},
            {"11380", "11"},
            {"11110", "11"},
            {"11140", "11"},
            {"11260", "11"},
            {"26440", "26"},
            {"26410", "26"},
            {"26710", "26"},
            {"26290", "26"},
            {"26170", "26"},
            {"26260", "26"},
            {"26230", "26"},
            {"26320", "26"},
            {"26530", "26"},
            {"26380", "26"},
            {"26140", "26"},
            {"26500", "26"},
            {"26470", "26"},
            {"26200", "26"},
            {"26110", "26"},
            {"26350", "26"},
            {"27200", "27"},
            {"27290", "27"},
            {"27710", "27"},
            {"27140", "27"},
            {"27230", "27"},
            {"27170", "27"},
            {"27260", "27"},
            {"27110", "27"},
            {"28710", "28"},
            {"28245", "28"},
            {"28200", "28"},
            {"28140", "28"},
            {"28177", "28"},
            {"28237", "28"},
            {"28260", "28"},
            {"28185", "28"},
            {"28720", "28"},
            {"28110", "28"},
            {"29200", "29"},
            {"29155", "29"},
            {"29110", "29"},
            {"29170", "29"},
            {"29140", "29"},
            {"30230", "30"},
            {"30110", "30"},
            {"30170", "30"},
            {"30200", "30"},
            {"30140", "30"},
            {"31140", "31"},
            {"31170", "31"},
            {"31200", "31"},
            {"31710", "31"},
            {"31110", "31"},
            {"36110", "36"},
            {"41820", "41"},
            {"41280", "41"},
            {"41281", "41"},
            {"41285", "41"},
            {"41287", "41"},
            {"41290", "41"},
            {"41210", "41"},
            {"41610", "41"},
            {"41310", "41"},
            {"41410", "41"},
            {"41570", "41"},
            {"41360", "41"},
            {"41250", "41"},
            {"41190", "41"},
            {"41130", "41"},
            {"41135", "41"},
            {"41131", "41"},
            {"41133", "41"},
            {"41110", "41"},
            {"41113", "41"},
            {"41117", "41"},
            {"41111", "41"},
            {"41115", "41"},
            {"41390", "41"},
            {"41270", "41"},
            {"41273", "41"},
            {"41271", "41"},
            {"41550", "41"},
            {"41170", "41"},
            {"41173", "41"},
            {"41171", "41"},
            {"41630", "41"},
            {"41830", "41"},
            {"41670", "41"},
            {"41800", "41"},
            {"41370", "41"},
            {"41460", "41"},
            {"41463", "41"},
            {"41465", "41"},
            {"41461", "41"},
            {"41430", "41"},
            {"41150", "41"},
            {"41500", "41"},
            {"41480", "41"},
            {"41220", "41"},
            {"41650", "41"},
            {"41450", "41"},
            {"41590", "41"},
            {"42150", "42"},
            {"42820", "42"},
            {"42170", "42"},
            {"42230", "42"},
            {"42210", "42"},
            {"42800", "42"},
            {"42830", "42"},
            {"42750", "42"},
            {"42130", "42"},
            {"42810", "42"},
            {"42770", "42"},
            {"42780", "42"},
            {"42110", "42"},
            {"42190", "42"},
            {"42760", "42"},
            {"42720", "42"},
            {"42790", "42"},
            {"42730", "42"},
            {"43760", "43"},
            {"43800", "43"},
            {"43720", "43"},
            {"43740", "43"},
            {"43730", "43"},
            {"43770", "43"},
            {"43150", "43"},
            {"43745", "43"},
            {"43750", "43"},
            {"43110", "43"},
            {"43111", "43"},
            {"43112", "43"},
            {"43114", "43"},
            {"43113", "43"},
            {"43130", "43"},
            {"44250", "44"},
            {"44150", "44"},
            {"44710", "44"},
            {"44230", "44"},
            {"44270", "44"},
            {"44180", "44"},
            {"44760", "44"},
            {"44210", "44"},
            {"44770", "44"},
            {"44200", "44"},
            {"44810", "44"},
            {"44130", "44"},
            {"44131", "44"},
            {"44133", "44"},
            {"44790", "44"},
            {"44825", "44"},
            {"44800", "44"},
            {"45790", "45"},
            {"45130", "45"},
            {"45210", "45"},
            {"45190", "45"},
            {"45730", "45"},
            {"45800", "45"},
            {"45770", "45"},
            {"45710", "45"},
            {"45140", "45"},
            {"45740", "45"},
            {"45110", "45"},
            {"45113", "45"},
            {"45111", "45"},
            {"45180", "45"},
            {"45720", "45"},
            {"46810", "46"},
            {"46770", "46"},
            {"46720", "46"},
            {"46230", "46"},
            {"46730", "46"},
            {"46170", "46"},
            {"46710", "46"},
            {"46110", "46"},
            {"46840", "46"},
            {"46780", "46"},
            {"46150", "46"},
            {"46910", "46"},
            {"46130", "46"},
            {"46870", "46"},
            {"46830", "46"},
            {"46890", "46"},
            {"46880", "46"},
            {"46800", "46"},
            {"46900", "46"},
            {"46860", "46"},
            {"46820", "46"},
            {"46790", "46"},
            {"47290", "47"},
            {"47130", "47"},
            {"47830", "47"},
            {"47190", "47"},
            {"47720", "47"},
            {"47150", "47"},
            {"47280", "47"},
            {"47920", "47"},
            {"47250", "47"},
            {"47840", "47"},
            {"47170", "47"},
            {"47770", "47"},
            {"47760", "47"},
            {"47210", "47"},
            {"47230", "47"},
            {"47900", "47"},
            {"47940", "47"},
            {"47930", "47"},
            {"47730", "47"},
            {"47820", "47"},
            {"47750", "47"},
            {"47850", "47"},
            {"47110", "47"},
            {"47111", "47"},
            {"47113", "47"},
            {"48310", "48"},
            {"48880", "48"},
            {"48820", "48"},
            {"48250", "48"},
            {"48840", "48"},
            {"48270", "48"},
            {"48240", "48"},
            {"48860", "48"},
            {"48330", "48"},
            {"48720", "48"},
            {"48170", "48"},
            {"48740", "48"},
            {"48120", "48"},
            {"48125", "48"},
            {"48127", "48"},
            {"48123", "48"},
            {"48121", "48"},
            {"48129", "48"},
            {"48220", "48"},
            {"48850", "48"},
            {"48730", "48"},
            {"48870", "48"},
            {"48890", "48"},
            {"50130", "50"},
            {"50110", "50"}
    }).collect(Collectors.toMap(kv -> kv[0], kv -> kv[1]));
}
