package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import java.util.Collection;

public class MemberListPrinter {
    private MemberDao memberDao;
    private MemberPrinter printer;
    
    // 의존 자동 주입을 위한 기본 생성자 정의
    public MemberListPrinter() {
    }
    public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
        this.memberDao = memberDao;
        this.printer = printer;
    }
    
    // 세터 메소드
    @Autowired
    void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    @Autowired
    // MemberPrinter 타입 빈이 여러 개이므로 특정한 한정자를 가진 빈을 선택해 의존 주입하는게 일반적
    // 하지만 자동 주입 대상에 한정자가 없으면 파라미터의 이름을(만약 세터 메소드가 아닌 필드일 경우 필드 이름을) 한정자로 사용해 빈 선택
    void setMemberPrinter(MemberPrinter summaryPrinter) {
        this.printer = summaryPrinter;
    }
    
    // 모든 회원의 정보 출력
    public void printAll() {
        Collection<Member> members = memberDao.selectAll();
        members.forEach(m -> printer.print(m));
    }
}