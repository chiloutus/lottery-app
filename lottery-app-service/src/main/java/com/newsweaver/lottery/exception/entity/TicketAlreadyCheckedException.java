package com.newsweaver.lottery.exception.entity;

import org.springframework.http.HttpStatus;

/**
 * Created by gary on 09/10/2016.
 */
public class TicketAlreadyCheckedException extends AbstractLotteryAppException {
    public TicketAlreadyCheckedException(String errorDetail) {
        super(errorDetail);
    }

    public TicketAlreadyCheckedException(String error, String errorDetail) {
        super(error, errorDetail);
    }

    public TicketAlreadyCheckedException(String errorCode, String error, String errorDetail) {
        super(errorCode, error, errorDetail);
    }

    @Override
    public int getHttpStatus() {
        return HttpStatus.UNAUTHORIZED.value();
    }
}
