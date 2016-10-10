package com.newsweaver.lottery.exception.handler;

import com.newsweaver.lottery.exception.LotteryAppException;
import com.newsweaver.lottery.exception.entity.AbstractLotteryAppException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * Created by gary on 08/10/2016.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Custom ExceptionHandler for LotteryAppException
     * @param lotteryAppException The LotteryAppException thrown by the application
     */
    @ExceptionHandler(LotteryAppException.class)
    public ResponseEntity<AbstractLotteryAppException> handleGuestEntityException(LotteryAppException lotteryAppException) {
        AbstractLotteryAppException excEntity = lotteryAppException.getExceptionEntity();
        HttpStatus status = HttpStatus.valueOf(excEntity.getHttpStatus());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(excEntity, responseHeaders, status);
    }
}
