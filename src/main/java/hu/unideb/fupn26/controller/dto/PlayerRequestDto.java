package hu.unideb.fupn26.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRequestDto extends PlayerDto{

    private int height;
    private int weight;
}
