package com.baiyan.ddd.core.infrastructure.repository.converter;

import com.baiyan.ddd.base.model.result.Page;
import com.baiyan.ddd.core.infrastructure.query.dto.UserPageDTO;
import com.baiyan.ddd.core.infrastructure.repository.po.RolePO;
import com.baiyan.ddd.core.infrastructure.repository.po.UserPO;
import com.baiyan.ddd.core.infrastructure.repository.po.UserRolePO;
import com.baiyan.ddd.core.user.User;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户转换器
 *
 * @author baiyan
 */
public class UserConverter {

    /**
     * 仓储数据转聚合
     *
     * @param userPO
     * @param roles
     * @return
     */
    public static User deserialize(UserPO userPO, List<RolePO> roles) {
        return new User(userPO,roles);
    }

    public static UserPO serializeUser(User user){
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(user,userPO);
        userPO.setCity(user.getAddress().getCity());
        userPO.setCounty(user.getAddress().getCounty());
        userPO.setProvince(user.getAddress().getProvince());
        userPO.setUnitId(user.getUnit().getId());
        userPO.setUnitName(user.getUnit().getUnitName());
        return userPO;
    }

    public static List<UserRolePO> serializeRole(User user){
        return user.getRoles().stream()
                .map(e-> new UserRolePO(user.getId(),e.getId()))
                .collect(Collectors.toList());
    }


    public static Page<UserPageDTO> serializeUserPage(Page<UserPO> users){
        return users.convert(user->{
            UserPageDTO userPageDTO = new UserPageDTO();
            BeanUtils.copyProperties(user,userPageDTO);
            return userPageDTO;
        });
    }

}
