package com.baiyan.ddd.domain.aggregate.user.service.impl;

import com.baiyan.ddd.domain.aggregate.role.model.Role;
import com.baiyan.ddd.domain.aggregate.user.model.User;
import com.baiyan.ddd.domain.aggregate.user.service.UserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 用户领域服务
 *
 * @author baiyan
 */
@Service
@Slf4j
public class UserDomainServiceImpl implements UserDomainService {

    @Override
    public void printTag(User user, Role role){
        //此处省略100行代码
        log.info("用户：{}的标签解析为：{}",user.getUserName(),role.getUserTag());
    }
}
