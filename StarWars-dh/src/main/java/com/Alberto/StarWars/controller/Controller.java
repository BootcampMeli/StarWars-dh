package com.Alberto.StarWars.controller;

import com.Alberto.StarWars.DTOs.PersonagemDTO;
import com.Alberto.StarWars.DTOs.ResponseDTO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @RequestMapping("/{nome}")
    public ResponseEntity<ResponseDTO> getPersonagens(@PathVariable String nome){
        // carrega arquivo de perosonagens
        List<PersonagemDTO> basePersonagens = loadPersonagensList();
        List<String> responseList = new ArrayList<>();
        // List<PersonagemDTO> responseList = new ArrayList<>();

        // compara cada personagem da base com o nome fornecido
        for (PersonagemDTO personagemDTOBase : basePersonagens){
                if (personagemDTOBase.getName().toLowerCase().contains(nome)){
                    responseList.add(personagemDTOBase.getName());
                }
        }
        ResponseDTO responseDTO = new ResponseDTO(responseList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    private List<PersonagemDTO> loadPersonagensList() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:starwars_characters.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<PersonagemDTO>> typeReference = new TypeReference<>() {};
        List<PersonagemDTO> personagemDTOList = null;
        try{
            personagemDTOList = objectMapper.readValue(file, typeReference);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personagemDTOList;
    }

}
