package com.fengyi.forFun.solutions.selectAndDisable;

import com.fengyi.forFun.domain.Pair;
import com.fengyi.forFun.utils.PairUtils;

import java.util.*;
import java.util.stream.Collectors;

public class SolutionGenerator {
    private List<Pair> choosablePairs;
    private List<Pair> selectedPairs = new ArrayList<>();
    private List<List<Pair>> disablePairs = new ArrayList<>();
    private final int teamSize;
    private List<List<Pair>> result = new ArrayList<>();
    List<Pair> relativePairs = new ArrayList<>();

    public SolutionGenerator(Set<String> teamMember) {
        this.choosablePairs = PairUtils.generatePossiblePairs(teamMember);
        this.teamSize = teamMember.size();
    }

    public void generatePairSchedule() {
        if (isFinishAll()) {
            return;
        }
        if (isFinishThisRound()) {
            addSelectedToResult();
            addDisableToChoosable();
            clearDisableAndSelected();
        } else {
            if (haveNoChoice()) {
                rollbackLastPick();
            } else {
                Pair picked = choosablePairs.get(0);
                selectedPairs.add(picked);
                disableRelativePairs(picked);
            }
        }
        generatePairSchedule();
    }

    private boolean haveNoChoice() {
        return choosablePairs.isEmpty();
    }

    private boolean isFinishThisRound() {
        return selectedPairs.size() == getPairNumber();
    }

    private void clearDisableAndSelected() {
        disablePairs.clear();
        selectedPairs.clear();
    }

    private void addDisableToChoosable() {
        choosablePairs.addAll(getAllPairFrom(disablePairs));
    }

    private void rollbackLastPick() {
        if (selectedPairs.isEmpty()) {
            throw new IllegalStateException("Can't not get the Answer, because this program doesn't support such a large team size");
        }
        Pair lastPicked = selectedPairs.remove(selectedPairs.size() - 1);
        List<Pair> lastDisablePairs = disablePairs.remove(disablePairs.size() - 1);
        choosablePairs.addAll(lastDisablePairs);
        disablePairs.add(Collections.singletonList(lastPicked));
    }

    private List<Pair> getAllPairFrom(List<List<Pair>> listOfPairList) {
        return listOfPairList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private void disableRelativePairs(Pair picked) {
        List<Pair> newChoosableList = new ArrayList<>();
        for (Pair pair : choosablePairs) {
            if (pair.isTheSamePair(picked)) {
                continue;
            }
            if (!pair.contains(picked)) {
                newChoosableList.add(pair);
            } else {
                relativePairs.add(pair);
            }
        }
        disablePairs.add(new ArrayList<>(relativePairs));
        relativePairs.clear();
        choosablePairs = newChoosableList;
    }

    private boolean isFinishAll() {
        return haveNoChoice() && result.size() == getRoundNumber();
    }

    private void addSelectedToResult() {
        result.add(new ArrayList<>(selectedPairs));
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
        printNestedPairList("result:", result);
    }

    private void printNestedPairList(String message, List<List<Pair>> pairs) {
        if (!message.isEmpty()) {
            System.out.println(message);
        }
        for (int listIndex = 0; listIndex < pairs.size(); listIndex++) {
            System.out.println(listIndex + ":");
            printPairList("", pairs.get(listIndex));
        }
    }

    private void printPairList(String message, List<Pair> pairs) {
        if (!message.isEmpty()) {
            System.out.println(message);
        }
        pairs.forEach(this::printPair);
    }

    private void printPair(String message, Pair pair) {
        if (!message.isEmpty()) {
            System.out.println(message);
        }
        System.out.println(pair.toString());
    }

    private void printPair(Pair pair) {
        System.out.println(pair.toString());
    }
}

