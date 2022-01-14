package com.fengyi.forFun.solutions.selectAndDisable;

import com.fengyi.forFun.domain.Pair;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PairGeneratorTest {
    @Test
    public void should_return_round_number_correctly_and_member_unique_in_every_round_new() {
        Random random = new Random();
        int randomNumber = random.nextInt(8);
        int teamSize = getTeamSize(randomNumber);
        Set<String> teamMember = new HashSet<>();
        for (int index = 0; index < randomNumber; index++) {
            teamMember.add("member" + index);
        }
        SolutionGenerator solutionGenerator = new SolutionGenerator(teamMember);
        solutionGenerator.generatePairSchedule();
        List<List<Pair>> actualSchedule = solutionGenerator.getResult();
        boolean allRoundsContainsAllMembers = actualSchedule.stream()
                .allMatch(everyRoundList -> getAllMemberInOneRound(everyRoundList).containsAll(new ArrayList<>(teamMember)));
        assertTrue(allRoundsContainsAllMembers);
        assertEquals(teamSize - 1, actualSchedule.size());
    }

    private int getTeamSize(int randomNumber) {
        return randomNumber % 2 == 0 ? randomNumber : randomNumber + 1;
    }

    private List<String> getAllMemberInOneRound(List<Pair> pairsInOneRound) {
        return pairsInOneRound.stream()
                .map(pair -> (List<String>) new ArrayList<>(pair.getPairMembers()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}