package guru.springframework.sfg_di.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfg_di.services.ConstructorGreetingService;
import guru.springframework.sfg_di.services.I18nEnglishGreetingService;
import guru.springframework.sfg_di.services.I18nSpanishGreetingService;
import guru.springframework.sfg_di.services.PrimaryGreetingService;
import guru.springframework.sfg_di.services.PropertyInjectedGreetingService;
import guru.springframework.sfg_di.services.SetterInjectedGreetingService;

@Configuration
public class GreetingServiceConfig 
{

    @Profile({"ES"})
    @Bean("i18nService")
    I18nSpanishGreetingService i18nSpanishGreetingService(){
        return new I18nSpanishGreetingService();
    }

    @Profile({"EN","default"})
    I18nEnglishGreetingService i18nService(){
        return new I18nEnglishGreetingService();
    }

    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService(){
        return new PrimaryGreetingService();
    }

    @Bean
    ConstructorGreetingService constructorGreetingService()
    {
        return new ConstructorGreetingService();
    }

    @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService(){
        return new PropertyInjectedGreetingService();
    }
    
    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService(){
        return new SetterInjectedGreetingService();
    }
}
