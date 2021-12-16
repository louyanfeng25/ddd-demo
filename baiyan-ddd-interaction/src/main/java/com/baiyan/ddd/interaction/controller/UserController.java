package com.baiyan.ddd.interaction.controller;

import com.baiyan.ddd.application.ability.user.cmd.CreateUserAbilityCommand;
import com.baiyan.ddd.application.command.UserApplicationService;
import com.baiyan.ddd.application.command.cmd.user.UpdateUserCommand;
import com.baiyan.ddd.application.query.UserQueryApplicationService;
import com.baiyan.ddd.application.query.model.user.dto.UserPageDTO;
import com.baiyan.ddd.base.model.query.KeywordQuery;
import com.baiyan.ddd.base.model.result.BaseResult;
import com.baiyan.ddd.base.model.result.Page;
import com.baiyan.ddd.base.model.result.PageResult;
import com.baiyan.ddd.base.model.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户管理web接口
 *
 * @author baiyan
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserApplicationService userApplicationService;

    @Autowired
    UserQueryApplicationService userQueryApplicationService;

    @PostMapping
    public Result<Object> create(@RequestBody @Valid CreateUserAbilityCommand command){
        userApplicationService.create(command);
        return Result.ok(BaseResult.INSERT_SUCCESS);
    }

    @PutMapping
    public Result<Object> update(@RequestBody @Valid UpdateUserCommand command){
        userApplicationService.update(command);
        return Result.ok(BaseResult.UPDATE_SUCCESS);
    }

    @DeleteMapping("/{id}")
    public Result<Object> delete(@PathVariable Long id){
        userApplicationService.delete(id);
        return Result.ok(BaseResult.DELETE_SUCCESS);
    }

    @GetMapping
    public PageResult<UserPageDTO> query(KeywordQuery query){
        Page<UserPageDTO> users = userQueryApplicationService.userPage(query);
        return PageResult.ok(users);
    }

}
