package com.baiyan.ddd.core.infrastructure.repository.converter;

import com.baiyan.ddd.base.model.result.Page;
import com.baiyan.ddd.core.infrastructure.repository.po.RolePO;
import com.baiyan.ddd.core.infrastructure.repository.po.UserPO;
import com.baiyan.ddd.core.infrastructure.repository.po.UserRolePO;
import com.baiyan.ddd.core.user.application.model.dto.UserPageDTO;
import com.baiyan.ddd.core.user.domain.model.User;
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
     * @param userPo
     * @param roles
     * @return
     */
    public static User deserialize(UserPO userPo, List<RolePO> roles) {
        User user = new User(userPo.getUserName(), userPo.getRealName(), userPo.getPhone(), userPo.getPassword());
        user.bindAddress(userPo.getProvince(),userPo.getCity(),userPo.getCounty());
        user.bindRoleByRolePo(roles);
        user.bindUnit(userPo.getUnitId(),userPo.getUnitName());
        return user;
    }

    public static UserPO serializeUser(User user){
        UserPO userPo = new UserPO();
        BeanUtils.copyProperties(user,userPo);
        userPo.setCity(user.getAddress().getCity());
        userPo.setCounty(user.getAddress().getCounty());
        userPo.setProvince(user.getAddress().getProvince());
        userPo.setUnitId(user.getUnit().getId());
        userPo.setUnitName(user.getUnit().getUnitName());
        return userPo;
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
