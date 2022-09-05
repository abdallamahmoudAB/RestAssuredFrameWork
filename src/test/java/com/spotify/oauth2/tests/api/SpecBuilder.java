package com.spotify.oauth2.tests.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.http.ContentType.JSON;

public class SpecBuilder {
    static String access_token = "BQDGDrv7kQKKtXg8ip-NqRGUCdZhdwKl3KzdI-Xi75scqcbe9WHmmYb4rdlgFUBDnxEhK-lLUGKteiOSbngt7B28cFuXeYbc6iX_MXI-t9J2UnKrg9aUzg7oN9uHDl6OcxW_AbV7cPtkycYJxqgemOsy-wK4WZcJVs0Hgm3FKf_VHZxDCigYMsESPIlos1rQIZfCwXtD87KKY1syXT6esqTacBbZ2UXJgucvlhxqMJbZMMa4BjFSmvCBnrG0";

    public static RequestSpecification getRequestSpec() {
        return new RequestSpecBuilder().
                setBaseUri("https://api.spotify.com").
                setBasePath("/v1").
                addHeader("Authorization", "Bearer " + access_token).
                setContentType(JSON).
                log(LogDetail.ALL).
                build();
    }

    public static ResponseSpecification getResponseSpec() {
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();

    }
}
