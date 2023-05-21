package assembler;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

public class Assembler {
    private MemberDao memberDao;
    private MemberRegisterService regSvc;
    private ChangePasswordService pwdSvc;
    
    public Assembler() {
        memberDao = new MemberDao();
        regSvc = new MemberRegisterService(memberDao);
        pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao);
    }
    
    // 필드의 게터메소드
    public MemberDao getMemberDao() {
        return memberDao;
    }
    public MemberRegisterService getMemberRegisterService() {
        return regSvc;
    }
    public ChangePasswordService getChangePasswordService() {
        return pwdSvc;
    }
}