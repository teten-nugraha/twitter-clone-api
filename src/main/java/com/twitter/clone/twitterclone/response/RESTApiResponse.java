package com.twitter.clone.twitterclone.response;

import com.twitter.clone.twitterclone.util.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class RESTApiResponse {

    public static ResponseEntity<?> responseSuccess(Object value, String status, String message) {

        if(Status.listSuccessResponse.contains(status)) {
            Map<String, Object> response = new HashMap<>();
            response.put("data", value);
            response.put("message", message);
            response.put("success", true);


            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
        }

        return null;

    }

    public static ResponseEntity<?> responseError (Object value, String status , String message, Object errorCode) {

        if(Status.listErrorResponse.contains(status)) {

            Map<String, Object> response = new HashMap<>();
            response.put("data", value);
            response.put("message", message);
            response.put("error", true);
            response.put("errorCode", (Integer)errorCode);

            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }

        return  null;

    }


}
