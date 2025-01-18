package com.skj;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Node {
    private String name;
    private long key;
    private final HashSet<Long> vnodesHashKeys;
    private final int virtualNodesCount;

    public Node(String name, long key, int virtualNodesCount) {
        this.name = name;
        this.key = key;
        this.virtualNodesCount = virtualNodesCount;
        vnodesHashKeys = new HashSet<>();
    }

    public void addVirtualNodeKey(long hashKey) {
        vnodesHashKeys.add(hashKey);
    }

    public List<Long> getVirtualNodes() {
        return new ArrayList<>(vnodesHashKeys);
    }

    public int getVirtualNodesCount() {
        return virtualNodesCount;
    }

    public long getKey() {
        return this.key;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name + " - " + this.key;
    }
}
