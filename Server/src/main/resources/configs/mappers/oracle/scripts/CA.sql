prompt PL/SQL Developer import file
prompt Created on 2017年3月10日 by Administrator
set feedback off
set define off
prompt Dropping CA_ORGANIZATION...
drop table CA_ORGANIZATION cascade constraints;
prompt Dropping CA_PERMISSION...
drop table CA_PERMISSION cascade constraints;
prompt Dropping CA_ROLE...
drop table CA_ROLE cascade constraints;
prompt Dropping CA_ROLE_PERMISSION...
drop table CA_ROLE_PERMISSION cascade constraints;
prompt Dropping CA_USER...
drop table CA_USER cascade constraints;
prompt Dropping CA_USER_ROLE...
drop table CA_USER_ROLE cascade constraints;
prompt Creating CA_ORGANIZATION...
create table CA_ORGANIZATION
(
  id          VARCHAR2(32) default sys_guid(),
  parent_id   VARCHAR2(32),
  name        VARCHAR2(100),
  code        VARCHAR2(20),
  description VARCHAR2(500),
  create_date TIMESTAMP(6),
  creater_id  VARCHAR2(32),
  update_date TIMESTAMP(6),
  updater_id  VARCHAR2(32),
  address     VARCHAR2(100),
  phone       VARCHAR2(15),
  email       VARCHAR2(50)
)
tablespace BM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CA_PERMISSION...
create table CA_PERMISSION
(
  id        VARCHAR2(32) default sys_guid() not null,
  parent_id VARCHAR2(32) not null,
  name      VARCHAR2(50) not null,
  code      VARCHAR2(50) not null,
  type      VARCHAR2(2) not null
)
tablespace BM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CA_ROLE...
create table CA_ROLE
(
  id          VARCHAR2(32) default sys_guid(),
  name        VARCHAR2(50),
  description VARCHAR2(500),
  code        VARCHAR2(20)
)
tablespace BM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CA_ROLE_PERMISSION...
create table CA_ROLE_PERMISSION
(
  id            VARCHAR2(32) default sys_guid() not null,
  role_id       VARCHAR2(32) not null,
  permission_id VARCHAR2(32) not null
)
tablespace BM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CA_USER...
create table CA_USER
(
  id              VARCHAR2(32) default SYS_GUID() not null,
  org_id          VARCHAR2(32) not null,
  login_name      VARCHAR2(30) not null,
  password        VARCHAR2(32) not null,
  job_num         VARCHAR2(20),
  create_date     TIMESTAMP(6),
  update_date     TIMESTAMP(6),
  last_login_date TIMESTAMP(6),
  username        VARCHAR2(50)
)
tablespace BM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Creating CA_USER_ROLE...
create table CA_USER_ROLE
(
  id      VARCHAR2(32) default sys_guid() not null,
  role_id VARCHAR2(32) not null,
  user_id VARCHAR2(32) not null
)
tablespace BM
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt Disabling triggers for CA_ORGANIZATION...
alter table CA_ORGANIZATION disable all triggers;
prompt Disabling triggers for CA_PERMISSION...
alter table CA_PERMISSION disable all triggers;
prompt Disabling triggers for CA_ROLE...
alter table CA_ROLE disable all triggers;
prompt Disabling triggers for CA_ROLE_PERMISSION...
alter table CA_ROLE_PERMISSION disable all triggers;
prompt Disabling triggers for CA_USER...
alter table CA_USER disable all triggers;
prompt Disabling triggers for CA_USER_ROLE...
alter table CA_USER_ROLE disable all triggers;
prompt Loading CA_ORGANIZATION...
insert into CA_ORGANIZATION (id, parent_id, name, code, description, create_date, creater_id, update_date, updater_id, address, phone, email)
values ('1', '0', '总部', 'zb', null, null, null, null, null, null, null, null);
insert into CA_ORGANIZATION (id, parent_id, name, code, description, create_date, creater_id, update_date, updater_id, address, phone, email)
values ('2', '1', '研发中心', 'yfzx', null, null, null, null, null, null, null, null);
insert into CA_ORGANIZATION (id, parent_id, name, code, description, create_date, creater_id, update_date, updater_id, address, phone, email)
values ('3', '1', '财务部', 'cwb', null, null, null, null, null, null, null, null);
insert into CA_ORGANIZATION (id, parent_id, name, code, description, create_date, creater_id, update_date, updater_id, address, phone, email)
values ('4', '1', '人事部', 'rsb', null, null, null, null, null, null, null, null);
insert into CA_ORGANIZATION (id, parent_id, name, code, description, create_date, creater_id, update_date, updater_id, address, phone, email)
values ('5', '2', '研发一部', 'yf1b', null, null, null, null, null, null, null, null);
insert into CA_ORGANIZATION (id, parent_id, name, code, description, create_date, creater_id, update_date, updater_id, address, phone, email)
values ('6', '2', '研发二部', 'yf2b', null, null, null, null, null, null, null, null);
insert into CA_ORGANIZATION (id, parent_id, name, code, description, create_date, creater_id, update_date, updater_id, address, phone, email)
values ('7', '2', '研发三部', 'yf3b', null, null, null, null, null, null, null, null);
commit;
prompt 7 records loaded
prompt Loading CA_PERMISSION...
insert into CA_PERMISSION (id, parent_id, name, code, type)
values ('C16C9D14488B42A99660966195347B9B', '0', '用户权限', 'user:*', '1');
insert into CA_PERMISSION (id, parent_id, name, code, type)
values ('E92BD668AF09476C954BB1F1463CFD43', 'C16C9D14488B42A99660966195347B9B', '用户添加', 'user:add', '1');
insert into CA_PERMISSION (id, parent_id, name, code, type)
values ('4E2EC92E4EE343CB8D095102C0E4650B', 'C16C9D14488B42A99660966195347B9B', '用户删除', 'user:delete', '1');
insert into CA_PERMISSION (id, parent_id, name, code, type)
values ('0E261E23C82E4CCDA75094FD87C9B422', 'C16C9D14488B42A99660966195347B9B', '用户修改', 'user:update', '1');
insert into CA_PERMISSION (id, parent_id, name, code, type)
values ('064440FD2B0C47A7BE949BAA1D77A538', 'C16C9D14488B42A99660966195347B9B', '用户查看', 'user:view', '1');
commit;
prompt 5 records loaded
prompt Loading CA_ROLE...
insert into CA_ROLE (id, name, description, code)
values ('1', '系统管理员', null, 'systemManager');
insert into CA_ROLE (id, name, description, code)
values ('2', '普通用户', null, 'NormalUser');
commit;
prompt 2 records loaded
prompt Loading CA_ROLE_PERMISSION...
insert into CA_ROLE_PERMISSION (id, role_id, permission_id)
values ('986C4E99C0A64F6FB05BEE83AA8D3F02', '1', '064440FD2B0C47A7BE949BAA1D77A538');
commit;
prompt 1 records loaded
prompt Loading CA_USER...
insert into CA_USER (id, org_id, login_name, password, job_num, create_date, update_date, last_login_date, username)
values ('C5F0CA07EA864022B1BF072DB4161119', '5', 'lisi', 'e10adc3949ba59abbe56e057f20f883e', 'No.113', null, null, to_timestamp('08-03-2017 09:14:13.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '李四');
insert into CA_USER (id, org_id, login_name, password, job_num, create_date, update_date, last_login_date, username)
values ('72F45FD93ACA428E99FF14D6416BC129', '6', 'wangwu', '123456', 'No.114', null, null, to_timestamp('08-03-2017 09:14:13.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '王五');
insert into CA_USER (id, org_id, login_name, password, job_num, create_date, update_date, last_login_date, username)
values ('8936AFF6DF2F404AB81042767248AFD3', '7', 'zhaoliu', '123456', 'No.115', null, null, to_timestamp('08-03-2017 09:14:13.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '赵六');
commit;
prompt 3 records loaded
prompt Loading CA_USER_ROLE...
insert into CA_USER_ROLE (id, role_id, user_id)
values ('E746174977064628A3ECC740B59920BB', '1', 'C5F0CA07EA864022B1BF072DB4161119');
insert into CA_USER_ROLE (id, role_id, user_id)
values ('B536DD016BA0403B87ED57BC69A95B51', '2', '72F45FD93ACA428E99FF14D6416BC129');
insert into CA_USER_ROLE (id, role_id, user_id)
values ('4F358EC132F44DE69C30442EF0E372E0', '2', '8936AFF6DF2F404AB81042767248AFD3');
insert into CA_USER_ROLE (id, role_id, user_id)
values ('CDCFAC27444C41AABF9EB3A68C292711', '2', 'C5F0CA07EA864022B1BF072DB4161119');
commit;
prompt 4 records loaded
prompt Enabling triggers for CA_ORGANIZATION...
alter table CA_ORGANIZATION enable all triggers;
prompt Enabling triggers for CA_PERMISSION...
alter table CA_PERMISSION enable all triggers;
prompt Enabling triggers for CA_ROLE...
alter table CA_ROLE enable all triggers;
prompt Enabling triggers for CA_ROLE_PERMISSION...
alter table CA_ROLE_PERMISSION enable all triggers;
prompt Enabling triggers for CA_USER...
alter table CA_USER enable all triggers;
prompt Enabling triggers for CA_USER_ROLE...
alter table CA_USER_ROLE enable all triggers;
set feedback on
set define on
prompt Done.
