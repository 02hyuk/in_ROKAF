package main;

import cal.Calculator;
import cal.RecCalculator;
import config.AppCtx;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

        Calculator cal = ctx.getBean("calculator", Calculator.class);
        long res = cal.factorial(10);

        System.out.println("cal.factorial(10) = " + res);
        System.out.println(cal.getClass().getName());

        System.out.println("-----------------");
        cal.factorial(7);
        cal.factorial(7);
        cal.factorial(5);
        cal.factorial(5);

        ctx.close();
    }
}
