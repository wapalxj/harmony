package com.vero.weatherapp.util;

import java.io.Closeable;
import java.io.IOException;
import java.util.Map;

public class HiNetUtil {

    /**
     * 拼接URL
     *
     * @param url
     * @param params
     * @return
     */
    public static final String buildParams(String url, Map<String, String> params) {
        if (params == null) {
            return url;
        }
        StringBuilder builder = new StringBuilder(url);
        boolean isFirst = true;
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (key != null && value != null) {
                if (isFirst) {
                    isFirst = false;
                    builder.append("?");
                } else {
                    builder.append("&");
                }
                builder.append(key)
                        .append("=")
                        .append(value);
            }
        }
        return builder.toString();
    }

    public static void close(Closeable closeable){
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
