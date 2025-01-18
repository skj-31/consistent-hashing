package com.skj;

import partitioner.IPartitioner;

import java.util.TreeMap;

public class HashRing {
    private final TreeMap<Long, Node> ring;
    private final IPartitioner partitioner;

    public HashRing(IPartitioner partitioner) {
        this.partitioner = partitioner;
        ring = new TreeMap<>();
    }

    public void addNode(String name, int vNodesCount) {
        long nodeKey = partitioner.getHash64(name);
        Node newNode = new Node(name, nodeKey, vNodesCount);
        ring.put(nodeKey, newNode);

        //add virtual nodes
        for(int i = 1; i <= vNodesCount; ++i) {
            String vName = name + ":" + i;
            long hashVNodeKey = partitioner.getHash64(vName);
            ring.put(hashVNodeKey, newNode);
            newNode.addVirtualNodeKey(hashVNodeKey);
        }
        System.out.printf("node %s added with key %d%n", name, nodeKey);
        displayRing();
    }

    public void removeNode(String name) {
        long key = partitioner.getHash64(name);
        Node node = ring.get(key);
        if (node == null) return;
        ring.remove(key);
        for(long vKey: node.getVirtualNodes()) {
            ring.remove(vKey);
        }
        System.out.printf("node %s removed with key %d%n\n", name, key);
        displayRing();
    }

    public void addValue(String value) {
        displayRing();
        long key = partitioner.getHash64(value);
        Node mappedNode = getNodeResponsible(key);
        System.out.printf("The key %d will be mapped to node %s\n", key, mappedNode.getName());
    }

    private Node getNodeResponsible(long key) {
        Long nodeKey = ring.higherKey(key);
        return nodeKey == null ? ring.firstEntry().getValue() : ring.get(nodeKey);
    }

    private void displayRing() {
        System.out.println(ring);
    }

}
