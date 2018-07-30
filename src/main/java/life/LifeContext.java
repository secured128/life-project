package life;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Created by amnona on 6/9/2016.
 */
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@Configuration
public class LifeContext implements ApplicationContextAware {

    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    @Bean
    AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LifeContext.context = applicationContext;
    }


}
