package com.baiyan.ddd.application.ability.share;

import com.baiyan.ddd.base.model.result.BaseResult;
import com.baiyan.ddd.base.model.result.Result;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 基础能力抽象类
 *
 * @author baiyan
 */
@Component
public abstract class BaseAbility<T, R> {

    /**
     * 能力点执行
     *
     * @param abilityCmd
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Result<R> executeAbility(T abilityCmd) {
        try {

            //初始化上下文
            AbilityContext.initContext();

            //能力点执行前的参数校验
            checkHandler(abilityCmd);

            //能力点执行前的幂等校验
            Result<R> checkIdempotent = checkIdempotent(abilityCmd);
            if (Objects.isNull(checkIdempotent) || !Objects.equals(checkIdempotent.getCode(), BaseResult.CODE_SUCCESS)) {
                return checkIdempotent;
            }

            //执行能力业务
            return execute(abilityCmd);

        } finally {
            AbilityContext.clearContext();
        }
    }

    /**
     * 能力点执行前的参数校验
     *
     * @param abilityCmd abilityCmd
     * @return
     */
    public abstract void checkHandler(T abilityCmd);

    /**
     * 能力点执行前的幂等校验
     *
     * @param abilityCmd abilityCmd
     * @return false：当前能力点已执行，不再执行业务逻辑、true：当前能力点未执行，继续执行业务逻辑
     */
    public abstract Result<R> checkIdempotent(T abilityCmd);

    /**
     * 执行能力业务
     *
     * @param abilityCmd abilityCmd
     * @return return
     */
    public abstract Result<R> execute(T abilityCmd);

}
