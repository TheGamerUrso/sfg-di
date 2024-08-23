package guru.springframework.sfg_di.controllers;

import guru.springframework.sfg_di.services.PetService;

public class PetController {
    private final PetService petService;

    public PetController(PetService petService){
        this.petService = petService;
    }

    public String whichPetIsTheBest(){
        return petService.getPetType();
    }
}
