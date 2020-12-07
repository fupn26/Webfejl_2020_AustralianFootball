package hu.unideb.fupn26.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MatchResponseDto extends MatchFullRequestDto {

    private String id;
}
