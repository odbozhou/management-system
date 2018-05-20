package com.management.service.impl;/**
 * Created by jiajia on 2018/5/12.
 */

import com.management.common.BaseDao;
import com.management.common.BaseServiceImpl;
import com.management.dao.UserDao;
import com.management.model.User;
import com.management.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Date;

/**
 * @author jiajia
 * @version V1.0
 * @Description: 用户服务
 * @date 2018/5/12 11:07
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService, UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
    public BaseDao<User> getDao() {
        return userDao;
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

    @Transactional
    @Override
    public User createNewUser(User userParam) {
        userParam.setCreateTime(new Date());
        userParam.setDeleteStatus(1);
        userParam.setStatus(1);
        userParam.setUpdateTime(new Date());
        userParam.setUserName(userParam.getLoginName());
        return save(userParam);
    }

    @Override
    public User getByLoginName(String userName) {
        return userDao.loadUserByUsername(userName);
    }

    @Override
    public Boolean updatePasswd(User user) {
        user.setUpdateTime(new Date());
        User oldUser = get(user.getUserId());
        passwordEncoder.encode(user.getPasswd());
        boolean flag = passwordEncoder.matches(user.getPasswd(), passwordEncoder.encode(user.getPasswd()));
/*        logger.info("passwd = {}", user.getPasswd());
        logger.info("passwd = {}", inputPasswd);
        logger.info("oldPasswd = {}", oldUser.getPasswd());*/
        if (!flag) {
            return Boolean.FALSE;
        }
        user.setPasswd(passwordEncoder.encode(user.getNewpasswd()));
        update(user);
        return Boolean.TRUE;
    }

    private Authentication authenticate(User user) {
        return new UsernamePasswordAuthenticationToken(createNewUser(user), null, Collections.singleton(createAuthority(user)));
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
