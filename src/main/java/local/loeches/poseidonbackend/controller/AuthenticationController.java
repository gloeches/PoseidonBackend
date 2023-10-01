package local.loeches.poseidonbackend.controller;

import local.loeches.poseidonbackend.dao.Version;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import local.loeches.poseidonbackend.dao.request.SignUpRequest;
import local.loeches.poseidonbackend.dao.request.SigninRequest;
import local.loeches.poseidonbackend.dao.response.JwtAuthenticationResponse;
import local.loeches.poseidonbackend.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.atomic.AtomicLong;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private static final String template = "Backend Version: ";
    private final AuthenticationService authenticationService;
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
    @GetMapping("/version")
    public Version version(String versionDate, String versionContent) {
        return new Version();
    }

}
