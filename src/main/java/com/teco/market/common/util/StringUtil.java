package com.teco.market.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
    private static final String SHA_256 = "SHA-256";

    public static String getExtension(String name) {
        if (name == null) {
            return null;
        }
        int index = indexOfExtension(name);
        if (index == -1) {
            return "";
        } else {
            return name.substring(index + 1);
        }
    }

    private static int indexOfExtension(String name) {
        if (name == null) {
            return -1;
        }
        int extensionPos = name.lastIndexOf(".");
        int lastSeparator = indexOfLastSeparator(name);
        return ((lastSeparator > extensionPos) ? -1 : extensionPos);
    }

    private static int indexOfLastSeparator(String filename) {
        if (filename == null) {
            return -1;
        }
        int lastUnixPos = filename.lastIndexOf("/");
        int lastWindowsPos = filename.lastIndexOf("/");
        return Math.max(lastUnixPos, lastWindowsPos);
    }

    public static String getRandomSHA256(final String message) {
        try {
            MessageDigest digest = MessageDigest.getInstance(SHA_256);
            byte[] encodedhash = digest.digest(
                message.getBytes(StandardCharsets.UTF_8));

            return byteArrayToString(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError("SHA-256 algorithm should exists in MessageDigest");
        }
    }

    private static String byteArrayToString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(String.format("%02x", b));
        }

        return stringBuilder.toString();
    }
}
