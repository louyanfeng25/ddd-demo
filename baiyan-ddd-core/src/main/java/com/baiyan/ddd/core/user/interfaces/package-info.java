/**
 * 灰度层，基于依赖倒置与限界的原则，0信任外部方法所传入的参数
 *
 * 例如领域层需要调用其他领域的应用服务，领域服务或者interface：
 * 1.领域层不可直达，需要调用本层的interface。
 * 2.interface定义接口，实现在基础设施层里面。
 * 3.由基础设施层来完成数据转换调用逻辑，实际的业务处理还是交给领域服务完成
 *
 * interface为灰度层，为了适配DDD六边形架构，为防腐层。因此突破一点北向与南向的原则，
 * 应用服务与领域服务均可直接依赖
 *
 * @author baiyan
 */
package com.baiyan.ddd.core.user.interfaces;