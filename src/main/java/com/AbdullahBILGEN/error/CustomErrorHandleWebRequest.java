package com.AbdullahBILGEN.error;

import com.AbdullahBILGEN.assist.FrontendUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LOMBOK
@RequiredArgsConstructor

// API
@RestController
@CrossOrigin(origins = FrontendUrl.REACT_URL)
public class CustomErrorHandleWebRequest implements ErrorController {

    // ErrorController
    // ErrorAttributes
    // WebRequest

    private ApiResult apiResult;
    private String path;
    private String message;
    private int status;
    private Map<String,String> validationErrors;

    /*
    1.YOL (Field Injection)
    @Autowired
    private ErrorAttributes errorAttributes;
    */

    /*
    2.YOL (Constructor Injection)
    private final ErrorAttributes errorAttributes;
    @Autowired
    public CustomErrorHandleWebRequest(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }
    */

    //3.YOL (Constructor Lombok Injection)
    private final ErrorAttributes errorAttributes;

    // http://localhost:4444/error
    @RequestMapping("/error")
    public ApiResult springMyHandleErrorMethod(WebRequest webRequest){
        //Spring >=2.3
        Map<String,Object> attributes=errorAttributes.getErrorAttributes(
                webRequest,
                ErrorAttributeOptions.of(
                        ErrorAttributeOptions.Include.MESSAGE,
                        ErrorAttributeOptions.Include.BINDING_ERRORS
                )
        ); //end attributes

        // Spring'ten gelen verileri almak
        status= (int) attributes.get("status");
        message= (String) attributes.get("message");
        path= (String) attributes.get("path");
        // public ApiResult(String path, String message, int status) {}
        apiResult=new ApiResult(path,message,status);

        // Eğer Spring'ten gelen bir hata varsa ise
        if(attributes.containsKey("errors")){
            List<FieldError> fieldErrorList= (List<FieldError>) attributes.get("errors");
            // HashMap oluştur
            validationErrors=new HashMap<>();
            // bütün hataları for each döngüsünde kullan
            for(FieldError temp :fieldErrorList){
                validationErrors.put(temp.getField(),temp.getDefaultMessage());
            }
            apiResult.setValidationErrors(validationErrors);
        }
        return apiResult;
    }  // end  springMyHandleErrorMethod
} // end class
