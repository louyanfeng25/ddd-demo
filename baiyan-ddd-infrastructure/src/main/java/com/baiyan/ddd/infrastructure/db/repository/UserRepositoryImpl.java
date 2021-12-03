package com.baiyan.ddd.infrastructure.db.repository;

import com.baiyan.ddd.domain.aggregate.user.model.User;
import com.baiyan.ddd.domain.aggregate.user.repository.UserRepository;
import com.baiyan.ddd.infrastructure.db.converter.UserConverter;
import com.baiyan.ddd.infrastructure.db.mapper.UserMapper;
import com.baiyan.ddd.infrastructure.db.model.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Objects;

/**
 *
 * 用户领域仓储
 *
 * @author baiyan
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void delete(Long id){
        userMapper.deleteById(id);
    }

    @Override
    public User byId(Long id){
        UserPO user = userMapper.selectById(id);
        if(Objects.isNull(user)){
            return null;
        }
        return UserConverter.deserialize(user);
    }

    @Override
    public User save(User user){
        UserPO userPo = UserConverter.serializeUser(user);
        if(Objects.isNull(user.getId())){
            userMapper.insert(userPo);
        }else {
            userMapper.updateById(userPo);
        }
        return UserConverter.deserialize(userPo);
    }

}
