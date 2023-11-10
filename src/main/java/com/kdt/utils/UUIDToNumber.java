package com.kdt.utils;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


public class UUIDToNumber {
    public static int uuidToPositiveInt(UUID uuid) {
        byte[] bytes = ByteBuffer.wrap(new byte[16])
                            .putLong(uuid.getMostSignificantBits())
                            .putLong(uuid.getLeastSignificantBits())
                            .array();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(bytes);
            BigInteger bigInt = new BigInteger(1, hash);
            return bigInt.intValue() & Integer.MAX_VALUE; // Convert to int and make it positive
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return 0; // Handle the exception as needed
        }
    }

    public static int convertUUIDToPositiveInt() {
        UUID uuid = UUID.randomUUID();
        return uuidToPositiveInt(uuid);
    }
}

