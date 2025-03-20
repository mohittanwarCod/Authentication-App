package authservice.controller;

import authservice.entities.RefreshToken;
import authservice.services.JwtService;
import authservice.services.RefreshTokenService;
import authservice.response.JwtResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TokenController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("auth/v1/login")
    public ResponseEntity AuthenticateAndGetToken(@RequestBody authservice.request.AuthRequestDTO authRequestDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(),authRequestDTO.getPassword()));
                if(authentication.isAuthenticated()){
                    RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
                    return new ResponseEntity<>(
                            authservice.response.JwtResponseDTO.builder()
                                    .accessToken(jwtService.generateToken(authRequestDTO.getUsername()))
                                    .token(refreshToken.getToken())
                                    .build()
                                    ,HttpStatus.OK);

                }else{
                    return new ResponseEntity<>("Exception in User Service",HttpStatus.INTERNAL_SERVER_ERROR);
                }

    }

    @PostMapping("/api/v1/refreshToken")
    public JwtResponseDTO refreshToken(@RequestBody authservice.request.RefreshTokenRequestDTO refreshTokenDTO){
        return  refreshTokenService.findByToken(refreshTokenDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo->{
                    String accessToken = jwtService.generateToken(userInfo.getUsername());
                    return authservice.response.JwtResponseDTO.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenDTO.getToken()).build();


                }).orElseThrow(()->new RuntimeException("Refresh token is not in DB... !"));



    }
}
