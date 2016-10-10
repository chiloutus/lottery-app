package com.newsweaver.lottery.exception;

import com.newsweaver.lottery.exception.entity.AbstractLotteryAppException;

/**
 * Created by gary on 08/10/2016.
 */
public class LotteryAppException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private AbstractLotteryAppException ticketAppException;

    public LotteryAppException(AbstractLotteryAppException ticketAppExceptionEntity) {
        this.ticketAppException = ticketAppExceptionEntity;
    }

    public AbstractLotteryAppException getExceptionEntity() {
        return ticketAppException;
    }

}
