package chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main{
    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx = 
            new AnnotationConfigApplicationContext(AppContext.class);
        Greeter g = ctx.getBean("greeter", Greeter.class);
        String msg = g.greet("스프링");
        
        System.out.println("\n- 실행 결과 -\n");
        System.out.println(msg);
        System.out.println();
        
        ctx.close();
    }
}