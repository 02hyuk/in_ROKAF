package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.VersionPrinter;

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
    // 의존 주입 방식
    // 1. 생성자 방식
    @Bean
    public MemberRegisterService memberRegSvc() {
        return new MemberRegisterService(memberDao());
    }
    // 2. 세터 메소드 방식
    @Bean
    public ChangePasswordService changePwdSvc() {
        ChangePasswordService pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao());
        return pwdSvc;
    }
    
    // 두 개 이상의 빈에 의존하는 경우도 똑같이 정의
    // 1. 생성자 방식
    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }
    @Bean
    public MemberListPrinter listPrinter() {
        return new MemberListPrinter(memberDao(), memberPrinter());
    }
    // 2. 세터 메서드 방식
    @Bean
    public MemberInfoPrinter infoPrinter() {
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
        infoPrinter.setMemberDao(memberDao());
        infoPrinter.setPrinter(memberPrinter());
        return infoPrinter;
    }
    // 세터 메소드를 통해 빈의 기본타입 필드값 설정
    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }
}