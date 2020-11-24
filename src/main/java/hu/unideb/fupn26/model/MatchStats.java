package hu.unideb.fupn26.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class MatchStats {

    private String matchId;
    private int playerId;
    private int teamId;
    private String location;
    private int kicks;
    private int marks;
    private int handballs;
    private int disposals;
    private int goals;
    private int behinds;
    private int hitOuts;
    private int tackles;
    private int rebound50s;
    private int inside50s;
    private int clearances;
    private int clangers;
    private int freeKicksFor;
    private int freeKicksAgainst;
    private int brownlowVotes;
    private int contestedPossessions;
    private int uncontestedPossessions;
    private int contestedMarks;
    private int marksInside50;
    private float onePercenters;
    private int bounces;
    private int goalAssist;
    private float percentageOfGamePlayed;

}
