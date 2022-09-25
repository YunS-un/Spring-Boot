package cn.yun.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND,"/4xxPage");
        ErrorPage error400 = new ErrorPage(HttpStatus.BAD_REQUEST,"/4xxPage");
        ErrorPage error401 = new ErrorPage(HttpStatus.UNAUTHORIZED,"/4xxPage");
        ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/5xxPage");
        registry.addErrorPages(error400,error401,error404,error500);
    }
}
