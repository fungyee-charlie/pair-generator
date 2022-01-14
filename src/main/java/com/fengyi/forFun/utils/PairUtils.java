package com.fengyi.forFun.utils;
import com.fengyi.forFun.domain.Pair;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PairUtils {
    public static List<Pair> generatePossiblePairs(Set<String> members) {
        Set<String> memberList = makeEvenMemberSet(members);
        Set<Set<String>> combinations = Sets.combinations(memberList, 2);
        return combinations.stream()
                .map(Pair::new)
                .collect(Collectors.toList());
    }

    public static Set<String> makeEvenMemberSet(Set<String> members) {
        if (isOddSize(members)) {
            members.add("Nobody");
        }
        return members;
    }

    private static boolean isOddSize(Set<String> members) {
        return members.size() % 2 == 1;
    }
}
