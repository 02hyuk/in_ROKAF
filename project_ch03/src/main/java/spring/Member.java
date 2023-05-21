package spring;

import java.time.LocalDateTime;

public class Member {
    private long id;
    private String email;
    private String password;
    private String name;
    private LocalDateTime registerDateTime;

    public Member(String email, String password, 
            String name, LocalDateTime regDateTime) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.registerDateTime = regDateTime;
    }
    // id만 세터메소드까지, 나머지 필드는 게터메소드만
    void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }
    // password 필드를 변경하는 메소드(oldPassowrd 틀리면 예외 발생 - 이메일과 원래 암호가 일치해야 암호 변경 가능)
    public void changePassword(String oldPassowrd, String newPassword) {
        if(!password.equals(oldPassowrd)){
            throw new WrongIdPasswordException();
        }
        this.password = newPassword;
    }
}