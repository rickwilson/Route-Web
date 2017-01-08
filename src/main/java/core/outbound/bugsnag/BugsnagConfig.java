package core.outbound.bugsnag;

import com.bugsnag.Bugsnag;
import core.services.EnvVariablesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

@Configuration
public class BugsnagConfig {

    private final EnvVariablesService envVariablesService;

    public BugsnagConfig (EnvVariablesService envVariablesService) {
        Assert.notNull(envVariablesService, "EnvVariablesService must not be null!");
        this.envVariablesService = envVariablesService;
    }

    @Bean
    public Bugsnag bugsnag() {
        Bugsnag bugsnag = new Bugsnag(envVariablesService.getBugsnagApiKey());
        bugsnag.setAppType("WebGeneral");
        bugsnag.setReleaseStage(envVariablesService.getEnvName());
        return bugsnag;
    }
}
