package spring;

public class ChangePasswordService {
    private MemberDao memberDao;
    
    public void changePassword(String email, String oldPwd, String newPwd) {
        Member member = memberDao.selectByEmail(email);
        // 존재하지 않는 이메일이면 예외 발생
        if(member == null) {
            throw new MemberNotFoundException();
        }
        member.changePassword(oldPwd, newPwd); // oldPwd 틀릴시 예외 발생
        
        memberDao.update(member);
    }
    // 세터 메소드
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}