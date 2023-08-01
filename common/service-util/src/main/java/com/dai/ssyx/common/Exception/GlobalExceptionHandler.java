package com.dai.ssyx.common.Exception;

import com.dai.ssyx.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class) //异常处理器
    @ResponseBody   //返回json数据
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail(null);
    }

    @ExceptionHandler(SsyxException.class)
    @ResponseBody
    public Result error(SsyxException e){
        return Result.fail(null);
    }

}
