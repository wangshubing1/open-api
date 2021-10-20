package cn.com.belle.bdc.openapi.model;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class UserDomain {

    private Integer userId;

    private String userName;

    private String password;

    private String phone;

}
