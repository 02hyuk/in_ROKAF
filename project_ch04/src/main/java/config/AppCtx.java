package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberSummaryPrinter;
import spring.MemberRegisterService;
import spring.VersionPrinter;

// main 메소드에서 쓸 스프링 컨테이너의 설정 클래스
@Configuration
public class AppCtx {
    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }
    /*
    빈 중복 문제를 발생시키기 위해 수정 조금 함
    @Bean
    public MemberPrinter memberPrinter() {
        return new MemberPrinter();
    }
    */
    // MemberPrinter 타입 빈이 여러 개 존재
    // -> 자동 주입할 때 충돌하므로 @Qualifier 어노테이션 필요
    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter1() {
        return new MemberPrinter();
    }
    @Bean
    public MemberPrinter memberPrinter2() {
        return new MemberPrinter();
    }
    // MemberSummaryPrinter는 MemberPrinter 타입의 하위 타입이므로
    // MemberPrinter 타입이기도 함
    // 그래서 빈 중복 문제 발생
    @Bean
    public MemberSummaryPrinter summaryPrinter() {
        return new MemberSummaryPrinter();
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
    public MemberListPrinter listPrinter() {
        // 매개변수 생성자를 삭제하고 @Autowired가 적용된 세터 메소드를 추가했음
        // 스프링 컨테이너가 의존 자동 주입을 위해 세터 메소드 자동 호출
        
        // return new MemberListPrinter(memberDao(), memberPrinter());
        
        return new MemberListPrinter();
    }
    @Bean
    public MemberInfoPrinter infoPrinter() {
        MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
        //  listPrinter 빈과 동일한 이유로 매개변수 생성자 필요 없음
        
        /* 
            infoPrinter.setMemberDao(memberDao());
            infoPrinter.setPrinter(memberPrinter()); 
        */
        
        // 아래 코드는 의존 자동 주입 때문에 묻힘(우선순위: 의존 자동 주입 > 명시적 의존 주입)
        infoPrinter.setPrinter(summaryPrinter());
        return infoPrinter;
    }
    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }
}