package spring;

import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;

public class MemberRegisterService {
    @Autowired
    private MemberDao memberDao;
    
    // 의존 자동 주입을 위한 기본 생성자 정의
    public MemberRegisterService() {
    }
    public MemberRegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public long regist(RegisterRequest req) {
        Member member = memberDao.selectByEmail(req.getEmail());
        // 이미 존재하는 이메일이면 예외 발생
        if(member != null) {
            throw new DuplicateMemberException("dup email " + req.getEmail());
        }
        Member newMember = new Member(req.getEmail(), 
                req.getPassword(), req.getName(), LocalDateTime.now());
        // insert 작업시 newMember의 id가 설정됨
        memberDao.insert(newMember);
        return newMember.getId();
    }
}