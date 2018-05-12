package com.management.service.impl;/**
 * Created by jiajia on 2018/5/12.
 */

import com.management.dao.UserDao;
import com.management.model.User;
import com.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;

/**
 * @author jiajia
 * @version V1.0
 * @Description: 用户服务
 * @date 2018/5/12 11:07
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    protected void initialize() {
/*        save(new User("student", "student", "ROLE_STUDENT"));
        save(new User("teacher", "teacher", "ROLE_TEACHER"));
        save(new User("admin", "admin", "ROLE_ADMIN"));*/
    }

    @Override
    public User save(User user) {
        user.setPasswd(passwordEncoder.encode(user.getPasswd()));
        userDao.insert(user);
        return user;
    }

    @Override
    public void signin(User user) {
        SecurityContextHolder.getContext().setAuthentication(authenticate(user));
    }

    private Authentication authenticate(User user) {
        return new UsernamePasswordAuthenticationToken(createUser(user), null, Collections.singleton(createAuthority(user)));
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.loadUserByUsername(userName);
        if (null == user) {
            throw new UsernameNotFoundException("user not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getLoginName(), user.getPasswd(), Collections.singleton(createAuthority(user)));
    }



    private org.springframework.security.core.userdetails.User createUser(User user) {
        return new org.springframework.security.core.userdetails.User(user.getLoginName(), user.getPasswd(), Collections.singleton(createAuthority(user)));
    }

    private GrantedAuthority createAuthority(User user) {
        return new SimpleGrantedAuthority(user.getRole());
    }
}
