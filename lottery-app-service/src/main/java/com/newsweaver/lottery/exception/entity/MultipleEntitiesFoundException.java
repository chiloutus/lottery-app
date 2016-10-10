package com.newsweaver.lottery.exception.entity;

import org.springframework.http.HttpStatus;

/**
 * Created by gary on 08/10/2016.
 */
public class MultipleEntitiesFoundException extends AbstractLotteryAppException {

    public MultipleEntitiesFoundException(String errorDetail) {
        super(errorDetail);
    }

    public MultipleEntitiesFoundException(String error, String errorDetail) {
        super(error, errorDetail);
    }

    public MultipleEntitiesFoundException(String errorCode, String error, String errorDetail) {
        super(errorCode, error, errorDetail);
    }

    @Override
    public int getHttpStatus() {
        //Throwing a 500 because we are in a failed state if this happens (it shouldn't through normal use!)
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
