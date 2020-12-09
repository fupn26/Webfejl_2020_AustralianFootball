package hu.unideb.fupn26.service;

import hu.unideb.fupn26.dao.MatchDao;
import hu.unideb.fupn26.exception.*;
import hu.unideb.fupn26.model.Match;
import hu.unideb.fupn26.model.MatchLocation;
import hu.unideb.fupn26.model.MatchRound;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchServiceImplTest {

    @InjectMocks
    private MatchServiceImpl service;
    @Mock
    private MatchDao dao;

    @Test
    void testGetAllMatch() {

        when(dao.readAll()).thenReturn(getMatches());

        Collection<Match> actual = service.getAllMatch();

        assertThat(getMatches(), is(actual));
    }

    @Test
    void testGetAllMatchByTeamWithTeam1() {

        when(dao.readAll()).thenReturn(getMatches());

        Collection<Match> actual = service.getAllMatchByTeam("Alma");

        assertThat(getMatches(), is(actual));
    }

    @Test
    void testGetAllMatchByTeamWithTeam2() {

        when(dao.readAll()).thenReturn(getMatches());

        Collection<Match> actual = service.getAllMatchByTeam("Körte");

        assertThat(getMatches(), is(actual));
    }

    @Test
    void testGetAllMatchByTeamWithEmptyString() {

        when(dao.readAll()).thenReturn(getMatches());

        Collection<Match> actual = service.getAllMatchByTeam("");

        assertThat(new ArrayList<>(), is(actual));
    }

    @Test
    void testRecordMatch() throws MatchAlreadyExistsException, InvalidMatchArgumentException, UnknownTeamException {

        service.recordMatch(getValidMatch());

        verify(dao, times(1)).createMatch(any());
    }

    @Test
    void testRecordMatchWithSeasonNull() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
           service.recordMatch(getMatchWithSeasonNull());
        });
    }

    @Test
    void testRecordMatchWithRoundNull() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithRoundNull());
        });
    }

    @Test
    void testRecordMatchWithTeam1Null() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithTeam1Null());
        });
    }

    @Test
    void testRecordMatchWithTeam2Null() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithTeam2Null());
        });
    }

    @Test
    void testRecordMatchWithTeam1LocNull() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithTeam1LocNull());
        });
    }

    @Test
    void testRecordMatchWithTeam2LocNull() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithTeam2LocNull());
        });
    }

    @Test
    void testRecordMatchWithTeam1ScoreNull() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithTeam1ScoreNull());
        });
    }

    @Test
    void testRecordMatchWithTeam2ScoreNull() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithTeam2ScoreNull());
        });
    }

    @Test
    void testRecordMatchWithSeasonLess1858() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithSeasonLess1858());
        });
    }

    @Test
    void testRecordMatchWithTeam1Team2Equal() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithTeam1Team2Equal());
        });
    }

    @Test
    void testRecordMatchWithTeam1Team2LocEqual() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithTeam1Team2LocEqual());
        });
    }

    @Test
    void testRecordMatchWithTeam1ScoreNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithTeam1ScoreNegative());
        });
    }

    @Test
    void testRecordMatchWithTeam2ScoreNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithTeam2ScoreNegative());
        });
    }

    @Test
    void testRecordMatchWithTeam1Team2ScoreEqual() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithTeam1Team2ScoreEqual());
        });
    }

    @Test
    void testRecordMatchWithAttendantsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithAttendantsNegative());
        });
    }

    @Test
    void testRecordMatchWithMarginNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithMarginNegative());
        });
    }

    @Test
    void testRecordMatchWithHomeQ1GoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithHomeQ1GoalsNegative());
        });
    }

    @Test
    void testRecordMatchWithHomeQ2GoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithHomeQ2GoalsNegative());
        });
    }

    @Test
    void testRecordMatchWithHomeQ3GoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithHomeQ3GoalsNegative());
        });
    }

    @Test
    void testRecordMatchWithHomeQ4GoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithHomeQ4GoalsNegative());
        });
    }

    @Test
    void testRecordMatchWithHomeExtraGoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithHomeExtraGoalsNegative());
        });
    }

    @Test
    void testRecordMatchWithHomeExtraBehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithHomeExtraBehindsNegative());
        });
    }

    @Test
    void testRecordMatchWithHomeQ1BehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithHomeQ1BehindsNegative());
        });
    }

    @Test
    void testRecordMatchWithHomeQ2BehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithHomeQ2BehindsNegative());
        });
    }

    @Test
    void testRecordMatchWithHomeQ3BehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithHomeQ3BehindsNegative());
        });
    }

    @Test
    void testRecordMatchWithHomeQ4BehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithHomeQ4BehindsNegative());
        });
    }

    @Test
    void testRecordMatchWithAwayQ1GoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithAwayQ1GoalsNegative());
        });
    }

    @Test
    void testRecordMatchWithAwayQ2GoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithAwayQ2GoalsNegative());
        });
    }

    @Test
    void testRecordMatchWithAwayQ3GoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithAwayQ3GoalsNegative());
        });
    }

    @Test
    void testRecordMatchWithAwayQ4GoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithAwayQ4GoalsNegative());
        });
    }

    @Test
    void testRecordMatchWithAwayExtraGoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithAwayExtraGoalsNegative());
        });
    }

    @Test
    void testRecordMatchWithAwayExtraBehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithAwayExtraBehindsNegative());
        });
    }

    @Test
    void testRecordMatchWithAwayQ1BehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithAwayQ1BehindsNegative());
        });
    }

    @Test
    void testRecordMatchWithAwayQ2BehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithAwayQ2BehindsNegative());
        });
    }

    @Test
    void testRecordMatchWithAwayQ3BehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithAwayQ3BehindsNegative());
        });
    }

    @Test
    void testRecordMatchWithAwayQ4BehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithAwayQ4BehindsNegative());
        });
    }

    @Test
    void testRecordMatchWithStartDateDifferentFromSeason() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithStartDateDifferentFromSeason());
        });
    }

    @Test
    void testRecordMatchWithVenueBlank() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.recordMatch(getMatchWithVenueBlank());
        });
    }

    @Test
    void testRecordMatchWithUnknownTeam() throws MatchAlreadyExistsException, UnknownTeamException {

        doThrow(UnknownTeamException.class).when(dao).createMatch(any());

        assertThrows(UnknownTeamException.class, () -> {
           service.recordMatch(getValidMatch());
        });
    }

    @Test
    void testRecordMatchWithMatchAlreadyExists() throws MatchAlreadyExistsException, UnknownTeamException {

        doThrow(MatchAlreadyExistsException.class).when(dao).createMatch(any());

        assertThrows(MatchAlreadyExistsException.class, () -> {
            service.recordMatch(getValidMatch());
        });
    }

    @Test
    void testUpdateMatch() throws UnknownMatchException, InvalidMatchArgumentException {

        service.updateMatch(getValidMatch());

        verify(dao, times(1)).updateMatch(any());
    }

    @Test
    void testUpdateMatchWithIdNull() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
           service.updateMatch(getMatchWithIdNull());
        });
    }

    @Test
    void testUpdateMatchWithTeam1LocNull() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithTeam1LocNull());
        });
    }

    @Test
    void testUpdateMatchWithTeam2LocNull() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithTeam2LocNull());
        });
    }

    @Test
    void testUpdateMatchWithTeam1ScoreNull() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithTeam1ScoreNull());
        });
    }

    @Test
    void testUpdateMatchWithTeam2ScoreNull() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithTeam1ScoreNull());
        });
    }

    @Test
    void testUpdateMatchWithSeasonLess1858() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithSeasonLess1858());
        });
    }

    @Test
    void testUpdateMatchWithTeam1Team2Equal() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithTeam1Team2Equal());
        });
    }

    @Test
    void testUpdateMatchWithTeam1Team2LocEqual() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithTeam1Team2LocEqual());
        });
    }

    @Test
    void testUpdateMatchWithTeam1ScoreNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithTeam1ScoreNegative());
        });
    }

    @Test
    void testUpdateMatchWithTeam2ScoreNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithTeam2ScoreNegative());
        });
    }

    @Test
    void testUpdateMatchWithTeam1Team2ScoreEqual() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithTeam1Team2ScoreEqual());
        });
    }

    @Test
    void testUpdateMatchWithAttendantsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithAttendantsNegative());
        });
    }

    @Test
    void testUpdateMatchWithMarginNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithMarginNegative());
        });
    }

    @Test
    void testUpdateMatchWithHomeQ1GoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithHomeQ1GoalsNegative());
        });
    }

    @Test
    void testUpdateMatchWithHomeQ2GoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithHomeQ2GoalsNegative());
        });
    }

    @Test
    void testUpdateMatchWithHomeQ3GoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithHomeQ3GoalsNegative());
        });
    }

    @Test
    void testUpdateMatchWithHomeQ4GoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithHomeQ4GoalsNegative());
        });
    }

    @Test
    void testUpdateMatchWithHomeExtraGoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithHomeExtraGoalsNegative());
        });
    }

    @Test
    void testUpdateMatchWithHomeExtraBehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithHomeExtraBehindsNegative());
        });
    }

    @Test
    void testUpdateMatchWithHomeQ1BehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithHomeQ1BehindsNegative());
        });
    }

    @Test
    void testUpdateMatchWithHomeQ2BehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithHomeQ2BehindsNegative());
        });
    }

    @Test
    void testUpdateMatchWithHomeQ3BehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithHomeQ3BehindsNegative());
        });
    }

    @Test
    void testUpdateMatchWithHomeQ4BehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithHomeQ4BehindsNegative());
        });
    }

    @Test
    void testUpdateMatchWithAwayQ1GoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithAwayQ1GoalsNegative());
        });
    }

    @Test
    void testUpdateMatchWithAwayQ2GoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithAwayQ2GoalsNegative());
        });
    }

    @Test
    void testUpdateMatchWithAwayQ3GoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithAwayQ3GoalsNegative());
        });
    }

    @Test
    void testUpdateMatchWithAwayQ4GoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithAwayQ4GoalsNegative());
        });
    }

    @Test
    void testUpdateMatchWithAwayExtraGoalsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithAwayExtraGoalsNegative());
        });
    }

    @Test
    void testUpdateMatchWithAwayExtraBehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithAwayExtraBehindsNegative());
        });
    }

    @Test
    void testUpdateMatchWithAwayQ1BehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithAwayQ1BehindsNegative());
        });
    }

    @Test
    void testUpdateMatchWithAwayQ2BehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithAwayQ2BehindsNegative());
        });
    }

    @Test
    void testUpdateMatchWithAwayQ3BehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithAwayQ3BehindsNegative());
        });
    }

    @Test
    void testUpdateMatchWithAwayQ4BehindsNegative() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithAwayQ4BehindsNegative());
        });
    }

    @Test
    void testUpdateMatchWithStartDateDifferentFromSeason() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithStartDateDifferentFromSeason());
        });
    }

    @Test
    void testUpdateMatchWithVenueBlank() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
            service.updateMatch(getMatchWithVenueBlank());
        });
    }

    @Test
    void testUpdateMatchWithUnknownMatch() throws UnknownMatchException {

        doThrow(UnknownMatchException.class).when(dao).updateMatch(any());

        assertThrows(UnknownMatchException.class, () -> {
            service.updateMatch(getValidMatch());
        });
    }

    @Test
    void testDeleteMatch() throws UnknownMatchException, MatchSqlIntegrityException, InvalidMatchArgumentException {

        service.deleteMatch(getValidMatch());

        verify(dao, times(1)).deleteMatch(any());
    }

    @Test
    void testDeleteMatchWithIdNull() {

        assertThrows(InvalidMatchArgumentException.class, () -> {
           service.deleteMatch(getMatchWithIdNull());
        });
    }

    @Test
    void testDeleteMatchWithUnknownMatch() throws MatchSqlIntegrityException, UnknownMatchException {

        doThrow(UnknownMatchException.class).when(dao).deleteMatch(any());

        assertThrows(UnknownMatchException.class, () -> {
            service.deleteMatch(getValidMatch());
        });
    }

    @Test
    void testDeleteMatchWithMatchReferencedInOtherTable() throws MatchSqlIntegrityException, UnknownMatchException {

        doThrow(MatchSqlIntegrityException.class).when(dao).deleteMatch(any());

        assertThrows(MatchSqlIntegrityException.class, () -> {
            service.deleteMatch(getValidMatch());
        });
    }

    private Match getValidMatch() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .build();
    }

    private Match getMatchWithIdNull() {
        return Match.builder()
                .id(null)
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .build();
    }

    private Match getMatchWithSeasonNull() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(null)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .build();
    }

    private Match getMatchWithSeasonLess1858() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(1857)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .build();
    }

    private Match getMatchWithRoundNull() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(null)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .build();
    }

    private Match getMatchWithTeam1Null() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1(null)
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .build();
    }

    private Match getMatchWithTeam2Null() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2(null)
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .build();
    }

    private Match getMatchWithTeam1Team2Equal() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Alma")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .build();
    }

    private Match getMatchWithTeam1LocNull() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(null)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .build();
    }

    private Match getMatchWithTeam2LocNull() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(null)
                .team1Score(100)
                .team2Score(300)
                .build();
    }

    private Match getMatchWithTeam1Team2LocEqual() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.H)
                .team1Score(100)
                .team2Score(300)
                .build();
    }

    private Match getMatchWithTeam1ScoreNull() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(null)
                .team2Score(300)
                .build();
    }

    private Match getMatchWithTeam2ScoreNull() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(300)
                .team2Score(null)
                .build();
    }

    private Match getMatchWithTeam1ScoreNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(-100)
                .team2Score(300)
                .build();
    }

    private Match getMatchWithTeam2ScoreNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(-300)
                .build();
    }

    private Match getMatchWithTeam1Team2ScoreEqual() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(100)
                .build();
    }

    private Match getMatchWithAttendantsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .attendants(-10)
                .build();
    }

    private Match getMatchWithMarginNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .margin(-10)
                .build();
    }

    //#########################################################
    private Match getMatchWithHomeQ1GoalsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .homeQ1Goals(-10)
                .build();
    }

    private Match getMatchWithHomeQ2GoalsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .homeQ2Goals(-10)
                .build();
    }

    private Match getMatchWithHomeQ3GoalsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .homeQ3Goals(-10)
                .build();
    }

    private Match getMatchWithHomeQ4GoalsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .homeQ4Goals(-10)
                .build();
    }

    private Match getMatchWithHomeExtraGoalsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .homeExtraTimeGoals(-10)
                .build();
    }

    private Match getMatchWithHomeQ1BehindsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .homeQ1Behinds(-10)
                .build();
    }

    private Match getMatchWithHomeQ2BehindsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .homeQ2Behinds(-10)
                .build();
    }

    private Match getMatchWithHomeQ3BehindsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .homeQ3Behinds(-10)
                .build();
    }

    private Match getMatchWithHomeQ4BehindsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .homeQ4Behinds(-10)
                .build();
    }

    private Match getMatchWithHomeExtraBehindsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .homeExtraTimeBehinds(-10)
                .build();
    }

    //########################################
    private Match getMatchWithAwayQ1GoalsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .awayQ1Goals(-10)
                .build();
    }

    private Match getMatchWithAwayQ2GoalsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .awayQ2Goals(-10)
                .build();
    }

    private Match getMatchWithAwayQ3GoalsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .awayQ3Goals(-10)
                .build();
    }

    private Match getMatchWithAwayQ4GoalsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .awayQ4Goals(-10)
                .build();
    }

    private Match getMatchWithAwayExtraGoalsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .awayExtraTimeGoals(-10)
                .build();
    }

    private Match getMatchWithAwayQ1BehindsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .awayQ1Behinds(-10)
                .build();
    }

    private Match getMatchWithAwayQ2BehindsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .awayQ2Behinds(-10)
                .build();
    }

    private Match getMatchWithAwayQ3BehindsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .awayQ3Behinds(-10)
                .build();
    }

    private Match getMatchWithAwayQ4BehindsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .awayQ4Behinds(-10)
                .build();
    }

    private Match getMatchWithAwayExtraBehindsNegative() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .awayExtraTimeBehinds(-10)
                .build();
    }

    private Match getMatchWithStartDateDifferentFromSeason() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(1920)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .startDate(LocalDateTime.now())
                .build();
    }

    private Match getMatchWithVenueBlank() {
        return Match.builder()
                .id("2020_EF_101_102")
                .season(2020)
                .round(MatchRound.EF)
                .team1("Alma")
                .team2("Körte")
                .team1Location(MatchLocation.H)
                .team2Location(MatchLocation.A)
                .team1Score(100)
                .team2Score(300)
                .venue("")
                .build();
    }


    private Collection<Match> getMatches() {
        return Arrays.asList(
                Match.builder()
                        .id("2020_EF_101_102")
                        .team1("Alma")
                        .team2("Körte")
                        .build(),
                Match.builder()
                        .id("2020_EF_101_102")
                        .team1("Alma")
                        .team2("Körte")
                        .build()
        );
    }
}