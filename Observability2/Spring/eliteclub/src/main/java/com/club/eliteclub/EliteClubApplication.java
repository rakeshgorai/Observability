package com.club.eliteclub;

import com.club.eliteclub.model.ClubDTO;
import com.club.eliteclub.service.EliteClubService;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.boot.builder.SpringApplicationBuilder;
// import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.*;

// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@SpringBootApplication
@EnableJpaRepositories(basePackageClasses  = {com.club.eliteclub.dao.EliteClubRepository.class})
@EntityScan(basePackageClasses = {com.club.eliteclub.entity.EliteClub.class})
// @Import(SwaggerConfig.class)
public class EliteClubApplication implements ApplicationRunner,WebMvcConfigurer {
//public class EliteClubApplication extends SpringBootServletInitializer implements ApplicationRunner{
//public class EliteClubApplication extends WebSecurityConfigurerAdapter implements ApplicationRunner {
    private static final Logger LOG = LoggerFactory.getLogger(EliteClubApplication.class);

    @Autowired
    private EliteClubService eliteClubService;

  @Autowired
  private Environment env;

    public static void main(String[] args) {
      SpringApplication.run(EliteClubApplication.class, args);




    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        eliteClubService.addClub("Billionaire", "Environmentalist", "Poker");
        List<ClubDTO> clubs = eliteClubService.searchClub("Bi");
        LOG.info("Search Result : {}", clubs);
        LOG.info("spring.security.user.password===", env.getProperty("spring.security.user.password"));
       
    }
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(EliteClubApplication.class);
//    }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http
    //             .antMatcher("/**")
    //             .authorizeRequests()
    //             .antMatchers("/", "/**","/login**", "/webjars/**", "/error**")
    //             .permitAll()
    //             .anyRequest()
    //             .authenticated()
    //             .and().logout().logoutSuccessUrl("/").permitAll()
    //             .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    // }

    
}
class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}

@Configuration
 class ObservedAspectConfiguration {

    @Bean
    public ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
        return new ObservedAspect(observationRegistry);
    }
}




