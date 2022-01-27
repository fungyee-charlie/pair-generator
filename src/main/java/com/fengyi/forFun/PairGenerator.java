package com.fengyi.forFun;


import com.fengyi.forFun.solutions.selectAndDisable.SolutionGenerator;

import java.util.HashSet;
import java.util.Set;

public class PairGenerator {
    public static void main(String[] args) {
        Set<String> teamMembers = new HashSet<>();
        teamMembers.add("A");
        teamMembers.add("B");
        teamMembers.add("C");
        teamMembers.add("D");
        teamMembers.add("E");
        teamMembers.add("F");
        teamMembers.add("G");
        teamMembers.add("H");
        teamMembers.add("I");
        teamMembers.add("J");
        teamMembers.add("K");
        teamMembers.add("L");
        SolutionGenerator solutionGenerator = new SolutionGenerator(teamMembers);
        solutionGenerator.generatePairSchedule(0, 0);
        solutionGenerator.printSolution();
    }
}
