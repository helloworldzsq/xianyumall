package com.dz.service.impl;

import com.dz.pojo.User;
import com.dz.mapper.UserMapper;
import com.dz.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dz.util.MybatisRedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dz
 * @since 2021-09-10
 */
@Service
@CacheNamespace(implementation= MybatisRedisCache.class,eviction= MybatisRedisCache.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    //获取姓名集合
    public List<String> getNames(){
        List<User> users = userMapper.selectList(null);
        List<String> names = new ArrayList<>();
        for (User user : users) {
            names.add(user.getUsername());
        }
        return names;
    }
    //登录验证
    public boolean validateLogin(String username,String password){
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password))
                return true;
        }
        return false;
    }
}
