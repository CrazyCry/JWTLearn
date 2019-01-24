package meng.learn.userservice.service;

import meng.learn.userservice.client.AuthServiceClient;
import meng.learn.userservice.dao.UserDao;
import meng.learn.userservice.dto.UserLoginDTO;
import meng.learn.userservice.entity.JWT;
import meng.learn.userservice.entity.User;
import meng.learn.userservice.exception.UserLoginException;
import meng.learn.userservice.utils.BPwdEncoderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceDetail implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Autowired
    AuthServiceClient authServiceClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.findByUsername(username);
    }

    public User insertUser(String username,String password){
        User user=new User();
        user.setUsername(username);
        user.setPassword(BPwdEncoderUtil.BCryptPassword(password));
        return userDao.save(user);
    }

    public UserLoginDTO login(String username,String password){
        User user=userDao.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        if(BPwdEncoderUtil.matchers(password,user.getPassword())){
            throw new UserLoginException("密码错误");
        }
        JWT jwt=authServiceClient.getToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==","password",username,password);
        if(jwt == null){
            throw new UserLoginException("内部错误");
        }
        UserLoginDTO userLoginDTO=new UserLoginDTO();
        userLoginDTO.setJwt(jwt);
        userLoginDTO.setUser(user);
        return userLoginDTO;
    }

}
