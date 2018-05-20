package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    /*
    comment out this bean to test the result
    @Bean
    public Preprocessor datePreprocessor() {
    return new DatePreprocessor();
    }
    */

    @Bean(initMethod = "open", destroyMethod = "close")
    public LoggerAPI fileLoggerAPI() {
        return new FileLoggerAPI("log.txt");
    }

    @Bean(name="consoleLogger")
    public LoggerAPI consoleLoggerAPI() {
        return new ConsoleLoggerAPI();
    }

    @Bean
    public Notifier notifier(@Qualifier("fileLoggerAPI") LoggerAPI api) {
        return new Notifier(api);
    }
}
