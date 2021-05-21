package com.mrcruz.algalogapi.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class StandartError {

    private Integer status;
    private String title;
    private LocalDateTime timestamp;
    private String path;
    private List<Field> fields;

    @Getter
    @AllArgsConstructor
    public static class Field{
        private String name;
        private String message;
    }
}
