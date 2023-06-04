package spring;

import org.springframework.beans.factory.annotation.Autowired;
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
    void setMemberPrinter(MemberPrinter printer) {
        this.printer = printer;
    }
    
    // 모든 회원의 정보 출력
    public void printAll() {
        Collection<Member> members = memberDao.selectAll();
        members.forEach(m -> printer.print(m));
    }
}