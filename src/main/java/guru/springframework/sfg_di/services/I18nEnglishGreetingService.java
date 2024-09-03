package guru.springframework.sfg_di.services;

import guru.springframework.sfg_di.repositories.EnglishGreetingRepository;

//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Service;

//@Profile({"EN","default"})
//@Service("i18nService")
public class I18nEnglishGreetingService implements GreetingService
{
    private final EnglishGreetingRepository englishGreetingRepository;

    
    public I18nEnglishGreetingService(EnglishGreetingRepository englishGreetingRepository) {
        this.englishGreetingRepository = englishGreetingRepository;
    }


    @Override
    public String sayGreeting() {
        return "Hello world - EN";
    }
}
