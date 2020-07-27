package com.teco.market.slack.notify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlInfo {

    public static String INCOMING;

    @Value("${slack.url}")
    public static void setINCOMING(final String INCOMING) {
        UrlInfo.INCOMING = INCOMING;
    }
}
