package spring;

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
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    public void setPrinter(MemberPrinter printer) {
        this.printer = printer;
    }
}