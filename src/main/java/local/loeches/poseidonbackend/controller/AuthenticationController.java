package local.loeches.poseidonbackend.controller;

import local.loeches.poseidonbackend.dao.Version;
import local.loeches.poseidonbackend.dao.request.Agilent;
import local.loeches.poseidonbackend.service.impl.AgilentService;
import local.loeches.poseidonbackend.service.impl.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import local.loeches.poseidonbackend.dao.request.SignUpRequest;
import local.loeches.poseidonbackend.dao.request.SigninRequest;
import local.loeches.poseidonbackend.dao.response.JwtAuthenticationResponse;
import local.loeches.poseidonbackend.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private static final String template = "Backend Version: ";
    private final AuthenticationService authenticationService;
    @Autowired
    private AgilentService agilentService;
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

    @GetMapping("/agilent/{mail}")
    public ResponseEntity<String> getAgilentByMail (@PathVariable(value="mail") String mail){
        return agilentService.IsAgilentMail(mail);

    }
    @GetMapping("/agilent")
    public ResponseEntity <List<Agilent>> getAllMails(@RequestParam(required = false) String name){
        return agilentService.getMails(name);
    }

}
