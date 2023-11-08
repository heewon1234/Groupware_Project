package com.kdt.utils;

import java.util.UUID;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class UUIDToNumber {
    public static long uuidToLong(UUID uuid) {
        byte[] bytes = ByteBuffer.wrap(new byte[16])
                            .putLong(uuid.getMostSignificantBits())
                            .putLong(uuid.getLeastSignificantBits())
                            .array();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(bytes);
            BigInteger bigInt = new BigInteger(1, hash);
            return bigInt.longValue();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return 0L; // Handle the exception as needed
        }
    }

    public static int convertUUIDToInt() {
        UUID uuid = UUID.randomUUID();
        long number = uuidToLong(uuid);
        return (int) number; // Convert long to int
    }
}

