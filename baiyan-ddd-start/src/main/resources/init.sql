create table t_user
(
    id           bigint auto_increment comment '主键'
        primary key,
    user_name    varchar(64)                             null comment '用户名',
    password     varchar(255)                            null comment '密码',
    real_name    varchar(64)                             null comment '真实姓名',
    phone        bigint                                  null comment '手机号',
    province     varchar(64)                             null comment '用户名',
    city         varchar(64)                             null comment '用户名',
    county       varchar(64)                             null comment '用户名',
    unit_id      bigint                                  null comment '单位id',
    role_ids     varchar(1024) default ''                not null comment '角色id，逗号间隔',
    gmt_create   datetime      default CURRENT_TIMESTAMP not null comment '创建时间',
    gmt_modified datetime      default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    deleted      bigint        default 0                 not null comment '是否删除，非0为已删除'
)
    comment '用户表' collate = utf8_bin;

create table t_role
(
    id           bigint auto_increment comment '主键'
        primary key,
    name         varchar(256)                       not null comment '名称',
    code         varchar(64)                        null comment '角色code',
    gmt_create   datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    gmt_modified datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    deleted      bigint   default 0                 not null comment '是否已删除'
)
    comment '角色表' charset = utf8;

INSERT INTO t_role (id, name, code, gmt_create, gmt_modified, deleted) VALUES (1, '超级管理员', 'super_admin', '2021-09-10 19:27:55', '2021-09-10 19:27:55', 0);
INSERT INTO t_role (id, name, code, gmt_create, gmt_modified, deleted) VALUES (2, '管理员', 'admin', '2021-09-10 19:27:55', '2021-09-10 19:27:55', 0);
INSERT INTO t_role (id, name, code, gmt_create, gmt_modified, deleted) VALUES (3, '普通用户', 'user', '2021-09-10 19:27:56', '2021-09-10 19:27:56', 0);