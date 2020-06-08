package com.teco.market.oauth2.ui.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teco.market.oauth2.ui.AuthInfoRequest;
import com.teco.market.oauth2.ui.service.AuthenticationService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/auth/google")
    public ResponseEntity<CustomTokenResponse> auth(@RequestBody AuthInfoRequest authInfoRequest) {
        String token = authenticationService.authGoogle(authInfoRequest);
        return ResponseEntity.ok().body(new CustomTokenResponse(token));
    }
}