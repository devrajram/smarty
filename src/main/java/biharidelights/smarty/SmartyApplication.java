package biharidelights.smarty;

import biharidelights.smarty.message.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class SmartyApplication {//implements CommandLineRunner {

    static int counter = 0;

    public static void main(String[] args) {
        SpringApplication.run(SmartyApplication.class, args);
    }

    @Autowired
    private Sender sender;

    //@Override
    @Scheduled(fixedRate = 1000)
    public void run() throws Exception {
        sender.send("My Message : " + counter++);
    }
}
