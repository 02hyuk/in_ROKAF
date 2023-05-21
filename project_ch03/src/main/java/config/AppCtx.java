package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

// main 메소드에서 쓸 스프링 컨테이너의 설정 클래스
@Configuration
public class AppCtx {
    // 회원가입 처리에 필요한 클래스를 빈으로 쓰기 위해 정의
    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }
    // memberDao()에 의해 생성된 객체는 스프링이 빈으로 관리
    // 따라서 아래 두 빈은 memberDao 빈에 의존
    @Bean
    public MemberRegisterService memberRegSvc() {
        return new MemberRegisterService(memberDao());
    }
    @Bean
    public ChangePasswordService changePwdSvc() {
        ChangePasswordService pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao());
        return pwdSvc;
    }
}