package demo;

import com.skj.HashRing;
import partitioner.IPartitioner;
import partitioner.MD5Partitioner;

import java.security.NoSuchAlgorithmException;

public class HashRingDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        IPartitioner md = new MD5Partitioner();
        HashRing hashRing = new HashRing(md);
        hashRing.addNode("node-1", 2);
        hashRing.addNode("node-2", 2);
        hashRing.addNode("node-3", 3);

        hashRing.addValue("value1");

        hashRing.removeNode("node-3");

        hashRing.addValue("value1");
    }
}
