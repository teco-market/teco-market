package com.teco.market.infra.oauth2;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/login")
@RequiredArgsConstructor
@RestController
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/token")
    public ResponseEntity<Void> redirectTokenPage(@RequestParam final String code,
        final HttpServletResponse response) throws IOException {
        final String redirectUrl = loginService.createJwtTokenUrl(code);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }
}
