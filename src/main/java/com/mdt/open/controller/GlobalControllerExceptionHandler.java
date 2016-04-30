package com.mdt.open.controller;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    void handleBadRequests(HttpServletResponse response) throws IOException {
        logger.error("Please try again with valid params.");
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Please try again with valid params.");
    }

    @ExceptionHandler(InvalidFormatException.class)
    void handleBadRequestsOfInvalidFormat(HttpServletResponse response) throws IOException {
        logger.error("Please try again with valid format.");
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Please try again with valid format.");
    }

    @ExceptionHandler(Exception.class)
    void handleDefaultException(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException{
        response.sendError(HttpStatus.EXPECTATION_FAILED.value(), "Default exception handler.");
    }
}
