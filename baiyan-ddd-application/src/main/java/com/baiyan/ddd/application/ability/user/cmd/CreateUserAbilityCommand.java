package com.baiyan.ddd.application.ability.user.cmd;

import com.baiyan.ddd.base.model.ddd.Command;
import com.baiyan.ddd.domain.aggregate.user.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 新建用户指令
 * @author baiyan
 */
@Data
public class CreateUserAbilityCommand implements Command {

    /**
     * 用户名
     */
    @NotBlank(message = "{user.userName.is.blank}")
    private String userName;

    /**
     * 用户真实名称
     */
    @NotBlank(message = "{user.realName.is.blank}")
    private String realName;

    /**
     * 用户手机号
     */
    @NotBlank(message = "{user.phone.is.blank}")
    private String phone;

    /**
     * 省
     */
    @NotBlank(message = "{user.province.is.blank}")
    private String province;

    /**
     * 市
     */
    @NotBlank(message = "{user.city.is.blank}")
    private String city;

    /**
     * 区
     */
    @NotBlank(message = "{user.county.is.blank}")
    private String county;

    /**
     * 角色
     */
    @Size(min = 1,message = "{user.role.id.is.empty}")
    private List<Long> roles;

    /**
     * 单位id
     */
    @NotNull(message = "{unit.id.is.null}")
    private Long unitId;

    /**
     * 用户密码
     */
    @NotBlank(message = "{user.password.is.blank}")
    private String password;

    /**
     * 将command转换成聚合根
     *
     * 逻辑简单，只是做一些字段的映射则在command里面直接转化返回给应用服务层使用即可。
     *
     * 如果逻辑复杂，command参数进来需要做一些比较复杂的逻辑处理，则使用工厂类
     *
     * @param command
     * @return
     */
    public User toUser(CreateUserAbilityCommand command){
        User user = User.builder()
                .userName(command.getUserName())
                .realName(command.getRealName())
                .phone(command.getPhone())
                .password(command.getPassword())
                .build();
        user.bindUnit(command.getUnitId());
        user.bindRole(command.getRoles());
        user.bindAddress(command.getProvince(),command.getCity(),command.getCounty());
        return user;
    }
}
