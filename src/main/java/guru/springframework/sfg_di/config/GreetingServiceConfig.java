package guru.springframework.sfg_di.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import com.springframework.pets.PetService;
import com.springframework.pets.PetServiceFactory;

import guru.springframework.sfg_di.datasource.FakeDataSource;
import guru.springframework.sfg_di.repositories.EnglishGreetingRepository;
import guru.springframework.sfg_di.repositories.EnglishGreetingRepositoryImpl;
import guru.springframework.sfg_di.services.ConstructorGreetingService;
import guru.springframework.sfg_di.services.I18nEnglishGreetingService;
import guru.springframework.sfg_di.services.I18nSpanishGreetingService;
import guru.springframework.sfg_di.services.PrimaryGreetingService;
import guru.springframework.sfg_di.services.PropertyInjectedGreetingService;
import guru.springframework.sfg_di.services.SetterInjectedGreetingService;

@PropertySource("classpath:datasource.properties")
@ImportResource("classpath:sfg_di-config.xml")
@Configuration
public class GreetingServiceConfig 
{
    
    @Bean
    FakeDataSource FakeDataSource(@Value("${guru.username}")String username,
                                @Value("${guru.password}")String password,
                                @Value("${guru.jdbcurl}")String jdbcurl)
    {
        FakeDataSource fakeDataSource = new FakeDataSource();
        fakeDataSource.setUsername(username);
        fakeDataSource.setPassword(password);
        fakeDataSource.setJdbcurl(jdbcurl);
        return fakeDataSource;
    }
    @Bean
    PetServiceFactory petServiceFactory()
    {
        return new PetServiceFactory();
    }

    @Profile({"dog", "default"})
    @Bean
    PetService dogPetService(PetServiceFactory petServiceFactory)
    {
        return petServiceFactory.getPetService("dog");
    }

    @Profile({"cat"})
    @Bean
    PetService catPetService(PetServiceFactory petServiceFactory)
    {
        return petServiceFactory.getPetService("cat");
    }

    @Profile({"ES"})
    @Bean("i18nService")
    I18nSpanishGreetingService i18nSpanishGreetingService(){
        return new I18nSpanishGreetingService();
    }

    @Bean
    EnglishGreetingRepository englishGreetingRepository(){
        return new EnglishGreetingRepositoryImpl();
    }

    @Profile({"EN","default"})
    @Bean
    I18nEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository){
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }

    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService(){
        return new PrimaryGreetingService();
    }

    //@Bean
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
