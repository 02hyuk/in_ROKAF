package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class ChangePasswordService {
    // 의존 주입 대상에 해당 어노테이션을 붙이면 스프링 컨테이너가 알아서
    // 아래 필드에 해당하는 빈을 찾아 할당
    @Autowired
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