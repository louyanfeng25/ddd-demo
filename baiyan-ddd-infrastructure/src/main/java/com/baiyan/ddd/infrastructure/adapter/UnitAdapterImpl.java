package com.baiyan.ddd.infrastructure.adapter;

import com.baiyan.ddd.domain.adapter.UnitAdapter;
import com.baiyan.ddd.domain.adapter.model.unit.UnitDTO;
import org.springframework.stereotype.Component;

@Component
public class UnitAdapterImpl implements UnitAdapter {

    ///正常应该注入调用，因为没有单位服务，所以用静态方法演示
//    @Autowired
//    private UnitApi unitApi;

    /**
     * 根据单位id获取单位信息
     *
     * @param unitId
     * @return
     */
    @Override
    public UnitDTO byUnitId(Long unitId){
//        UnitInfoDTO byUnitId = UnitApi.getByUnitId(unitId);
//        ValidationUtil.isTrue(Objects.nonNull(byUnitId),"unit.is.not.exist");
//        UnitDTO unitDTO = new UnitDTO();
//        BeanUtils.copyProperties(byUnitId,unitDTO);
        return new UnitDTO(12345L,"柏炎单位");
    }
}
