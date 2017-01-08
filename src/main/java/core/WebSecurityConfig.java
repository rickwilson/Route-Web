package core;

import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.AWSXRayRecorderBuilder;
import com.amazonaws.xray.plugins.EC2Plugin;
import com.amazonaws.xray.strategy.sampling.DefaultSamplingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.URL;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
    }

    /*
        ROLES:
            SYSTEM_DEVELOPER
            SYSTEM_ADMIN
            ACCOUNT_ADMIN
            ACCOUNT_USER
            SECRET_VIEWER (This is only for admin to login to account, account owners don't know this is here and can't see in their users page)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/demo/**", "/register/**", "/forgot", "/resetEmail", "/350$786dh4!df|yudr5#8c5468d62*4/**", "/d$621*sdfgs|2357!fgds975$j89!8ad79af/**", "/publicHealth/health/9d!s987dfg!hjf*#56jyu9s5gh5gss|f").permitAll()
            .antMatchers("/admin/**").hasAuthority("SYSTEM_ADMIN")
            .antMatchers("/health/**").hasAuthority("SYSTEM_DEVELOPER")
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .permitAll();
    }

    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();
    }

    static {
        AWSXRayRecorderBuilder builder = AWSXRayRecorderBuilder.standard().withPlugin(new EC2Plugin());
        AWSXRay.setGlobalRecorder(builder.build());
    }
}
