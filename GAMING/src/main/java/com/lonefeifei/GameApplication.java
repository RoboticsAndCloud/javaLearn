package com.lonefeifei;

import com.lonefeifei.custom.CommandLineRunner.StartupRunner;
import com.lonefeifei.domain.entity.SysRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintStream;
import java.net.InetAddress;

@SpringBootApplication
@EnableFeignClients
//@EnableScheduling
public class GameApplication {

    private static final Logger LOG = LoggerFactory.getLogger(GameApplication.class);
    public static final String SERVER_PORT = "server.port";

    public static void main(String[] args) {
//        SpringApplication.run(GameApplication.class, args);
        SpringApplication application = new SpringApplication(GameApplication.class);
        //application.setShowBanner(false);
        //		application.run(args);
//        application.setBannerMode(Banner.Mode.OFF);

        // @formatter:off
        //SpringApplication application = new SpringApplication(Application.class);
//        SimpleCommandLinePropertySource propertySource = new SimpleCommandLinePropertySource(args);
       // setDefaultProfiles(application, propertySource);
//        application.setBanner(new Banner() {@Override
//    public void printBanner(Environment environment, Class<?> aClass, PrintStream printStream) {
//            System.out.println("how are u !!!");
//    }});
//        application.setBanner(new ResourceBanner(new ClassPathResource("banner.txt")));
		ConfigurableApplicationContext run = application.run(args);
        Environment env = run.getEnvironment();
		try {
        LOG.info("Application running information:\n"
                        + "------------------------------------------------------------------\n"
                        + "Address: {}\n"
                        + "Port: {}\n"
                        + "------------------------------------------------------------------",
                InetAddress.getLocalHost().getHostAddress(), env.getProperty(SERVER_PORT));
			} catch (Exception e) {

		}

	}
}
