package meng.learn.userservice.client;

import meng.learn.userservice.entity.JWT;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceHystrix implements AuthServiceClient{
    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        return null;
    }
}
