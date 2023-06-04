package spring;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class MemberPrinter {
    private DateTimeFormatter dateTimeFormatter;
    
    public MemberPrinter() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    }
    
    public void print(Member member) {
        if(dateTimeFormatter == null) {
            System.out.printf(
                "회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%tF\n",
                member.getId(), member.getEmail(), 
                member.getName(), member.getRegisterDateTime()
            );
        } else {
            System.out.printf(
                "회원 정보: 아이디=%d, 이메일=%s, 이름=%s, 등록일=%s\n",
                member.getId(), member.getEmail(), 
                member.getName(),
                dateTimeFormatter.format(member.getRegisterDateTime())
            );
        }
    }
    // @Autowired 어노테이션만 썼을 때 DateTimeFormatter 타입 빈이 없다면 예외 발생
    
    /*
    // 첫 번째 해결 방법
    // 빈이 없으면 그냥 주입 자체를 안함
    // 그래서 print 메소드 실행시 년 월 일 출력됨
    @Autowired(required = false)
    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
    */
    
    /* 
    // 두 번째 방법
    // 빈이 없으면 null 값을 가진 Optional을 인자로 전달하고
    // 있으면 빈을 값으로 가진 Optional을 인자로 전달해 세터 메소드 호출
    @Autowired
    public void setDateTimeFormatter(Optional<DateTimeFormatter> formatterOpt) {
        if(formatterOpt.isPresent()) { // Optional 객체에 저장된 값이 null이 아니면 true
            this.dateTimeFormatter = formatterOpt.get(); // Optional 객체에 저장된 값을 리턴
        } else {
            this.dateTimeFormatter = null;
        }
    }
    */
    
    
    // 세 번째 방법
    // 있으면 빈을, 없으면 null을 인자로 전달해 세터 메소드 호출
    @Autowired
    public void setDateTimeFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
}