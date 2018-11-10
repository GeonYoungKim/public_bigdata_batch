package com.skuniv.bigdata;

import org.apache.commons.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Test {
    private static final Log log = (Log) LogFactory.getLog( Test.class );

    public void print(){
        log.info("test");
        log.warn("test");
        log.debug("test");
        log.error("test");
        System.out.println("test");

    }
}
