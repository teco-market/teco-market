package com.teco.market.oauth2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.teco.market.oauth2.Oauth2GoogleUriBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class Oauth2PageController {
    private final Oauth2GoogleUriBuilder oauth2UriBuilder;

    @GetMapping("/google/oauth2")
    public RedirectView redirectGoogleForm() {
        return new RedirectView(oauth2UriBuilder.build());
    }

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/oauth2/google")
    public String auth() {
        return "auth.html";
    }
}
