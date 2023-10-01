package local.loeches.poseidonbackend.service;

import local.loeches.poseidonbackend.dao.request.SignUpRequest;
import local.loeches.poseidonbackend.dao.request.SigninRequest;
import local.loeches.poseidonbackend.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
