package spring;

import annotation.ManualBean;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@ManualBean
public class MemberDao {
    private static long nextId = 0;
    private Map<String, Member> map = new HashMap<>(); // 키: 이메일, 값: Member 객체
    
    // 이메일로 Member 객체 선택
    public Member selectByEmail(String email) {
        return map.get(email);
    }
    // 모든 가입자의 정보 리턴
    public Collection<Member> selectAll() {
        return map.values();
    }
    // 최초 삽입시 id값 세팅하고 삽입
    public void insert(Member member) {
        member.setId(++nextId);
        map.put(member.getEmail(), member);
    }
    // 나중에 삽입시(원래 있던 Member 객체를 업데이트) id값은 변경하지 않음
    public void update(Member member) {
        map.put(member.getEmail(), member);
    }
}