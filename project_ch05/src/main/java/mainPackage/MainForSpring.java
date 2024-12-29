package mainPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.ChangePasswordService;
import spring.DuplicateMemberException;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberNotFoundException;
import spring.MemberRegisterService;
import spring.RegisterRequest;
import spring.VersionPrinter;
import spring.WrongIdPasswordException;

public class MainForSpring {
    private static ApplicationContext ctx = null;
    
    public static void main(String[] args) throws IOException {
        
        // 각 설정 클래스 AppCtx1, AppCtx2의 정보를 기반으로 스프링 컨테이너 생성하여 각각에 정의된 빈 초기화 및 관리
        ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        while(true) {
            String command = reader.readLine();
            if(command == null) break;

            System.out.println("------------------------------------------------");
            // 명령어: exit, new, change 3개
            if(command.equalsIgnoreCase("exit")) {
                System.out.println("종료합니다.");
                break;
            }
            if(command.startsWith("new ")) {
                processNewCommand(command.split(" "));
                continue;
            } else if(command.startsWith("change ")) {
                processChangeCommand(command.split(" "));
                continue;
            } else if(command.equals("list")) {
                processListCommand();
                continue;
            } else if(command.startsWith("info ")) {
                processInfoCommand(command.split(" "));
                continue;
            } else if(command.equals("version")) {
                processVersionCommand();
                continue;
            }
            // if문에 모두 해당하지 않는 경우 - 명령을 잘못 입력한 것이므로 도움말 출력
            printHelp();
        }
        reader.close();
    }
    
    private static void processNewCommand(String[] arg) {
        // new 명령이 유효하게 적혔는지(5개 단어인지) 확인
        if(arg.length != 5) {
            printHelp();
            return;
        }
        MemberRegisterService regSvc = 
            ctx.getBean("memberRegSvc", MemberRegisterService.class);
        // 회원가입 요청 정보를 저장할 객체 req 생성
        RegisterRequest req = new RegisterRequest();
        req.setEmail(arg[1]);
        req.setName(arg[2]);
        req.setPassword(arg[3]);
        req.setConfirmPassword(arg[4]);
        // 비번, 비번확인 동일한지 확인(req 선에서)
        if(!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("암호와 확인이 일치하지 않습니다.");
            return;
        }
        // reqSvc로 회원가입 진행
        try {
            regSvc.regist(req);
            System.out.println("등록했습니다.\n");
        } catch(DuplicateMemberException e) {
            System.out.println("이미 존재하는 이메일입니다.\n");
        }
    }
    private static void processChangeCommand(String[] arg) {
        // change 명령이 유효하게 적혔는지(4개 단어인지) 확인
        if(arg.length != 4) {
            printHelp();
            return;
        }
        ChangePasswordService changePwdSvc = 
            ctx.getBean("changePwdSvc", ChangePasswordService.class);
        try {
            changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
            System.out.println("암호를 변경했습니다.\n");
        } catch(MemberNotFoundException e) {
            System.out.println("존재하지 않는 이메일입니다.\n");
        } catch(WrongIdPasswordException e) {
            System.out.println("이메일과 암호가 일치하지 않습니다.\n");
        }
    }
    private static void processListCommand() {
        MemberListPrinter listPrinter = 
            ctx.getBean("listPrinter", MemberListPrinter.class);
        listPrinter.printAll();
    }
    private static void processInfoCommand(String[] arg) {
        // info 명령이 유효하게 적혔는지(2개 단어인지) 확인
        if(arg.length != 2) {
            printHelp();
            return;
        }
        MemberInfoPrinter infoPrinter = 
            ctx.getBean("infoPrinter", MemberInfoPrinter.class);
        infoPrinter.printMemberInfo(arg[1]);
    }
    private static void processVersionCommand() {
        VersionPrinter versionPrinter = 
            ctx.getBean("versionPrinter", VersionPrinter.class);
        versionPrinter.print();
    }
    private static void printHelp() {
        System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요.");
        System.out.println("<명령어 사용법>");
        System.out.println("1. new 이메일 이름 암호 암호확인");
        System.out.println("2. change 이메일 현재암호 변경암호\n");
    }
}