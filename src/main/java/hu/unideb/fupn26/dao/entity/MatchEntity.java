package hu.unideb.fupn26.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="matches", schema="AustralianFootball")
public class MatchEntity {
    @Id
    @Column(name = "mid")
    private String id;

    private int season;

    private String round;

    @ManyToOne
    @JoinColumn(name = "tid1")
    private TeamEntity team1;

    @ManyToOne
    @JoinColumn(name = "tid2")
    private TeamEntity team2;

    @Column(name = "tid1_loc")
    private String team1Location;

    @Column(name = "tid2_loc")
    private String team2Location;

    @Column(name = "start_dt")
    private Timestamp startDate;

    private String venue;

    @Column(name = "att")
    private int attendants;

    private int margin;

    @Column(name = "win_score")
    private int winnerScore;

    @Column(name = "win_tid")
    private int winnerTeam;

    @Column(name = "win_loc")
    private String winnerLocation;

    @Column(name = "lose_score")
    private int loserScore;

    @Column(name = "lose_tid")
    private int loserTeam;

    @Column(name = "lose_loc")
    private int loserLocation;

    @Column(name = "h_tid")
    private int homeTeam;

    @Column(name = "h_score")
    private int homeScore;

    @Column(name = "h_q1g")
    private int homeQ1Goals;

    @Column(name = "h_q2g")
    private int homeQ2Goals;

    @Column(name = "h_q3g")
    private int homeQ3Goals;

    @Column(name = "h_q4g")
    private int homeQ4Goals;

    @Column(name = "h_etg")
    private int homeExtraTimeGoals;

    @Column(name = "h_q1b")
    private int homeQ1Behinds;

    @Column(name = "h_q2b")
    private int homeQ2Behinds;

    @Column(name = "h_q3b")
    private int homeQ3Behinds;

    @Column(name = "h_q4b")
    private int homeQ4Behinds;

    @Column(name = "h_etb")
    private int homeExtraTimeBehinds;

    @Column(name = "a_tid")
    private int awayTeam;

    @Column(name = "a_score")
    private int awayScore;

    @Column(name = "a_q1g")
    private int awayQ1Goals;

    @Column(name = "a_q2g")
    private int awayQ2Goals;

    @Column(name = "a_q3g")
    private int awayQ3Goals;

    @Column(name = "a_q4g")
    private int awayQ4Goals;

    @Column(name = "a_etg")
    private int awayExtraTimeGoals;

    @Column(name = "a_q1b")
    private int awayQ1Behinds;

    @Column(name = "a_q2b")
    private int awayQ2Behinds;

    @Column(name = "a_q3b")
    private int awayQ3Behinds;

    @Column(name = "a_q4b")
    private int awayQ4Behinds;

    @Column(name = "a_etb")
    private int awayExtraTimeBehinds;

    @Column(name = "target")
    private int target;
}
