package com.baiyan.ddd.biz.user.domain.model;

import com.baiyan.ddd.base.model.ddd.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 地址值对象
 *
 * @author baiyan
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address implements ValueObject<Address> {

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String county;

    /**
     * 比较地址相等
     *
     * @param address 地址
     * @return
     */
    @Override
    public boolean sameValueAs(Address address){
        return Objects.equals(this,address);
    }

}
