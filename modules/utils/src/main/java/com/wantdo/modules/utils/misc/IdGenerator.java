package com.wantdo.modules.utils.misc;

import java.security.SecureRandom;
import java.util.UUID;

public class IdGenerator {

    private static SecureRandom random = new SecureRandom();

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static String uuid2() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
