package authservice.models;

import authservice.entities.UserInfo;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming (PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserInfoDTO extends UserInfo {
//    private String firstName; // user_name -> firstName (SnakeCaseStrategy)
//    private String lastName;
    private String userName;
    private Long phoneNumber;

    private String email;





}
