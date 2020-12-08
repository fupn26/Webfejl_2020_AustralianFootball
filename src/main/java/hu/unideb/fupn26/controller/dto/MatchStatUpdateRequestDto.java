package hu.unideb.fupn26.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MatchStatUpdateRequestDto {

    private Integer teamId;
    private Integer goals;
    private Integer behinds;

    private Integer kicks;
    private Integer marks;
    private Integer handballs;
    private Integer disposals;
    private Integer hitOuts;
    private Integer tackles;
    private Integer rebound50s;
    private Integer inside50s;
    private Integer clearances;
    private Integer clangers;
    private Integer freeKicksFor;
    private Integer freeKicksAgainst;
    private Integer brownlowVotes;
    private Integer contestedPossessions;
    private Integer uncontestedPossessions;
    private Integer contestedMarks;
    private Integer marksInside50;
    private Float onePercenters;
    private Integer bounces;
    private Integer goalAssist;
    private Float percentageOfGamePlayed;
}
