-- Project Name : 社員DB
-- Date/Time    : 2019/12/05 3:04:16
-- Author       : ryzen
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

/*
  BackupToTempTable, RestoreFromTempTable疑似命令が付加されています。
  これにより、drop table, create table 後もデータが残ります。
  この機能は一時的に $$TableName のような一時テーブルを作成します。
*/

-- 組織マスタ
--* BackupToTempTable
drop table if exists MST_ORGANIZATION cascade;

--* RestoreFromTempTable
create table MST_ORGANIZATION (
  ORGANIZATION_ID character varying not null
  , ORGANIZATION_NAME character varying not null
  , ORGANIZATION_COMMENT character varying
  , ORGANIZATION_PARENT_ID character varying
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD character varying
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD character varying
  , constraint MST_ORGANIZATION_PKC primary key (ORGANIZATION_ID)
) ;

-- 機能マスタ
--* BackupToTempTable
drop table if exists MST_FUNCTION cascade;

--* RestoreFromTempTable
create table MST_FUNCTION (
  FUNCTION_ID character varying not null
  , FUNCTION_NAME character varying not null
  , FUNCTION_PATH character varying not null
  , FUNCTION_COMMENT character varying
  , AUTHORIZED_ID character varying
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD character varying
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD character varying
  , constraint MST_FUNCTION_PKC primary key (FUNCTION_ID,FUNCTION_NAME)
) ;

-- メニューマスタ
--* BackupToTempTable
drop table if exists MST_MENU cascade;

--* RestoreFromTempTable
create table MST_MENU (
  HIERARCHY character varying not null
  , FUNCTION_ORDER integer not null
  , FUNCTION_ID character varying
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD character varying
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD character varying
  , constraint MST_MENU_PKC primary key (HIERARCHY)
) ;

-- 権限マスタ
--* BackupToTempTable
drop table if exists MST_AUTHORIZED cascade;

--* RestoreFromTempTable
create table MST_AUTHORIZED (
  AUTHORIZED_ID character varying not null
  , AUTHORIZED_NAME character varying not null
  , AUTHORIZED_COMMENT character varying
  , AUTHORIZED_LEVEL integer default 0 not null
  , ORGANIZETION_ID character varying not null
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD character varying
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD character varying
  , constraint MST_AUTHORIZED_PKC primary key (AUTHORIZED_ID,ORGANIZETION_ID)
) ;

-- 区分グループマスタ
--* BackupToTempTable
drop table if exists MST_SECTION_GROUP cascade;

--* RestoreFromTempTable
create table MST_SECTION_GROUP (
  SECTION_GROUP_ID character varying not null
  , SECTION_GROUP_NAME character varying not null
  , SECTION_GROUP_COMMENT character varying
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD character varying
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD character varying
  , constraint MST_SECTION_GROUP_PKC primary key (SECTION_GROUP_ID)
) ;

-- 区分マスタ
--* BackupToTempTable
drop table if exists MST_SECTION cascade;

--* RestoreFromTempTable
create table MST_SECTION (
  SECTION_GROUP_ID character varying not null
  , SECTION_ID character varying not null
  , SECTION_NAME character varying not null
  , SECTION_COMMENT character varying
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD character varying
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD character varying
  , constraint MST_SECTION_PKC primary key (SECTION_GROUP_ID,SECTION_ID)
) ;

-- 社員マスタ
--* BackupToTempTable
drop table if exists MST_EMPLOYEE cascade;

--* RestoreFromTempTable
create table MST_EMPLOYEE (
  EMPLOYEE_ID character varying not null
  , EMPLOYEE_NAME character varying not null
  , BIRTH_YMD character varying not null
  , SEX character not null
  , JOINED_YMD character varying not null
  , DEPARTMENT_ID character varying
  , AUTHORIZED character default 0 not null
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD character varying
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD character varying
  , constraint MST_EMPLOYEE_PKC primary key (EMPLOYEE_ID)
) ;

-- 認証ユーザ
--* BackupToTempTable
drop table if exists AUTHORIZED_USER cascade;

--* RestoreFromTempTable
create table AUTHORIZED_USER (
  USER_ID character varying not null
  , USER_PASSWORD character varying not null
  , ORGANIZATION_ID character varying not null
  , AUTHORIZED_GROUP_ID character varying not null
  , LAST_UPDATE_YMD character varying
  , LAST_UPDATE_PASS character varying
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD character varying
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD character varying
  , constraint AUTHORIZED_USER_PKC primary key (USER_ID)
) ;

comment on table MST_ORGANIZATION is '組織マスタ';
comment on column MST_ORGANIZATION.ORGANIZATION_ID is '組織ＩＤ';
comment on column MST_ORGANIZATION.ORGANIZATION_NAME is '組織名称';
comment on column MST_ORGANIZATION.ORGANIZATION_COMMENT is '組織説明';
comment on column MST_ORGANIZATION.ORGANIZATION_PARENT_ID is '親組織ＩＤ';
comment on column MST_ORGANIZATION.DELETE_FLG is '削除フラグ:0:未削除、1:削除';
comment on column MST_ORGANIZATION.CREATE_MODULE_ID is '作成モジュールＩＤ';
comment on column MST_ORGANIZATION.CREATE_USER_ID is '作成ユーザＩＤ';
comment on column MST_ORGANIZATION.CREATE_YMD is '作成年月日';
comment on column MST_ORGANIZATION.UPDATE_MODULE_ID is '更新モジュールＩＤ';
comment on column MST_ORGANIZATION.UPDATE_USER_ID is '更新ユーザＩＤ';
comment on column MST_ORGANIZATION.UPDATE_YMD is '更新年月日';

comment on table MST_FUNCTION is '機能マスタ';
comment on column MST_FUNCTION.FUNCTION_ID is '機能ＩＤ';
comment on column MST_FUNCTION.FUNCTION_NAME is '機能名称';
comment on column MST_FUNCTION.FUNCTION_PATH is '機能パス';
comment on column MST_FUNCTION.FUNCTION_COMMENT is '機能説明';
comment on column MST_FUNCTION.AUTHORIZED_ID is '権限ＩＤ';
comment on column MST_FUNCTION.DELETE_FLG is '削除フラグ:0:未削除、1:削除';
comment on column MST_FUNCTION.CREATE_MODULE_ID is '作成モジュールＩＤ';
comment on column MST_FUNCTION.CREATE_USER_ID is '作成ユーザＩＤ';
comment on column MST_FUNCTION.CREATE_YMD is '作成年月日';
comment on column MST_FUNCTION.UPDATE_MODULE_ID is '更新モジュールＩＤ';
comment on column MST_FUNCTION.UPDATE_USER_ID is '更新ユーザＩＤ';
comment on column MST_FUNCTION.UPDATE_YMD is '更新年月日';

comment on table MST_MENU is 'メニューマスタ';
comment on column MST_MENU.HIERARCHY is '階層';
comment on column MST_MENU.FUNCTION_ORDER is '順序';
comment on column MST_MENU.FUNCTION_ID is '機能ＩＤ';
comment on column MST_MENU.DELETE_FLG is '削除フラグ:0:未削除、1:削除';
comment on column MST_MENU.CREATE_MODULE_ID is '作成モジュールＩＤ';
comment on column MST_MENU.CREATE_USER_ID is '作成ユーザＩＤ';
comment on column MST_MENU.CREATE_YMD is '作成年月日';
comment on column MST_MENU.UPDATE_MODULE_ID is '更新モジュールＩＤ';
comment on column MST_MENU.UPDATE_USER_ID is '更新ユーザＩＤ';
comment on column MST_MENU.UPDATE_YMD is '更新年月日';

comment on table MST_AUTHORIZED is '権限マスタ';
comment on column MST_AUTHORIZED.AUTHORIZED_ID is '権限ＩＤ';
comment on column MST_AUTHORIZED.AUTHORIZED_NAME is '権限名称';
comment on column MST_AUTHORIZED.AUTHORIZED_COMMENT is '権限コメント';
comment on column MST_AUTHORIZED.AUTHORIZED_LEVEL is '権限レベル:TDB';
comment on column MST_AUTHORIZED.ORGANIZETION_ID is '組織ＩＤ';
comment on column MST_AUTHORIZED.DELETE_FLG is '削除フラグ:0:未削除、1:削除';
comment on column MST_AUTHORIZED.CREATE_MODULE_ID is '作成モジュールＩＤ';
comment on column MST_AUTHORIZED.CREATE_USER_ID is '作成ユーザＩＤ';
comment on column MST_AUTHORIZED.CREATE_YMD is '作成年月日';
comment on column MST_AUTHORIZED.UPDATE_MODULE_ID is '更新モジュールＩＤ';
comment on column MST_AUTHORIZED.UPDATE_USER_ID is '更新ユーザＩＤ';
comment on column MST_AUTHORIZED.UPDATE_YMD is '更新年月日';

comment on table MST_SECTION_GROUP is '区分グループマスタ:区分グループ情報';
comment on column MST_SECTION_GROUP.SECTION_GROUP_ID is '区分グループＩＤ';
comment on column MST_SECTION_GROUP.SECTION_GROUP_NAME is '区分グループ名称';
comment on column MST_SECTION_GROUP.SECTION_GROUP_COMMENT is '区分グループコメント';
comment on column MST_SECTION_GROUP.DELETE_FLG is '削除フラグ:0:未削除、1:削除';
comment on column MST_SECTION_GROUP.CREATE_MODULE_ID is '作成モジュールＩＤ';
comment on column MST_SECTION_GROUP.CREATE_USER_ID is '作成ユーザＩＤ';
comment on column MST_SECTION_GROUP.CREATE_YMD is '作成年月日';
comment on column MST_SECTION_GROUP.UPDATE_MODULE_ID is '更新モジュールＩＤ';
comment on column MST_SECTION_GROUP.UPDATE_USER_ID is '更新ユーザＩＤ';
comment on column MST_SECTION_GROUP.UPDATE_YMD is '更新年月日';

comment on table MST_SECTION is '区分マスタ:区分値と区分名称情報';
comment on column MST_SECTION.SECTION_GROUP_ID is '区分グループ';
comment on column MST_SECTION.SECTION_ID is '区分ＩＤ';
comment on column MST_SECTION.SECTION_NAME is '区分名称';
comment on column MST_SECTION.SECTION_COMMENT is '区分コメント';
comment on column MST_SECTION.DELETE_FLG is '削除フラグ:0:未削除、1:削除';
comment on column MST_SECTION.CREATE_MODULE_ID is '作成モジュールＩＤ';
comment on column MST_SECTION.CREATE_USER_ID is '作成ユーザＩＤ';
comment on column MST_SECTION.CREATE_YMD is '作成年月日';
comment on column MST_SECTION.UPDATE_MODULE_ID is '更新モジュールＩＤ';
comment on column MST_SECTION.UPDATE_USER_ID is '更新ユーザＩＤ';
comment on column MST_SECTION.UPDATE_YMD is '更新年月日';

comment on table MST_EMPLOYEE is '社員マスタ:社員情報';
comment on column MST_EMPLOYEE.EMPLOYEE_ID is '社員ＩＤ';
comment on column MST_EMPLOYEE.EMPLOYEE_NAME is '社員氏名';
comment on column MST_EMPLOYEE.BIRTH_YMD is '生年月日';
comment on column MST_EMPLOYEE.SEX is '性別';
comment on column MST_EMPLOYEE.JOINED_YMD is '入社日';
comment on column MST_EMPLOYEE.DEPARTMENT_ID is '所属ＩＤ';
comment on column MST_EMPLOYEE.AUTHORIZED is '認証';
comment on column MST_EMPLOYEE.DELETE_FLG is '削除フラグ:0:未削除、1:削除';
comment on column MST_EMPLOYEE.CREATE_MODULE_ID is '作成モジュールＩＤ';
comment on column MST_EMPLOYEE.CREATE_USER_ID is '作成ユーザＩＤ';
comment on column MST_EMPLOYEE.CREATE_YMD is '作成年月日';
comment on column MST_EMPLOYEE.UPDATE_MODULE_ID is '更新モジュールＩＤ';
comment on column MST_EMPLOYEE.UPDATE_USER_ID is '更新ユーザＩＤ';
comment on column MST_EMPLOYEE.UPDATE_YMD is '更新年月日';

comment on table AUTHORIZED_USER is '認証ユーザ:システム認証用のユーザ情報を保持するテーブル';
comment on column AUTHORIZED_USER.USER_ID is 'ユーザＩＤ';
comment on column AUTHORIZED_USER.USER_PASSWORD is 'パスワード';
comment on column AUTHORIZED_USER.ORGANIZATION_ID is '組織ＩＤ';
comment on column AUTHORIZED_USER.AUTHORIZED_GROUP_ID is '権限グループＩＤ';
comment on column AUTHORIZED_USER.LAST_UPDATE_YMD is '前回更新年月日';
comment on column AUTHORIZED_USER.LAST_UPDATE_PASS is '前回更新パスワード';
comment on column AUTHORIZED_USER.DELETE_FLG is '削除フラグ:0:未削除、1:削除';
comment on column AUTHORIZED_USER.CREATE_MODULE_ID is '作成モジュールＩＤ';
comment on column AUTHORIZED_USER.CREATE_USER_ID is '作成ユーザＩＤ';
comment on column AUTHORIZED_USER.CREATE_YMD is '作成年月日';
comment on column AUTHORIZED_USER.UPDATE_MODULE_ID is '更新モジュールＩＤ';
comment on column AUTHORIZED_USER.UPDATE_USER_ID is '更新ユーザＩＤ';
comment on column AUTHORIZED_USER.UPDATE_YMD is '更新年月日';

