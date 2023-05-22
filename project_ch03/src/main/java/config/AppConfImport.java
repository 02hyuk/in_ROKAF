package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import spring.MemberDao;
import spring.MemberPrinter;

// main 메소드에서 쓸 스프링 컨테이너의 설정 클래스 1
@Configuration
@Import(AppConf2.class) // 컨테이너 생성시 AppConf2의 설정까지 포함하겠다는 뜻
public class AppConfImport {
    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }
    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }
}