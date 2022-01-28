package com.fengyi.forFun.solutions.selectAndDisable;

import com.fengyi.forFun.domain.Pair;
import com.fengyi.forFun.utils.PairUtils;

import java.util.*;
import java.util.stream.Collectors;

public class SolutionGenerator {
    private final List<Pair> notSelectedPairs;
    private final List<List<Pair>> result = new ArrayList<>();
    private final int teamSize;
    private int rollbackTimes = 0;

    public SolutionGenerator(Set<String> teamMember) {
        this.notSelectedPairs = PairUtils.generatePossiblePairs(teamMember);
        this.teamSize = teamMember.size();
    }

    public boolean generatePairSchedule(int round, int pairIndex) {
        if (oneRoundFinish(pairIndex)) {
            if (haveDuplicateMember()) {
                return false;
            }
            round++;
            addSelectedToResult();
            enableNotSelectedPairs();
            if (isAllRoundFinish(round)) {
                return true;
            }
            pairIndex = 0;
        }

        for (Pair pair : notSelectedPairs) {
            if (isNotAvailable(pair)) {
                continue;
            }
            pair.markSelected();
            pair.disable();
            disableRelativePairs(pair);
            if (generatePairSchedule(round, pairIndex + 1)) {
                return true;
            }
            rollbackTimes ++;
            System.out.println("roll back" + rollbackTimes);
            pair.cancelSelected();
            enableRelativePairs(pair);
            pair.enable();
        }
        return false;
    }

    private boolean isNotAvailable(Pair pair) {
        return pair.isDisable() || pair.isSelected() || isRelativeOfSelected(pair);
    }

    private boolean isRelativeOfSelected(Pair pair) {
        return getSelectedPairs().stream()
                .anyMatch(selectedPair -> selectedPair.isRelative(pair));
    }

    private boolean isAllRoundFinish(int round) {
        return round == getRoundNumber();
    }

    private boolean oneRoundFinish(int pairIndex) {
        return pairIndex == getPairNumber();
    }

    private boolean haveDuplicateMember() {
        int size = getSelectedPairs().stream()
                .map(Pair::getPairMembers)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet())
                .size();
        return !(size == this.teamSize);
    }

    private List<Pair> getSelectedPairs() {
        return this.notSelectedPairs.stream()
                .filter(Pair::isSelected)
                .collect(Collectors.toList());
    }

    private void enableNotSelectedPairs() {
        notSelectedPairs.removeAll(getSelectedPairs());
        notSelectedPairs.forEach(Pair::enable);
    }

    private void disableRelativePairs(Pair picked) {
        notSelectedPairs.stream()
                .filter(pair -> pair.isRelative(picked))
                .forEach(Pair::disable);
    }

    private void enableRelativePairs(Pair picked) {
        notSelectedPairs.stream()
                .filter(pair -> pair.isRelative(picked))
                .forEach(Pair::enable);
    }

    private void addSelectedToResult() {
        result.add(getSelectedPairs());
    }

    private int getPairNumber() {
        return (teamSize + 1) / 2;
    }

    private int getRoundNumber() {
        return getPairNumber() * 2 - 1;
    }

    public List<List<Pair>> getResult() {
        return result;
    }

    public void printSolution() {
        printNestedPairList("result", result);
    }

    private void printNestedPairList(String message, List<List<Pair>> pairs) {
        if (!message.isEmpty()) {
            System.out.println(message);
        }
        for (int listIndex = 0; listIndex < pairs.size(); listIndex++) {
            System.out.println("Round" + listIndex + ":");
            printPairList(pairs.get(listIndex));
        }
    }

    private void printPairList(List<Pair> pairs) {
        pairs.forEach(this::printPair);
    }

    private void printPair(Pair pair) {
        System.out.println(pair.toString());
    }
}