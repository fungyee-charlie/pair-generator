package com.fengyi.forFun.domain;

import java.util.*;

public class Pair {
    private Set<String> pairMembers;
    private boolean isDisable;
    private boolean isSelected;

    public void disable() {
        this.isDisable = true;
    }

    public void enable() {
        this.isDisable = false;
    }

    public boolean isDisable() {
        return isDisable;
    }

    public Pair(Set<String> pairMembers) {
        this.pairMembers = new HashSet<>(pairMembers);
    }

    public boolean contains(String name) {
        return pairMembers.contains(name);
    }

    public Set<String> getPairMembers() {
        return new HashSet<>(pairMembers);
    }

    public List<String> getPairMemberList() {
        return new ArrayList<>(pairMembers);
    }

    public boolean contains(Pair pair) {
        return pair.getPairMembers().stream().anyMatch(this::contains);
    }

    public boolean isRelative(Pair pair) {
        if (this.isTheSamePair(pair)) {
            return false;
        }
        return this.contains(pair);
    }

    public String toString() {
        List<String> pairMemberList = getPairMemberList();
        return String.format("%s + %s", pairMemberList.get(0), pairMemberList.get(1));
    }

    public boolean isTheSamePair(Pair pair) {
        return this.pairMembers.containsAll(pair.getPairMembers());
    }


    public void markSelected() {
        this.isSelected = true;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void cancelSelected() {
        this.isSelected = false;
    }
}
