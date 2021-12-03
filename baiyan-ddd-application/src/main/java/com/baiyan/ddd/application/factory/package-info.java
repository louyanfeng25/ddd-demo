/**
 * 工厂包
 *
 * 复杂逻辑使用工厂与新建聚合，且Factory可以被申明为一个bean，去依赖应用服务
 * 或者领域服务，本质上工厂只是一个工具类，用来生产聚合，逻辑合理
 *
 * 从上面的角度来看，工厂本身并不是领域的一部分，它只是用来创造聚合
 *
 * 当然有的人也把这个认为是领域的一部分，把factory这个包放在domain的包下，然后使用
 * 与lombok类似的builder方式对新建聚合做一层工具类的包装。我觉得太繁琐，为了形式而形式
 * 没必要，大多数场景下直接通过command的toDomain方法即可得到聚合，少数复杂聚合才需要工厂包装
 *
 * @author baiyan
 */
package com.baiyan.ddd.application.factory;