package biharidelights.smarty;

import biharidelights.smarty.message.Sender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class SmartyApplication {//implements CommandLineRunner {

    static int counter = 0;

    public static void main(String[] args) {
        SpringApplication.run(SmartyApplication.class, args);
    }

    @Autowired
    private Sender sender;

    //@Override
    //@Scheduled(fixedRate = 60000)
    public void run() throws Exception {
        log.info("publishing message.");
        if(sender != null) {
            sender.send("My Message : " + counter++);
        }
    }
}
