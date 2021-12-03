package com.baiyan.ddd.infrastructure.rpc;

/**
 * 单位rpc接口
 *
 * 此处对应为调用其他微服务接口，为演示方便，此处采用默认返回
 *
 * @author baiyan
 */
public interface UnitApi {

    /**
     * 根据单位id获取单位信息
     *
     * @param unitId 单位id
     * @return
     */
    default UnitInfoDTO getByUnitId(Long unitId){
        return new UnitInfoDTO(12345L,"柏炎单位","浙江省杭州市滨江区江南大道");
    }

}
