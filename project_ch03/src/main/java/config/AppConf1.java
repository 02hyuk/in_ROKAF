package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.MemberDao;
import spring.MemberPrinter;

// main 메소드에서 쓸 스프링 컨테이너의 설정 클래스 1
@Configuration
public class AppConf1 {
    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }
    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }
}