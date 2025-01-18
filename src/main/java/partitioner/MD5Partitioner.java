package partitioner;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Partitioner implements IPartitioner{
    private final MessageDigest md;
    private final String MD5 = "MD5";

    public MD5Partitioner() throws NoSuchAlgorithmException {
        this.md = MessageDigest.getInstance(MD5);
    }


    @Override
    public long getHash64(String key) {
        long hashedValue = 0;
        md.update(key.getBytes());
        byte[] messageDigest = md.digest();
        //get first 8 bytes
        for(int i = 0; i < 8; ++i) {
            hashedValue <<= 8;
            hashedValue |= ((long) messageDigest[i] & 0xFF);
        }
        return hashedValue & 0x7fffffff;
    }
}
