package com.newsweaver.lottery.exception.entity;

import org.springframework.http.HttpStatus;

/**
 * Created by gary on 08/10/2016.
 */
public class TicketNotFoundException extends AbstractLotteryAppException {

    public TicketNotFoundException(String errorDetail) {
        super(errorDetail);
    }

    public TicketNotFoundException(String error, String errorDetail) {
        super(error, errorDetail);
    }

    public TicketNotFoundException(String errorCode, String error, String errorDetail) {
        super(errorCode, error, errorDetail);
    }

    @Override
    public int getHttpStatus() {
        return HttpStatus.NOT_FOUND.value();
    }
}
