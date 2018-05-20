package ua.kiev.prog;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

enum LoggerType {Console, File};

public class Main {
    static LoggerType loggerType = LoggerType.File;
    static boolean usePreprocessors = true;

    public static void main(String[] args) {
        // case #1
        System.out.println(">>> Sample #1:");

        LoggerAPI api = null;
        if (loggerType == LoggerType.Console)
            api = new ConsoleLoggerAPI();
        else if (loggerType == LoggerType.File)
            api = new FileLoggerAPI("log.txt");

        try {
            api.open();
            try {
                // optional functionality
                if (usePreprocessors) {
                    Preprocessor preprocessor = new DatePreprocessor();
                    api.setPreprocessor(preprocessor);
                }
                Notifier notifier = new Notifier(api);
                notifier.sendSms();
            } finally {
                api.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // case #2
        System.out.println(">>> Sample #2:");

        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        try {
            Notifier notifier = ctx.getBean("notifier", Notifier.class);
            notifier.sendSms();
        } finally {
            ctx.close();
        }

        // case #3
        System.out.println(">>> Sample #3:");

        ctx = new ClassPathXmlApplicationContext("/spring-config.xml");
        try {
            Notifier notifier = ctx.getBean("notifier", Notifier.class);
            notifier.sendSms();
        } finally {
            ctx.close();
        }
    }
}
