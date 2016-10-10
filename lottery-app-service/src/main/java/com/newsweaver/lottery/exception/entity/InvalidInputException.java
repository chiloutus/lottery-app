package com.newsweaver.lottery.exception.entity;

import org.springframework.http.HttpStatus;

/**
 * Created by gary on 08/10/2016.
 */
public class InvalidInputException extends AbstractLotteryAppException {

    public InvalidInputException(String errorDetail) {
        super(errorDetail);
    }

    public InvalidInputException(String error, String errorDetail) {
        super(error, errorDetail);
    }

    public InvalidInputException(String errorCode, String error, String errorDetail) {
        super(errorCode, error, errorDetail);
    }

    @Override
    public int getHttpStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY.value();
    }
}
