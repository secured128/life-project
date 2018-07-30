package life;


import life.server.HeartBit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HeartBitRunner implements CommandLineRunner {

//    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HeartBitRunner.class);

    @Autowired
    private HeartBit heartBit;

    @Override
    public void run(String... args) throws Exception {
        heartBit.start();
    }
}