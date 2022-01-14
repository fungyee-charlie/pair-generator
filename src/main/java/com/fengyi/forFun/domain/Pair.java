package com.fengyi.forFun.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pair {
    private Set<String> pairMembers = new HashSet<>();

    public Pair(String first, String second) {
        this.pairMembers.add(first);
        this.pairMembers.add(second);
    }

    public Pair(Set<String> pairMembers) {
        this.pairMembers = pairMembers;
    }

    public boolean contains(String name) {
        return pairMembers.contains(name);
    }

    public Set<String> getPairMembers() {
        return pairMembers;
    }

    public List<String> getPairMemberList() {
        return new ArrayList<>(pairMembers);
    }

    public boolean contains(Pair pair) {
        return pair.getPairMembers().stream().anyMatch(this::contains);
    }

    public String toString() {
        List<String> pairMemberList = getPairMemberList();
        return String.format("%s + %s", pairMemberList.get(0), pairMemberList.get(1));
    }

    public boolean isTheSamePair(Pair pair) {
        return this.pairMembers.containsAll(pair.getPairMembers());
    }
}
