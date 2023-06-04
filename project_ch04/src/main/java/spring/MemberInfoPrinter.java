package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoPrinter {
    private MemberDao memberDao;
    private MemberPrinter printer;
    
    // 이메일에 해당하는 회원 정보 출력
    public void printMemberInfo(String email) {
        Member member = memberDao.selectByEmail(email);
        if(member == null) {
            System.out.println("데이터 없음\n");
            return;
        }
        printer.print(member);
        System.out.println();
    }
    // 의존 대상 객체에 대한 세터 메소드에 @Autowired 어노테이션을 붙이면
    // 스프링 컨테이너가 세터 메소드의 파라미터의 타입에 해당하는 빈을 찾아
    // 세터 메소드의 인자로 주입하여 호출
    @Autowired
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    @Autowired
    public void setPrinter(MemberPrinter printer) {
        this.printer = printer;
    }
}