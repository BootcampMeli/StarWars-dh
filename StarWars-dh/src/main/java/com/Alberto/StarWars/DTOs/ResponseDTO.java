package com.Alberto.StarWars.DTOs;

import java.util.List;

public class ResponseDTO {
    public List<String> personagemDTOList;

    public ResponseDTO(List<String> personagemDTOList) {
        this.personagemDTOList = personagemDTOList;
    }
}
