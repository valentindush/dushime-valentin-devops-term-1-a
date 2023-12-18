package com.example.dushimevalentintermonea.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private Boolean success;
    private String message;
    private Object calcResponse;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(Boolean success, Object calcResponse) {
        this.success = success;
        this.calcResponse = calcResponse;
    }

    public static ApiResponse success(Object calcResponse) {
        return new ApiResponse(true, calcResponse);
    }

    public static ApiResponse fail(Object calcResponse) {
        return new ApiResponse(false, calcResponse);
    }

    public static ApiResponse success(String message) {
        return new ApiResponse(true, message);
    }

    public static ApiResponse fail(String message) {
        return new ApiResponse(false, message);
    }
}