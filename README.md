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
│   ├── src
│   │   └── main
│   │       └── java
│   │           └── com
│   │               └── baiyan
│   │                   └── ddd
│   │                       └── api
│   │                           └── package-info.java
│   └── target
│       ├── baiyan-ddd-api-0.0.1-SNAPSHOT-sources.jar
│       ├── baiyan-ddd-api-0.0.1-SNAPSHOT.jar
│       ├── classes
│       ├── generated-sources
│       │   └── annotations
│       ├── maven-archiver
│       │   └── pom.properties
│       └── maven-status
│           └── maven-compiler-plugin
│               └── compile
│                   └── default-compile
│                       ├── createdFiles.lst
│                       └── inputFiles.lst
├── baiyan-ddd-base
│   ├── baiyan-ddd-base.iml
│   ├── pom.xml
│   ├── src
│   │   └── main
│   │       └── java
│   │           └── com
│   │               └── baiyan
│   │                   └── ddd
│   │                       └── base
│   │                           ├── exception
│   │                           │   ├── ServiceException.java
│   │                           │   └── ValidationException.java
│   │                           ├── model
│   │                           │   ├── ddd
│   │                           │   │   ├── AggregateRoot.java
│   │                           │   │   ├── ApplicationService.java
│   │                           │   │   ├── Command.java
│   │                           │   │   ├── DomainService.java
│   │                           │   │   ├── Entity.java
│   │                           │   │   ├── Factory.java
│   │                           │   │   ├── MarkerInterface.java
│   │                           │   │   ├── QueryApplicationService.java
│   │                           │   │   ├── Repository.java
│   │                           │   │   ├── Representation.java
│   │                           │   │   └── ValueObject.java
│   │                           │   ├── event
│   │                           │   │   └── BaseDomainEvent.java
│   │                           │   ├── query
│   │                           │   │   ├── KeywordQuery.java
│   │                           │   │   └── PageQuery.java
│   │                           │   └── result
│   │                           │       ├── BaseResult.java
│   │                           │       ├── BaseUuidEntity.java
│   │                           │       ├── Page.java
│   │                           │       ├── PageResult.java
│   │                           │       └── Result.java
│   │                           ├── package-info.java
│   │                           └── util
│   │                               ├── GsonUtil.java
│   │                               └── ValidationUtil.java
│   └── target
│       ├── baiyan-ddd-base-0.0.1-SNAPSHOT-sources.jar
│       ├── baiyan-ddd-base-0.0.1-SNAPSHOT.jar
│       ├── classes
│       │   └── com
│       │       └── baiyan
│       │           └── ddd
│       │               └── base
│       │                   ├── exception
│       │                   │   ├── ServiceException.class
│       │                   │   └── ValidationException.class
│       │                   ├── model
│       │                   │   ├── ddd
│       │                   │   │   ├── AggregateRoot.class
│       │                   │   │   ├── ApplicationService.class
│       │                   │   │   ├── Command.class
│       │                   │   │   ├── DomainService.class
│       │                   │   │   ├── Entity.class
│       │                   │   │   ├── Factory.class
│       │                   │   │   ├── MarkerInterface.class
│       │                   │   │   ├── QueryApplicationService.class
│       │                   │   │   ├── Repository.class
│       │                   │   │   ├── Representation.class
│       │                   │   │   └── ValueObject.class
│       │                   │   ├── event
│       │                   │   │   └── BaseDomainEvent.class
│       │                   │   ├── query
│       │                   │   │   ├── KeywordQuery.class
│       │                   │   │   └── PageQuery.class
│       │                   │   └── result
│       │                   │       ├── BaseResult.class
│       │                   │       ├── BaseUuidEntity.class
│       │                   │       ├── Page.class
│       │                   │       ├── PageResult.class
│       │                   │       └── Result.class
│       │                   └── util
│       │                       ├── GsonUtil$1.class
│       │                       ├── GsonUtil$2.class
│       │                       ├── GsonUtil.class
│       │                       └── ValidationUtil.class
│       ├── generated-sources
│       │   └── annotations
│       ├── maven-archiver
│       │   └── pom.properties
│       └── maven-status
│           └── maven-compiler-plugin
│               └── compile
│                   └── default-compile
│                       ├── createdFiles.lst
│                       └── inputFiles.lst
├── baiyan-ddd-core
│   ├── baiyan-ddd-core.iml
│   ├── pom.xml
│   ├── src
│   │   └── main
│   │       ├── java
│   │       │   └── com
│   │       │       └── baiyan
│   │       │           └── ddd
│   │       │               └── core
│   │       │                   ├── infrastructure
│   │       │                   │   ├── adapter
│   │       │                   │   │   ├── UnitAdapter.java
│   │       │                   │   │   ├── model
│   │       │                   │   │   │   ├── dto
│   │       │                   │   │   │   │   └── UnitDTO.java
│   │       │                   │   │   │   └── package-info.java
│   │       │                   │   │   └── package-info.java
│   │       │                   │   ├── common
│   │       │                   │   │   ├── config
│   │       │                   │   │   │   ├── SpringMessageSourceErrorMessageSource.java
│   │       │                   │   │   │   └── package-info.java
│   │       │                   │   │   └── event
│   │       │                   │   │       ├── DomainEventPublisher.java
│   │       │                   │   │       └── DomainEventPublisherImpl.java
│   │       │                   │   ├── constant
│   │       │                   │   │   └── package-info.java
│   │       │                   │   ├── event
│   │       │                   │   │   ├── UserEventHandler.java
│   │       │                   │   │   └── package-info.java
│   │       │                   │   ├── mq
│   │       │                   │   │   └── producer
│   │       │                   │   │       └── package-info.java
│   │       │                   │   ├── query
│   │       │                   │   │   ├── assember
│   │       │                   │   │   │   └── package-info.java
│   │       │                   │   │   ├── dto
│   │       │                   │   │   │   ├── UserPageDTO.java
│   │       │                   │   │   │   └── package-info.java
│   │       │                   │   │   ├── impl
│   │       │                   │   │   │   ├── UserQueryRepositoryImpl.java
│   │       │                   │   │   │   └── package-info.java
│   │       │                   │   │   ├── package-info.java
│   │       │                   │   │   └── query
│   │       │                   │   │       └── package-info.java
│   │       │                   │   ├── repository
│   │       │                   │   │   ├── UserRepositoryImpl.java
│   │       │                   │   │   ├── converter
│   │       │                   │   │   │   ├── UserConverter.java
│   │       │                   │   │   │   └── package-info.java
│   │       │                   │   │   ├── mapper
│   │       │                   │   │   │   ├── RoleMapper.java
│   │       │                   │   │   │   ├── UserMapper.java
│   │       │                   │   │   │   ├── UserRoleMapper.java
│   │       │                   │   │   │   └── package-info.java
│   │       │                   │   │   ├── package-info.java
│   │       │                   │   │   └── po
│   │       │                   │   │       ├── RolePO.java
│   │       │                   │   │       ├── UserPO.java
│   │       │                   │   │       ├── UserRolePO.java
│   │       │                   │   │       └── package-info.java
│   │       │                   │   └── rpc
│   │       │                   │       ├── UnitApi.java
│   │       │                   │       ├── UnitInfoDTO.java
│   │       │                   │       └── package-info.java
│   │       │                   └── user
│   │       │                       ├── Address.java
│   │       │                       ├── Role.java
│   │       │                       ├── Unit.java
│   │       │                       ├── User.java
│   │       │                       ├── UserApplicationService.java
│   │       │                       ├── UserDomainService.java
│   │       │                       ├── UserQueryApplicationService.java
│   │       │                       ├── UserQueryRepository.java
│   │       │                       ├── UserRepository.java
│   │       │                       ├── command
│   │       │                       │   ├── BaseUserCommand.java
│   │       │                       │   ├── CreateUserCommand.java
│   │       │                       │   ├── UpdateUserCommand.java
│   │       │                       │   └── package-info.java
│   │       │                       ├── event
│   │       │                       │   ├── UserCreateEvent.java
│   │       │                       │   ├── UserDeleteEvent.java
│   │       │                       │   ├── UserUpdateEvent.java
│   │       │                       │   └── package-info.java
│   │       │                       ├── exception
│   │       │                       │   └── package-info.java
│   │       │                       ├── factory
│   │       │                       │   └── package-info.java
│   │       │                       ├── impl
│   │       │                       │   ├── UserApplicationServiceImpl.java
│   │       │                       │   ├── UserDomainServiceImpl.java
│   │       │                       │   └── package-info.java
│   │       │                       └── package-info.java
│   │       └── resources
│   │           └── mapper
│   │               └── UserMapper.xml
│   └── target
│       ├── baiyan-ddd-core-0.0.1-SNAPSHOT-sources.jar
│       ├── baiyan-ddd-core-0.0.1-SNAPSHOT.jar
│       ├── classes
│       │   ├── com
│       │   │   └── baiyan
│       │   │       └── ddd
│       │   │           └── core
│       │   │               ├── infrastructure
│       │   │               │   ├── adapter
│       │   │               │   │   ├── UnitAdapter.class
│       │   │               │   │   └── model
│       │   │               │   │       └── dto
│       │   │               │   │           └── UnitDTO.class
│       │   │               │   ├── common
│       │   │               │   │   ├── config
│       │   │               │   │   │   └── SpringMessageSourceErrorMessageSource.class
│       │   │               │   │   └── event
│       │   │               │   │       ├── DomainEventPublisher.class
│       │   │               │   │       └── DomainEventPublisherImpl.class
│       │   │               │   ├── event
│       │   │               │   │   └── UserEventHandler.class
│       │   │               │   ├── query
│       │   │               │   │   ├── dto
│       │   │               │   │   │   └── UserPageDTO.class
│       │   │               │   │   └── impl
│       │   │               │   │       └── UserQueryRepositoryImpl.class
│       │   │               │   ├── repository
│       │   │               │   │   ├── UserRepositoryImpl.class
│       │   │               │   │   ├── converter
│       │   │               │   │   │   └── UserConverter.class
│       │   │               │   │   ├── mapper
│       │   │               │   │   │   ├── RoleMapper.class
│       │   │               │   │   │   ├── UserMapper.class
│       │   │               │   │   │   └── UserRoleMapper.class
│       │   │               │   │   └── po
│       │   │               │   │       ├── RolePO$RolePOBuilder.class
│       │   │               │   │       ├── RolePO.class
│       │   │               │   │       ├── UserPO.class
│       │   │               │   │       ├── UserRolePO$UserRolePOBuilder.class
│       │   │               │   │       └── UserRolePO.class
│       │   │               │   └── rpc
│       │   │               │       ├── UnitApi.class
│       │   │               │       └── UnitInfoDTO.class
│       │   │               └── user
│       │   │                   ├── Address.class
│       │   │                   ├── Role.class
│       │   │                   ├── Unit.class
│       │   │                   ├── User.class
│       │   │                   ├── UserApplicationService.class
│       │   │                   ├── UserDomainService.class
│       │   │                   ├── UserQueryApplicationService.class
│       │   │                   ├── UserQueryRepository.class
│       │   │                   ├── UserRepository.class
│       │   │                   ├── command
│       │   │                   │   ├── BaseUserCommand.class
│       │   │                   │   ├── CreateUserCommand.class
│       │   │                   │   └── UpdateUserCommand.class
│       │   │                   ├── event
│       │   │                   │   ├── UserCreateEvent.class
│       │   │                   │   ├── UserDeleteEvent.class
│       │   │                   │   └── UserUpdateEvent.class
│       │   │                   └── impl
│       │   │                       ├── UserApplicationServiceImpl.class
│       │   │                       └── UserDomainServiceImpl.class
│       │   └── mapper
│       │       └── UserMapper.xml
│       ├── generated-sources
│       │   └── annotations
│       ├── maven-archiver
│       │   └── pom.properties
│       └── maven-status
│           └── maven-compiler-plugin
│               └── compile
│                   └── default-compile
│                       ├── createdFiles.lst
│                       └── inputFiles.lst
├── baiyan-ddd-interaction
│   ├── baiyan-ddd-interaction.iml
│   ├── pom.xml
│   ├── src
│   │   └── main
│   │       └── java
│   │           └── com
│   │               └── baiyan
│   │                   └── ddd
│   │                       └── interaction
│   │                           ├── api
│   │                           │   └── package-info.java
│   │                           ├── config
│   │                           │   ├── GlobalExceptionHandler.java
│   │                           │   └── package-info.java
│   │                           ├── controller
│   │                           │   ├── UserController.java
│   │                           │   └── package-info.java
│   │                           ├── mq
│   │                           │   └── package-info.java
│   │                           └── package-info.java
│   └── target
│       ├── baiyan-ddd-interaction-0.0.1-SNAPSHOT-sources.jar
│       ├── baiyan-ddd-interaction-0.0.1-SNAPSHOT.jar
│       ├── classes
│       │   └── com
│       │       └── baiyan
│       │           └── ddd
│       │               └── interaction
│       │                   ├── config
│       │                   │   └── GlobalExceptionHandler.class
│       │                   └── controller
│       │                       └── UserController.class
│       ├── generated-sources
│       │   └── annotations
│       ├── maven-archiver
│       │   └── pom.properties
│       └── maven-status
│           └── maven-compiler-plugin
│               └── compile
│                   └── default-compile
│                       ├── createdFiles.lst
│                       └── inputFiles.lst
├── baiyan-ddd-rpc
│   ├── baiyan-ddd-rpc.iml
│   ├── pom.xml
│   ├── src
│   │   └── main
│   │       └── java
│   │           └── com.baiyan.ddd.rpc
│   │               └── package-info.java
│   └── target
│       ├── baiyan-ddd-rpc-0.0.1-SNAPSHOT-sources.jar
│       ├── baiyan-ddd-rpc-0.0.1-SNAPSHOT.jar
│       ├── classes
│       ├── generated-sources
│       │   └── annotations
│       ├── maven-archiver
│       │   └── pom.properties
│       └── maven-status
│           └── maven-compiler-plugin
│               └── compile
│                   └── default-compile
│                       ├── createdFiles.lst
│                       └── inputFiles.lst
├── baiyan-ddd-start
│   ├── baiyan-ddd-start.iml
│   ├── pom.xml
│   ├── src
│   │   └── main
│   │       ├── java
│   │       │   └── com
│   │       │       └── baiyan
│   │       │           └── ddd
│   │       │               └── BaiyanDDDApplication.java
│   │       └── resources
│   │           ├── application.yml
│   │           ├── init.sql
│   │           └── messages.properties
│   └── target
│       ├── baiyan-ddd-start-0.0.1-SNAPSHOT-sources.jar
│       ├── baiyan-ddd-start-0.0.1-SNAPSHOT.jar
│       ├── baiyan-ddd-start-0.0.1-SNAPSHOT.jar.original
│       ├── classes
│       │   ├── application.yml
│       │   ├── com
│       │   │   └── baiyan
│       │   │       └── ddd
│       │   │           └── BaiyanDDDApplication.class
│       │   ├── init.sql
│       │   └── messages.properties
│       ├── generated-sources
│       │   └── annotations
│       ├── maven-archiver
│       │   └── pom.properties
│       └── maven-status
│           └── maven-compiler-plugin
│               └── compile
│                   └── default-compile
│                       ├── createdFiles.lst
│                       └── inputFiles.lst
├── ddd.iml
└── pom.xml

184 directories, 210 files
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
│                               │   │   ├── AggregateRoot.java
│                               │   │   ├── ApplicationService.java
│                               │   │   ├── Command.java
│                               │   │   ├── DomainService.java
│                               │   │   ├── Entity.java
│                               │   │   ├── Factory.java
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
│           │                   │   │   ├── model
│           │                   │   │   │   ├── dto
│           │                   │   │   │   │   └── UnitDTO.java
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
│           │                   │   │   ├── UserEventHandler.java
│           │                   │   │   └── package-info.java
│           │                   │   ├── mq
│           │                   │   │   └── producer
│           │                   │   │       └── package-info.java
│           │                   │   ├── query
│           │                   │   │   ├── assember
│           │                   │   │   │   └── package-info.java
│           │                   │   │   ├── dto
│           │                   │   │   │   ├── UserPageDTO.java
│           │                   │   │   │   └── package-info.java
│           │                   │   │   ├── impl
│           │                   │   │   │   ├── UserQueryRepositoryImpl.java
│           │                   │   │   │   └── package-info.java
│           │                   │   │   ├── package-info.java
│           │                   │   │   └── query
│           │                   │   │       └── package-info.java
│           │                   │   ├── repository
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
│           │                       ├── Address.java
│           │                       ├── Role.java
│           │                       ├── Unit.java
│           │                       ├── User.java
│           │                       ├── UserApplicationService.java
│           │                       ├── UserDomainService.java
│           │                       ├── UserQueryApplicationService.java
│           │                       ├── UserQueryRepository.java
│           │                       ├── UserRepository.java
│           │                       ├── command
│           │                       │   ├── BaseUserCommand.java
│           │                       │   ├── CreateUserCommand.java
│           │                       │   ├── UpdateUserCommand.java
│           │                       │   └── package-info.java
│           │                       ├── event
│           │                       │   ├── UserCreateEvent.java
│           │                       │   ├── UserDeleteEvent.java
│           │                       │   ├── UserUpdateEvent.java
│           │                       │   └── package-info.java
│           │                       ├── exception
│           │                       │   └── package-info.java
│           │                       ├── factory
│           │                       │   └── package-info.java
│           │                       ├── impl
│           │                       │   ├── UserApplicationServiceImpl.java
│           │                       │   ├── UserDomainServiceImpl.java
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