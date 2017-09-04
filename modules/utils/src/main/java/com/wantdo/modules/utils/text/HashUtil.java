package com.wantdo.modules.utils.text;

import com.wantdo.modules.utils.base.annotation.NotNull;
import com.wantdo.modules.utils.base.annotation.Nullable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    private static final ThreadLocal<MessageDigest> MD5_DIGEST = createThreadLocalMessageDigest("MD5");
    private static final ThreadLocal<MessageDigest> SHA_1_DIGEST = createThreadLocalMessageDigest("SHA-1");


    private static ThreadLocal<MessageDigest> createThreadLocalMessageDigest(final String digest) {
        return new ThreadLocal<MessageDigest>(){
            @Override
            protected MessageDigest initialValue() {
                try {
                    return MessageDigest.getInstance(digest);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException("unexpected exception creating MessageDigest instance for [" + digest + "]", e);
                }
            }
        };
    }

    public static byte[] sha1(@NotNull byte[] input) {
        return digest(input, get(SHA_1_DIGEST), null, 1);
    }

    public static byte[] sha1(@NotNull String input) {
        return digest(input.getBytes(Charsets.UTF_8), get(SHA_1_DIGEST), null, 1);
    }

    public static byte[] sha1(@NotNull byte[] input, @Nullable byte[] salt) {
        return digest(input, get(SHA_1_DIGEST), salt, 1);
    }

    public static byte[] sha1(@NotNull String input, @NotNull byte[] salt) {
        return digest(input.getBytes(Charsets.UTF_8), get(SHA_1_DIGEST), salt, 1);
    }

    public static byte[] sha1(@NotNull byte[] input, @NotNull byte[] salt, int iterations) {
        return digest(input, get(SHA_1_DIGEST), salt, iterations);
    }

    public static byte[] sha1(@NotNull String input, byte[] salt, int iterations) {
        return digest(input.getBytes(Charsets.UTF_8), get(SHA_1_DIGEST), salt, iterations);
    }

    private static MessageDigest get(ThreadLocal<MessageDigest> messageDigest) {
        MessageDigest instance = messageDigest.get();
        instance.reset();
        return instance;
    }

    private static byte[] digest(@NotNull byte[] input, MessageDigest digest, byte[] salt, int iterations) {
        if (salt != null) {
            digest.update(salt);
        }

        byte[] result = digest.digest(input);

        for(int i = 1; i < iterations; i ++) {
            digest.reset();
            result = digest.digest(result);
        }

        return result;
    }
}
