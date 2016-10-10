package com.newsweaver.lottery.exception.entity;


import com.newsweaver.lottery.util.StringUtils;

/**
 * Created by gary on 08/10/2016.
 */
public abstract class AbstractLotteryAppException {

    private String error;

    private String errorDetail;

    /**
     * If left as min value (unset), getErrorCode defaults to the same value as the HTTP status specified in subclasses
     */
    private String errorCode;

    public AbstractLotteryAppException() {}

    /**
     * One-argument constructor -- in this case the error property will be generated from the class name.
     * For example, if the class name is ItemNotFoundExceptionEntity, then the error property will be
     * "Item Not Found".
     */
    public AbstractLotteryAppException(String errorDetail) {
        this(null, null, errorDetail);
    }

    /**
     * If the input error string is empty/null, the error property will be generated from the class name.
     * For example, if the class name is ItemNotFoundExceptionEntity, then the error property will be
     * "Item Not Found".
     */
    public AbstractLotteryAppException(String error, String errorDetail) {
        this(null, error, errorDetail);
    }

    public AbstractLotteryAppException(String errorCode, String error, String errorDetail) {
        this.errorCode = errorCode;
        this.error = error;
        this.errorDetail = errorDetail;
    }

    public abstract int getHttpStatus();

    public String getError() {
        return org.apache.commons.lang3.StringUtils.isBlank(error) ? convertClassnameToError() : error;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public String getErrorCode() {
        return errorCode == null ? String.valueOf(getHttpStatus()) : errorCode;
    }

    private String convertClassnameToError() {
        return StringUtils.splitCamelCase(this.getClass().getSimpleName().replace("Exception", ""));
    }
}
