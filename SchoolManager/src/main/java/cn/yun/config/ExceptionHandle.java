package cn.yun.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;


@RestControllerAdvice
public class ExceptionHandle {

    private static final String DEFAULT_ERROR = "error/error";

    @ExceptionHandler({Exception.class})
    public ModelAndView exceptionsHan(Exception e){
        ModelAndView modelAndView = new ModelAndView(DEFAULT_ERROR);
        modelAndView.addObject("exce",e.getMessage());
        return modelAndView;
    }
}
