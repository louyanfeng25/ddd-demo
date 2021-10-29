/**
 * 数据组合，应用查询服务或者rpc的adapter的到的数据需要组合成一个复杂的实体
 * 不可通过实体一个个set，通过汇编器将各个数据源结果组合得到返回给前端的数据，
 * 业务含义更加清晰
 *
 * @author baiyan
 */
package com.baiyan.ddd.biz.user.application.assember;