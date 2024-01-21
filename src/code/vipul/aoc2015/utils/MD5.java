package code.vipul.aoc2015.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by vgaur created on 19/01/24
 */
public class MD5 {

    private static final MessageDigest MD;

    static {
        try {
            MD = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static final char[] HEX_CHARS = {'0', '1', '2', '3',
            '4', '5', '6', '7',
            '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f',};

    /**
     * Taken from -> http://www.twmacinta.com/myjava/fast_md5.php
     */
    private static String asHex (byte[] hash) {
        char[] buf= new char[hash.length * 2];
        for (int i = 0, x = 0; i < hash.length; i++) {
            buf[x++] = HEX_CHARS[(hash[i] >>> 4) & 0xf];
            buf[x++] = HEX_CHARS[hash[i] & 0xf];
        }
        return new String(buf);
    }

    public static String hashMd5(String string) {
        MD.update(string.getBytes(StandardCharsets.UTF_8));
        return asHex(MD.digest());
    }
}
