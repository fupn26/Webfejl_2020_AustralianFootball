package hu.unideb.fupn26.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="match_stats", schema="AustralianFootball")
public class MatchStatEntity {

    @EmbeddedId
    private MatchStatId id;

    @ManyToOne
    @JoinColumn(name = "tid")
    private TeamEntity team;

    @Column(name = "loc")
    private String location;

    @Column(name = "ki")
    private int kicks;

    @Column(name = "mk")
    private int marks;

    @Column(name = "hb")
    private int handballs;

    @Column(name = "di")
    private int disposals;

    @Column(name = "gl")
    private int goals;

    @Column(name = "bh")
    private int behinds;

    @Column(name = "ho")
    private int hitOuts;

    @Column(name = "tk")
    private int tackles;

    @Column(name = "rb")
    private int rebound50s;

    @Column(name = "if")
    private int inside50s;

    @Column(name = "cl")
    private int clearances;

    @Column(name = "cg")
    private int clangers;

    @Column(name = "ff")
    private int freeKicksFor;

    @Column(name = "fa")
    private int freeKicksAgainst;

    @Column(name = "br")
    private int brownlowVotes;

    @Column(name = "cp")
    private int contestedPossessions;

    @Column(name = "up")
    private int uncontestedPossessions;

    @Column(name = "cm")
    private int contestedMarks;

    @Column(name = "mi")
    private int marksInside50;

    @Column(name = "1p")
    private float onePercenters;

    @Column(name = "bo")
    private int bounces;

    @Column(name = "ga")
    private int goalAssist;

    @Column(name = "pp")
    private float percentageOfGamePlayed;
}
