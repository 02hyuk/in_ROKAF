package config;

import annotation.ManualBean;
import annotation.NoProduct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.VersionPrinter;

// main 메소드에서 쓸 스프링 컨테이너의 설정 클래스
@Configuration
@ComponentScan(basePackages = {"spring"}, excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = MemberDao.class))
public class AppCtx {
    // 제외 필터 조건에 MemberDao가 해당되므로 컴포넌트 스캔이 안됨
    // 빈 수동 등록해야 함

    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }
    
    // 아래 빈들은 위의 두 빈에 의존
    @Bean
    public MemberRegisterService memberRegSvc() {
        // 인자가 없는 기본 생성자를 호출해도
        // 스프링 컨테이너의 의존 자동 주입으로 인해
        // MemberDao 타입 빈이 들어있는 채로 빈 생성
        return new MemberRegisterService();
    }
    @Bean
    public ChangePasswordService changePwdSvc() {
        ChangePasswordService pwdSvc = new ChangePasswordService();
        // 이 빈은 클래스 내의 의존 대상 객체가 스프링에 의해 자동 주입되어
        // 세터 메소드가 필요 없음
        //pwdSvc.setMemberDao(memberDao);
        return pwdSvc;
    }

    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }
}