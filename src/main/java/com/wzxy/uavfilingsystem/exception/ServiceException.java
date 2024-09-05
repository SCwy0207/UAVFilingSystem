package com.wzxy.uavfilingsystem.exception;

import com.wzxy.uavfilingsystem.common.ResultCode;

public class ServiceException extends RuntimeException {
    private final ResultCode resultCode;

    public ServiceException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public ServiceException(ResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    // 可以添加更多构造方法和功能，视需求而定
}
