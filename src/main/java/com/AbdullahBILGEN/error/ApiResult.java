package com.AbdullahBILGEN.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.Map;

// LOMBOK
@Data

// JSON: Eğer bu fieldlarda null varsa backentte girmesin
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult {

    // Pırasa Vali MESC
    private String path;
    private Map<String,String> validationErrors;
    private String message;
    private String error;
    private int status;
    private Date systemDate;

    // Parametresiz Constructor
    public ApiResult() {
    }

    // Parametreli Constructor
    public ApiResult(String path, String message, int status) {
        this.path = path;
        this.message = message;
        this.status = status;
    }
}
