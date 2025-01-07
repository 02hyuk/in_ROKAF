import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx = 
            new AnnotationConfigApplicationContext(AppCtx.class);

        Client client1 = ctx.getBean(Client.class);
        Client client2 = ctx.getBean(Client.class);
        System.out.println(client1 == client2);

        client1.send();

        ctx.close();
    }
}