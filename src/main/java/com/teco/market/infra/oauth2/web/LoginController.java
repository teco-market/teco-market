package com.teco.market.infra.oauth2.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teco.market.infra.oauth2.service.LoginService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/login")
@RequiredArgsConstructor
@RestController
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/token")
    public ResponseEntity<Void> login(@RequestParam String code, HttpServletResponse response) throws IOException {
        String redirectUrl = loginService.createJwtTokenUrl(code);
        response.sendRedirect(redirectUrl);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/check")
    public ResponseEntity<JwtTokenResponse> loginCheck(
        @RequestParam(value = "access_token", required = false) String token,
        @RequestParam boolean success) {
        if (success) {
            return ResponseEntity.ok(JwtTokenResponse.of(token));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
