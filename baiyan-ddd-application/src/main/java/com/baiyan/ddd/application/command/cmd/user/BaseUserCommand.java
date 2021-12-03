package com.baiyan.ddd.application.command.cmd.user;

import com.baiyan.ddd.base.model.ddd.Command;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 通用用户命令
 *
 * @author baiyan
 */
@Data
public class BaseUserCommand implements Command {

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

}
