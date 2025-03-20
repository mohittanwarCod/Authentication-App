package authservice.services;


import authservice.entities.UserInfo;
import authservice.models.UserInfoDTO;
import authservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;


@Component
@AllArgsConstructor
@Data
public class UserDetailsServiceImp implements UserDetailsService  {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    private static final Logger log= LoggerFactory.getLogger(UserDetailsServiceImp.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         log.debug(" Entering in loadByUsername Method... ");

         UserInfo user = userRepository.findByUsername(username);
         if(user == null) {
             log.error("Username Not Found : " +  username);
             throw new UsernameNotFoundException("Could not find username : ");

         }

         log.info("User Authenticated Successfully... !!!");

         return new CustomUserDetails(user);
    }

    public UserInfo checkIfAlreadyExists(UserInfoDTO userInfoDTO){
        return userRepository.findByUsername(userInfoDTO.getUsername());
    }

    public Boolean signupUser(UserInfoDTO userInfoDTO){

        // make user details validation like email validation
        // make a validation utils class

        userInfoDTO.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));

        if(Objects.nonNull(checkIfAlreadyExists(userInfoDTO))){
            return false;
        }

        String userId = UUID.randomUUID().toString();

        userRepository.save(new UserInfo(userId,userInfoDTO.getUsername(),userInfoDTO.getPassword(),new HashSet<>()));

        return true;
    }




}
