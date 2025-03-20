package authservice.controller;

import authservice.entities.RefreshToken;
import authservice.models.UserInfoDTO;
import authservice.services.JwtService;
import authservice.services.RefreshTokenService;
import authservice.services.UserDetailsServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @PostMapping("auth/v1/signup")
    public ResponseEntity SignUp(@RequestBody UserInfoDTO userInfoDto){
        try{
            Boolean isSignUped = userDetailsService.signupUser(userInfoDto);



            if(Boolean.FALSE.equals(isSignUped)){
                return new ResponseEntity<>("Already Exist", HttpStatus.BAD_REQUEST);
            }
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userInfoDto.getUsername());
            String jwtToken = jwtService.generateToken(userInfoDto.getUsername());
            return new ResponseEntity<>(authservice.response.JwtResponseDTO.builder().accessToken(jwtToken).
                    token(refreshToken.getToken()).build(),HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity<>(" Exception in User Service", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
