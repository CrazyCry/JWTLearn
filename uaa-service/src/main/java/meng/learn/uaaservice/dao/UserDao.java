package meng.learn.uaaservice.dao;

import meng.learn.uaaservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
