package com.mysite.kws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 스프링부트 프로그램의 시작을 담당하는 파일
 * @SpringBootApplication 어노테이션을 통해 스프링부트 프로그램의 시작을 알림
 *  - @Configuration, @EnableAutoConfiguration, @ComponentScan 어노테이션을 포함
 *  - @Configuration: 스프링부트의 설정파일임을 알림
 *  - @EnableAutoConfiguration: 스프링부트의 자동설정을 활성화
 *  - @ComponentScan: 스프링부트에서 빈을 찾을 위치를 지정
 */
@SpringBootApplication
public class KwsApplication {

    public static void main(String[] args) {
        SpringApplication.run(KwsApplication.class, args);
    }

}
