package meng.learn.userservice.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BPwdEncoderUtil {
    private static final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    public static  String BCryptPassword(String password){
        return encoder.encode(password);
    }

    public static Boolean matchers(CharSequence rawPassword,String encodePassword){
        return encoder.matches(rawPassword,encodePassword);
    }
}

