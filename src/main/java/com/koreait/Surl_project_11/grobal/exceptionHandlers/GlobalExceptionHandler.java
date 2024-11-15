package com.koreait.Surl_project_11.grobal.exceptionHandlers;

import com.koreait.Surl_project_11.grobal.eceptions.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception ex) {
        log.debug("handleException 1");
        return ex.getMessage();
    }
    @ExceptionHandler(GlobalException.class)
    @ResponseBody
    public String handleException(GlobalException ex) {
        log.debug("handleException 2");
        return ex.getMessage();
    }
}