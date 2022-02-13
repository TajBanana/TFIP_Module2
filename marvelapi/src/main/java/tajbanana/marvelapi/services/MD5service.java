package tajbanana.marvelapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tajbanana.marvelapi.Constants;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;

public class MD5service {
    public static final Logger logger = LoggerFactory.getLogger(MD5service.class.getName());

    public static byte[] digest(byte[] input) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] accessKeyBytes = md.digest(input);
        return accessKeyBytes;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void getHash() throws NoSuchAlgorithmException {
        String ts = String.valueOf(ZonedDateTime.now().toInstant().toEpochMilli());
        String accessKey = ts + Constants.PRIVATE_KEY + Constants.API_KEY;
        logger.info(accessKey);
        byte[] accessKeyBytes = MD5service.digest(accessKey.getBytes(StandardCharsets.UTF_8));
        logger.info(bytesToHex(accessKeyBytes));
    }

}

