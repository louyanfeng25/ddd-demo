# 一.前言

​		被好多读者给催DDD的demo，终于上线了！！！

​		还是以博客说的，文章以MVC迁移至DDD为核心诉求进行编写。

​		标准DDD对应的orm框架应为JPA，因为博客推文为MVC架构迁移DDD，考虑市面上使用较多的还是mybatis，因此本文采用mybatis-plus作为orm框架作为演示。

> 博客中贴图的代码结构与本demo略有出路，根据实际业务与合理性考虑，对结构做了修正，以demo为准。

# 二.系列博客

[一文带你落地DDD](https://juejin.cn/post/7004002483601145863)

[DDD落地之事件驱动模型](https://juejin.cn/post/7005175434555949092)	

# 三.包结构

```
.
├── README.md
├── baiyan-ddd-api
│   ├── baiyan-ddd-api.iml
│   ├── pom.xml
│   └── src
│       └── main
│           └── java
│               └── com
│                   └── baiyan
│                       └── ddd
│                           └── api
│                               └── package-info.java
├── baiyan-ddd-base
│   ├── baiyan-ddd-base.iml
│   ├── pom.xml
│   └── src
│       └── main
│           └── java
│               └── com
│                   └── baiyan
│                       └── ddd
│                           └── base
│                               ├── exception
│                               │   ├── ServiceException.java
│                               │   └── ValidationException.java
│                               ├── model
│                               │   ├── ddd
│                               │   │   ├── Adapter.java
│                               │   │   ├── AggregateRoot.java
│                               │   │   ├── ApplicationService.java
│                               │   │   ├── Command.java
│                               │   │   ├── DomainService.java
│                               │   │   ├── Entity.java
│                               │   │   ├── Factory.java
│                               │   │   ├── Interface.java
│                               │   │   ├── MarkerInterface.java
│                               │   │   ├── QueryApplicationService.java
│                               │   │   ├── Repository.java
│                               │   │   ├── Representation.java
│                               │   │   └── ValueObject.java
│                               │   ├── event
│                               │   │   └── BaseDomainEvent.java
│                               │   ├── query
│                               │   │   ├── KeywordQuery.java
│                               │   │   └── PageQuery.java
│                               │   └── result
│                               │       ├── BaseResult.java
│                               │       ├── BaseUuidEntity.java
│                               │       ├── Page.java
│                               │       ├── PageResult.java
│                               │       └── Result.java
│                               ├── package-info.java
│                               └── util
│                                   ├── GsonUtil.java
│                                   └── ValidationUtil.java
├── baiyan-ddd-core
│   ├── baiyan-ddd-core.iml
│   ├── pom.xml
│   └── src
│       └── main
│           ├── java
│           │   └── com
│           │       └── baiyan
│           │           └── ddd
│           │               └── core
│           │                   ├── infrastructure
│           │                   │   ├── adapter
│           │                   │   │   ├── UnitAdapter.java
│           │                   │   │   ├── impl
│           │                   │   │   │   └── UnitAdapterImpl.java
│           │                   │   │   ├── model
│           │                   │   │   │   ├── UnitDTO.java
│           │                   │   │   │   └── package-info.java
│           │                   │   │   └── package-info.java
│           │                   │   ├── common
│           │                   │   │   ├── config
│           │                   │   │   │   ├── SpringMessageSourceErrorMessageSource.java
│           │                   │   │   │   └── package-info.java
│           │                   │   │   └── event
│           │                   │   │       ├── DomainEventPublisher.java
│           │                   │   │       └── DomainEventPublisherImpl.java
│           │                   │   ├── constant
│           │                   │   │   └── package-info.java
│           │                   │   ├── event
│           │                   │   │   └── package-info.java
│           │                   │   ├── interfaces
│           │                   │   │   ├── UserInterfaceImpl.java
│           │                   │   │   └── package-info.java
│           │                   │   ├── mq
│           │                   │   │   └── producer
│           │                   │   │       └── package-info.java
│           │                   │   ├── repository
│           │                   │   │   ├── UserQueryRepositoryImpl.java
│           │                   │   │   ├── UserRepositoryImpl.java
│           │                   │   │   ├── converter
│           │                   │   │   │   ├── UserConverter.java
│           │                   │   │   │   └── package-info.java
│           │                   │   │   ├── mapper
│           │                   │   │   │   ├── RoleMapper.java
│           │                   │   │   │   ├── UserMapper.java
│           │                   │   │   │   ├── UserRoleMapper.java
│           │                   │   │   │   └── package-info.java
│           │                   │   │   ├── package-info.java
│           │                   │   │   └── po
│           │                   │   │       ├── RolePO.java
│           │                   │   │       ├── UserPO.java
│           │                   │   │       ├── UserRolePO.java
│           │                   │   │       └── package-info.java
│           │                   │   └── rpc
│           │                   │       ├── UnitApi.java
│           │                   │       ├── UnitInfoDTO.java
│           │                   │       └── package-info.java
│           │                   └── user
│           │                       ├── application
│           │                       │   ├── assember
│           │                       │   │   └── package-info.java
│           │                       │   ├── listener
│           │                       │   │   ├── UserEventHandler.java
│           │                       │   │   └── package-info.java
│           │                       │   ├── model
│           │                       │   │   ├── command
│           │                       │   │   │   ├── BaseUserCommand.java
│           │                       │   │   │   ├── CreateUserCommand.java
│           │                       │   │   │   ├── UpdateUserCommand.java
│           │                       │   │   │   └── package-info.java
│           │                       │   │   ├── dto
│           │                       │   │   │   ├── UserPageDTO.java
│           │                       │   │   │   └── package-info.java
│           │                       │   │   └── query
│           │                       │   │       └── package-info.java
│           │                       │   ├── repository
│           │                       │   │   └── UserQueryRepository.java
│           │                       │   └── service
│           │                       │       ├── UserApplicationService.java
│           │                       │       ├── UserQueryApplicationService.java
│           │                       │       └── impl
│           │                       │           └── UserApplicationServiceImpl.java
│           │                       ├── domain
│           │                       │   ├── event
│           │                       │   │   ├── UserCreateEvent.java
│           │                       │   │   ├── UserDeleteEvent.java
│           │                       │   │   ├── UserUpdateEvent.java
│           │                       │   │   └── package-info.java
│           │                       │   ├── factory
│           │                       │   │   ├── UserFactory.java
│           │                       │   │   └── package-info.java
│           │                       │   ├── model
│           │                       │   │   ├── Address.java
│           │                       │   │   ├── Role.java
│           │                       │   │   ├── Unit.java
│           │                       │   │   └── User.java
│           │                       │   ├── repository
│           │                       │   │   └── UserRepository.java
│           │                       │   └── service
│           │                       │       ├── UserDomainService.java
│           │                       │       └── impl
│           │                       │           └── UserDomainServiceImpl.java
│           │                       ├── exception
│           │                       │   └── package-info.java
│           │                       ├── interfaces
│           │                       │   ├── UserInterface.java
│           │                       │   └── package-info.java
│           │                       └── package-info.java
│           └── resources
│               └── mapper
│                   └── UserMapper.xml
├── baiyan-ddd-interaction
│   ├── baiyan-ddd-interaction.iml
│   ├── pom.xml
│   └── src
│       └── main
│           └── java
│               └── com
│                   └── baiyan
│                       └── ddd
│                           └── interaction
│                               ├── api
│                               │   └── package-info.java
│                               ├── config
│                               │   ├── GlobalExceptionHandler.java
│                               │   └── package-info.java
│                               ├── controller
│                               │   ├── UserController.java
│                               │   └── package-info.java
│                               ├── mq
│                               │   └── package-info.java
│                               └── package-info.java
├── baiyan-ddd-rpc
│   ├── baiyan-ddd-rpc.iml
│   ├── pom.xml
│   └── src
│       └── main
│           └── java
│               └── com.baiyan.ddd.rpc
│                   └── package-info.java
├── baiyan-ddd-start
│   ├── baiyan-ddd-start.iml
│   ├── pom.xml
│   └── src
│       └── main
│           ├── java
│           │   └── com
│           │       └── baiyan
│           │           └── ddd
│           │               └── BaiyanDDDApplication.java
│           └── resources
│               ├── application.yml
│               ├── init.sql
│               └── messages.properties
├── ddd.iml
└── pom.xml
```



# 四.更多DDD学习资料

**博客资料:**

[ThoughtWork DDD系列](https://insights.thoughtworks.cn/tag/domain-driven-design/)

[张逸 DDD系列](https://cloud.tencent.com/developer/user/1327231)

[欧创新 DDD系列](https://www.infoq.cn/article/s_LFUlU6ZQODd030RbH9)

**代码示例：**

[阿里COLA](https://github.com/alibaba/COLA)

> <https://github.com/citerus/dddsample-core>

> <https://github.com/YaoLin1/ddddemo>

> <https://github.com/ddd-by-examples/factory>

> <https://github.com/Sayi/ddd-cargo>



# 五.特别鸣谢

[lilpilot](https://postcard.lilpilot.co/)

# 六.联系我

demo如有不正确之处，欢迎指正，别忘了star哦~~~

钉钉：louyanfeng25

微信：baiyan_lou

微信公众号：柏炎大叔