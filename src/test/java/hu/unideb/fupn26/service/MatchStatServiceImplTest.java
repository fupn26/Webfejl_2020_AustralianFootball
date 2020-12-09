package hu.unideb.fupn26.service;

import hu.unideb.fupn26.dao.MatchStatDao;
import hu.unideb.fupn26.exception.*;
import hu.unideb.fupn26.model.MatchStat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchStatServiceImplTest {

    @InjectMocks
    private MatchStatServiceImpl service;
    @Mock
    private MatchStatDao dao;

    @Test
    void testGetAllMatchStat() {

        when(dao.readAll()).thenReturn(getMatchStats());

        Collection<MatchStat> actual = service.getAllMatchStat();

        assertThat(actual, is(getMatchStats()));
    }

    @Test
    void testGetAllMatchStatByPlayer() {

        when(dao.readAll()).thenReturn(getMatchStats());

        Collection<MatchStat> actual = service.getAllMatchStatByPlayer(10001);
        Collection<MatchStat> expected = Arrays.asList(MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .build()
        );

        assertThat(actual, is(expected));
    }

    @Test
    void testGetAllMatchStatByTeam() {

        when(dao.readAll()).thenReturn(getMatchStats());

        Collection<MatchStat> actual = service.getAllMatchStatByTeam(102);
        Collection<MatchStat> expected = Arrays.asList(MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(102)
                .playerId(10002)
                .build()
        );

        assertThat(actual, is(expected));
    }

    @Test
    void testRecordMatchStat() throws MatchStatAlreadyExistsException, UnknownPlayerException, UnknownTeamException, UnknownMatchException, InvalidMatchStatArgumentException {

        service.recordMatchStat(getValidMatchStat());

        verify(dao, times(1)).createMatchStat(any());
    }

    @Test
    void testRecordMatchStatWithMatchIdNull() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithMatchIdNull());
        });
    }

    @Test
    void testRecordMatchStatWithTeamIdNull() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithTeamIdNull());
        });
    }

    @Test
    void testRecordMatchStatWithPlayerIdNull() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithPlayerIdNull());
        });
    }

    @Test
    void testRecordMatchStatWithGoalsNull() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithGoalsNull());
        });
    }

    @Test
    void testRecordMatchStatWithGoalsNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithGoalsNegative());
        });
    }

    @Test
    void testRecordMatchStatWithBehindsNull() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithBehindsNull());
        });
    }

    @Test
    void testRecordMatchStatWithBehindsNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithBehindsNegative());
        });
    }

    @Test
    void testRecordMatchStatWithTeamNotParticipant() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithTeamNotParticipant());
        });
    }

    @Test
    void testRecordMatchStatWithKicksNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithKicksNegative());
        });
    }

    @Test
    void testRecordMatchStatWithMarksNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithMarksNegative());
        });
    }

    @Test
    void testRecordMatchStatWithHandBallsNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithHandBallsNegative());
        });
    }

    @Test
    void testRecordMatchStatWithDisposalsNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithDisposalsNegative());
        });
    }

    @Test
    void testRecordMatchStatWithHitOutsNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithHitOutsNegative());
        });
    }

    @Test
    void testRecordMatchStatWithTacklesNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithTacklesNegative());
        });
    }

    @Test
    void testRecordMatchStatWithReboundNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithReboundNegative());
        });
    }

    @Test
    void testRecordMatchStatWithInsideNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithInsideNegative());
        });
    }

    @Test
    void testRecordMatchStatWithClearancesNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithClearancesNegative());
        });
    }

    @Test
    void testRecordMatchStatWithClangersNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithClangersNegative());
        });
    }

    @Test
    void testRecordMatchStatWithFreeKicksForNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithFreeKicksForNegative());
        });
    }

    @Test
    void testRecordMatchStatWithFreeKicksAgainstNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithFreeKicksAgainstNegative());
        });
    }

    @Test
    void testRecordMatchStatWithBrownlowVotesNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithBrownlowVotesNegative());
        });
    }

    @Test
    void testRecordMatchStatWithContestedPossessionNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithContestedPossessionsNegative());
        });
    }

    @Test
    void testRecordMatchStatWithUncontestedPossessionsNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithUncontestedPossessionsNegative());
        });
    }

    @Test
    void testRecordMatchStatWithContestedMarksNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithContestedMarksNegative());
        });
    }

    @Test
    void testRecordMatchStatWithMarksInsideNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithMarksInsideNegative());
        });
    }

    @Test
    void testRecordMatchStatWithOnePercentersNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithOnePercentersNegative());
        });
    }

    @Test
    void testRecordMatchStatWithBouncesNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithBouncesNegative());
        });
    }

    @Test
    void testRecordMatchStatWithGoalAssistNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithGoalAssistNegative());
        });
    }

    @Test
    void testRecordMatchStatWithPercentageOfGamePlayedNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.recordMatchStat(getMatchStatWithPercentageOfGamePlayedNegative());
        });
    }

    @Test
    void testRecordMatchStatWithUnknownMatch() throws MatchStatAlreadyExistsException, UnknownPlayerException, UnknownTeamException, UnknownMatchException {

        doThrow(UnknownMatchException.class).when(dao).createMatchStat(any());

        assertThrows(UnknownMatchException.class, () -> {
            service.recordMatchStat(getValidMatchStat());
        });
    }

    @Test
    void testRecordMatchStatWithUnknownPlayer() throws MatchStatAlreadyExistsException, UnknownPlayerException, UnknownTeamException, UnknownMatchException {

        doThrow(UnknownPlayerException.class).when(dao).createMatchStat(any());

        assertThrows(UnknownPlayerException.class, () -> {
            service.recordMatchStat(getValidMatchStat());
        });
    }

    @Test
    void testRecordMatchStatWithUnknownTeam() throws MatchStatAlreadyExistsException, UnknownPlayerException, UnknownTeamException, UnknownMatchException {

        doThrow(UnknownTeamException.class).when(dao).createMatchStat(any());

        assertThrows(UnknownTeamException.class, () -> {
            service.recordMatchStat(getValidMatchStat());
        });
    }

    @Test
    void testRecordMatchStatWithMatchStatAlreadyExists() throws MatchStatAlreadyExistsException, UnknownPlayerException, UnknownTeamException, UnknownMatchException {

        doThrow(MatchStatAlreadyExistsException.class).when(dao).createMatchStat(any());

        assertThrows(MatchStatAlreadyExistsException.class, () -> {
            service.recordMatchStat(getValidMatchStat());
        });
    }

    @Test
    void testUpdateMatchStat() throws UnknownMatchStatException, UnknownPlayerException, UnknownTeamException, UnknownMatchException, InvalidMatchStatArgumentException {

        service.updateMatchStat(getValidMatchStat());

        verify(dao, times(1)).updateMatchStat(any());
    }

    @Test
    void testUpdateMatchStatWithMatchIdNull() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithMatchIdNull());
        });
    }

    @Test
    void testUpdateMatchStatWithTeamIdNull() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithTeamIdNull());
        });
    }

    @Test
    void testUpdateMatchStatWithPlayerIdNull() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithPlayerIdNull());
        });
    }

    @Test
    void testUpdateMatchStatWithGoalsNull() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithGoalsNull());
        });
    }

    @Test
    void testUpdateMatchStatWithGoalsNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithGoalsNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithBehindsNull() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithBehindsNull());
        });
    }

    @Test
    void testUpdateMatchStatWithBehindsNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithBehindsNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithTeamNotParticipant() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithTeamNotParticipant());
        });
    }

    @Test
    void testUpdateMatchStatWithKicksNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithKicksNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithMarksNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithMarksNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithHandBallsNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithHandBallsNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithDisposalsNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithDisposalsNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithHitOutsNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithHitOutsNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithTacklesNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithTacklesNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithReboundNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithReboundNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithInsideNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithInsideNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithClearancesNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithClearancesNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithClangersNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithClangersNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithFreeKicksForNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithFreeKicksForNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithFreeKicksAgainstNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithFreeKicksAgainstNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithBrownlowVotesNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithBrownlowVotesNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithContestedPossessionNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithContestedPossessionsNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithUncontestedPossessionsNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithUncontestedPossessionsNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithContestedMarksNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithContestedMarksNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithMarksInsideNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithMarksInsideNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithOnePercentersNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithOnePercentersNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithBouncesNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithBouncesNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithGoalAssistNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithGoalAssistNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithPercentageOfGamePlayedNegative() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.updateMatchStat(getMatchStatWithPercentageOfGamePlayedNegative());
        });
    }

    @Test
    void testUpdateMatchStatWithUnknownMatch() throws UnknownPlayerException, UnknownTeamException, UnknownMatchException, UnknownMatchStatException {

        doThrow(UnknownMatchException.class).when(dao).updateMatchStat(any());

        assertThrows(UnknownMatchException.class, () -> {
            service.updateMatchStat(getValidMatchStat());
        });
    }

    @Test
    void testUpdateMatchStatWithUnknownPlayer() throws UnknownPlayerException, UnknownTeamException, UnknownMatchException, UnknownMatchStatException {

        doThrow(UnknownPlayerException.class).when(dao).updateMatchStat(any());

        assertThrows(UnknownPlayerException.class, () -> {
            service.updateMatchStat(getValidMatchStat());
        });
    }

    @Test
    void testUpdateMatchStatWithUnknownTeam() throws UnknownPlayerException, UnknownTeamException, UnknownMatchException, UnknownMatchStatException {

        doThrow(UnknownTeamException.class).when(dao).updateMatchStat(any());

        assertThrows(UnknownTeamException.class, () -> {
            service.updateMatchStat(getValidMatchStat());
        });
    }

    @Test
    void testUpdateMatchStatWithUnknownMatchStat() throws UnknownPlayerException, UnknownTeamException, UnknownMatchException, UnknownMatchStatException {

        doThrow(UnknownMatchStatException.class).when(dao).updateMatchStat(any());

        assertThrows(UnknownMatchStatException.class, () -> {
            service.updateMatchStat(getValidMatchStat());
        });
    }

    @Test
    void deleteMatchStat() throws UnknownMatchException, UnknownPlayerException, InvalidMatchStatArgumentException {

        service.deleteMatchStat(getValidMatchStat());

        verify(dao, times(1)).deleteMatchStat(any());
    }

    @Test
    void deleteMatchWithMatchIdNull() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.deleteMatchStat(getMatchStatWithMatchIdNull());
        });
    }

    @Test
    void deleteMatchWithPlayerIdNull() {

        assertThrows(InvalidMatchStatArgumentException.class, () -> {
            service.deleteMatchStat(getMatchStatWithPlayerIdNull());
        });
    }

    @Test
    void testDeleteMatchStatWithUnknownMatch() throws UnknownPlayerException, UnknownMatchException {

        doThrow(UnknownMatchException.class).when(dao).deleteMatchStat(any());

        assertThrows(UnknownMatchException.class, () -> {
            service.deleteMatchStat(getValidMatchStat());
        });
    }

    @Test
    void testDeleteMatchStatWithUnknownPlayer() throws UnknownPlayerException, UnknownMatchException {

        doThrow(UnknownPlayerException.class).when(dao).deleteMatchStat(any());

        assertThrows(UnknownPlayerException.class, () -> {
            service.deleteMatchStat(getValidMatchStat());
        });
    }

    private MatchStat getValidMatchStat() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .build();
    }

    private MatchStat getMatchStatWithMatchIdNull() {
        return MatchStat.builder()
                .matchId(null)
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .build();
    }

    private MatchStat getMatchStatWithTeamIdNull() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(null)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .build();
    }

    private MatchStat getMatchStatWithPlayerIdNull() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(null)
                .goals(10)
                .behinds(11)
                .build();
    }

    private MatchStat getMatchStatWithGoalsNull() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(null)
                .behinds(11)
                .build();
    }

    private MatchStat getMatchStatWithGoalsNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(-10)
                .behinds(11)
                .build();
    }

    private MatchStat getMatchStatWithBehindsNull() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(null)
                .build();
    }

    private MatchStat getMatchStatWithBehindsNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(-11)
                .build();
    }

    private MatchStat getMatchStatWithTeamNotParticipant() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(103)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .build();
    }

    private MatchStat getMatchStatWithKicksNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .kicks(-1)
                .build();
    }

    private MatchStat getMatchStatWithMarksNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .marks(-1)
                .build();
    }

    private MatchStat getMatchStatWithHandBallsNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .handballs(-1)
                .build();
    }

    private MatchStat getMatchStatWithDisposalsNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .disposals(-1)
                .build();
    }

    private MatchStat getMatchStatWithHitOutsNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .hitOuts(-1)
                .build();
    }

    private MatchStat getMatchStatWithTacklesNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .tackles(-1)
                .build();
    }

    private MatchStat getMatchStatWithReboundNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .rebound50s(-1)
                .build();
    }

    private MatchStat getMatchStatWithInsideNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .inside50s(-1)
                .build();
    }

    private MatchStat getMatchStatWithClearancesNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .clearances(-1)
                .build();
    }

    private MatchStat getMatchStatWithClangersNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .clangers(-1)
                .build();
    }

    private MatchStat getMatchStatWithFreeKicksForNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .freeKicksFor(-1)
                .build();
    }

    private MatchStat getMatchStatWithFreeKicksAgainstNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .freeKicksAgainst(-1)
                .build();
    }

    private MatchStat getMatchStatWithBrownlowVotesNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .brownlowVotes(-1)
                .build();
    }

    private MatchStat getMatchStatWithContestedPossessionsNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .contestedPossessions(-1)
                .build();
    }

    private MatchStat getMatchStatWithUncontestedPossessionsNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .uncontestedPossessions(-1)
                .build();
    }

    private MatchStat getMatchStatWithContestedMarksNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .contestedMarks(-1)
                .build();
    }

    private MatchStat getMatchStatWithMarksInsideNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .marksInside50(-1)
                .build();
    }

    private MatchStat getMatchStatWithOnePercentersNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .onePercenters(-1.0f)
                .build();
    }

    private MatchStat getMatchStatWithBouncesNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .bounces(-1)
                .build();
    }

    private MatchStat getMatchStatWithGoalAssistNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .goalAssist(-1)
                .build();
    }

    private MatchStat getMatchStatWithPercentageOfGamePlayedNegative() {
        return MatchStat.builder()
                .matchId("2020_EF_101_102")
                .teamId(101)
                .playerId(10001)
                .goals(10)
                .behinds(11)
                .percentageOfGamePlayed(-1.0f)
                .build();
    }



    private Collection<MatchStat> getMatchStats() {
        return Arrays.asList(
                MatchStat.builder()
                        .matchId("2020_EF_101_102")
                        .teamId(101)
                        .playerId(10001)
                        .build(),
                MatchStat.builder()
                        .matchId("2020_EF_101_102")
                        .teamId(102)
                        .playerId(10002)
                        .build()
        );
    }
}