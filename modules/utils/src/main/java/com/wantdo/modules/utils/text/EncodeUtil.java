package com.wantdo.modules.utils.text;

import com.google.common.io.BaseEncoding;

public class EncodeUtil {

    /**
     * Base64编码
     * @param input
     * @return
     */
    public static String encodeBase64(byte[] input) {
        return BaseEncoding.base64().encode(input);
    }
}
