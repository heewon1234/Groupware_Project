package com.kdt.utils;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class uuid {
    
    public static int uuidToPositiveInt(UUID uuid) {
        byte[] bytes = ByteBuffer.wrap(new byte[16])
                            .putLong(uuid.getMostSignificantBits())
                            .putLong(uuid.getLeastSignificantBits())
                            .array();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(bytes);
            BigInteger bigInt = new BigInteger(1, hash);
            return bigInt.intValue() & Integer.MAX_VALUE; // Convert to positive int
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return 0; // Handle the exception as needed
        }
    }

    @RequestMapping("/generateUUID")
    public int generateUUID() {
        // UUID 생성
        UUID uuid = UUID.randomUUID();
        // 양수로 변환하여 반환
        return uuidToPositiveInt(uuid);
    }
}
