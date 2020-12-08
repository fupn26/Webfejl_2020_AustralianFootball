package hu.unideb.fupn26.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MatchStatMinimalDto {

    private String matchId;
    private Integer playerId;
    private Integer teamId;
    private Integer goals;
    private Integer behinds;
}
