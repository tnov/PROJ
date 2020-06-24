-- Project Name : 社員DB
-- Date/Time    : 2020/06/24 11:35:24
-- Author       : And
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

-- 案件マスタ
drop table if exists MST_MATTER cascade;

create table MST_MATTER (
  CUSTOMER_ID character varying not null
  , MATTER_ID character varying not null
  , MATTER_NAME character varying not null
  , MATTER_KBN character varying
  , AGREE_KBN character varying
  , PG_LANGUAGE_1 character varying
  , PG_LANGUAGE_2 character varying
  , PG_LANGUAGE_3 character varying
  , START_YMD character varying
  , END_YMD character varying
  , AGREE_START_YMD character varying
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD timestamp
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD timestamp
  , constraint MST_MATTER_PKC primary key (CUSTOMER_ID,MATTER_ID)
) ;

-- 顧客マスタ
drop table if exists MST_CUSTOMER cascade;

create table MST_CUSTOMER (
  CUSTOMER_ID character varying not null
  , CUSTOMER_NAME character varying
  , ZIP_CODE character varying
  , ADDRESS character varying
  , LIAISON character varying
  , STAFF character varying
  , TEL character varying
  , DEVELOPMENT_FLG character default 0
  , MAINTENANCE_FLG character default 0
  , OPERATION_FLG character default 0
  , INFRASTRUCTURE_FLG character default 0
  , FIRST_AGREE_YMD character varying
  , AGREE_STATUS character
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD timestamp
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD timestamp
  , constraint MST_CUSTOMER_PKC primary key (CUSTOMER_ID)
) ;

-- 階層マスタ
drop table if exists MST_HIERARCHY cascade;

create table MST_HIERARCHY (
  HIERARCHY_ID character varying not null
  , HIERARCHY_NAME character varying not null
  , HIERARCHY_ORDER integer default 0 not null
  , DISP_FLG character not null
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD timestamp
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD timestamp
  , constraint MST_HIERARCHY_PKC primary key (HIERARCHY_ID)
) ;

-- 部署マスタ
drop table if exists MST_DEPARTMENT cascade;

create table MST_DEPARTMENT (
  DEPARTMENT_ID character varying not null
  , DEPARTMENT_NAME character varying not null
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD timestamp
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD timestamp
  , constraint MST_DEPARTMENT_PKC primary key (DEPARTMENT_ID)
) ;

-- 機能マスタ
drop table if exists MST_FUNCTION cascade;

create table MST_FUNCTION (
  FUNCTION_ID character varying not null
  , FUNCTION_NAME character varying not null
  , FUNCTION_PATH character varying not null
  , FUNCTION_COMMENT character varying
  , AUTHORIZED_ID character varying
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD timestamp
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD timestamp
  , constraint MST_FUNCTION_PKC primary key (FUNCTION_ID,FUNCTION_NAME)
) ;

-- メニューマスタ
drop table if exists MST_MENU cascade;

create table MST_MENU (
  HIERARCHY_ID character varying not null
  , FUNCTION_ID character varying not null
  , FUNCTION_ORDER integer default 0 not null
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD timestamp
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD timestamp
  , constraint MST_MENU_PKC primary key (HIERARCHY_ID,FUNCTION_ID)
) ;

-- 区分グループマスタ
drop table if exists MST_SECTION_GROUP cascade;

create table MST_SECTION_GROUP (
  SECTION_GROUP_ID character varying not null
  , SECTION_GROUP_NAME character varying not null
  , SECTION_GROUP_COMMENT character varying
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD timestamp
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD timestamp
  , constraint MST_SECTION_GROUP_PKC primary key (SECTION_GROUP_ID)
) ;

-- 区分マスタ
drop table if exists MST_SECTION cascade;

create table MST_SECTION (
  SECTION_GROUP_ID character varying not null
  , SECTION_ID character varying not null
  , SECTION_NAME character varying not null
  , SECTION_COMMENT character varying
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD timestamp
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD timestamp
  , constraint MST_SECTION_PKC primary key (SECTION_GROUP_ID,SECTION_ID)
) ;

-- 社員マスタ
drop table if exists MST_EMPLOYEE cascade;

create table MST_EMPLOYEE (
  EMPLOYEE_ID character varying not null
  , EMPLOYEE_NAME character varying not null
  , SEX character not null
  , BIRTH_YMD character varying not null
  , ZIP_CODE character varying
  , ADDRESS character varying
  , TEL character varying
  , JOINED_YMD character varying
  , RETIRE_YMD character varying
  , DEPARTMENT_ID character varying
  , AUTHORIZED character default 0 not null
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD timestamp
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD timestamp
  , constraint MST_EMPLOYEE_PKC primary key (EMPLOYEE_ID)
) ;

-- 認証ユーザ
drop table if exists AUTHORIZED_USER cascade;

create table AUTHORIZED_USER (
  USER_ID character varying not null
  , USER_PASSWORD character varying not null
  , LAST_UPDATE_YMD character varying
  , LAST_UPDATE_PASSWORD character varying
  , DELETE_FLG character default 0 not null
  , CREATE_MODULE_ID character varying
  , CREATE_USER_ID character varying
  , CREATE_YMD timestamp
  , UPDATE_MODULE_ID character varying
  , UPDATE_USER_ID character varying
  , UPDATE_YMD timestamp
  , constraint AUTHORIZED_USER_PKC primary key (USER_ID)
) ;

comment on table MST_MATTER is '案件マスタ:案件情報';
comment on column MST_MATTER.CUSTOMER_ID is '顧客ID';
comment on column MST_MATTER.MATTER_ID is '案件ID';
comment on column MST_MATTER.MATTER_NAME is '案件名称';
comment on column MST_MATTER.MATTER_KBN is '案件種別';
comment on column MST_MATTER.AGREE_KBN is '契約形態';
comment on column MST_MATTER.PG_LANGUAGE_1 is '開発言語1';
comment on column MST_MATTER.PG_LANGUAGE_2 is '開発言語2';
comment on column MST_MATTER.PG_LANGUAGE_3 is '開発言語3';
comment on column MST_MATTER.START_YMD is '開始年月日';
comment on column MST_MATTER.END_YMD is '終了年月日';
comment on column MST_MATTER.AGREE_START_YMD is '契約開始年月日';
comment on column MST_MATTER.DELETE_FLG is '削除フラグ:0:未削除、1:削除';
comment on column MST_MATTER.CREATE_MODULE_ID is '作成モジュールＩＤ';
comment on column MST_MATTER.CREATE_USER_ID is '作成ユーザＩＤ';
comment on column MST_MATTER.CREATE_YMD is '作成年月日';
comment on column MST_MATTER.UPDATE_MODULE_ID is '更新モジュールＩＤ';
comment on column MST_MATTER.UPDATE_USER_ID is '更新ユーザＩＤ';
comment on column MST_MATTER.UPDATE_YMD is '更新年月日';

comment on table MST_CUSTOMER is '顧客マスタ:顧客情報';
comment on column MST_CUSTOMER.CUSTOMER_ID is '顧客ID';
comment on column MST_CUSTOMER.CUSTOMER_NAME is '顧客名称';
comment on column MST_CUSTOMER.ZIP_CODE is '郵便番号';
comment on column MST_CUSTOMER.ADDRESS is '住所';
comment on column MST_CUSTOMER.LIAISON is '担当窓口';
comment on column MST_CUSTOMER.STAFF is '担当者';
comment on column MST_CUSTOMER.TEL is '電話番号';
comment on column MST_CUSTOMER.DEVELOPMENT_FLG is '開発業務有無';
comment on column MST_CUSTOMER.MAINTENANCE_FLG is '保守業務有無';
comment on column MST_CUSTOMER.OPERATION_FLG is '運用業務有無';
comment on column MST_CUSTOMER.INFRASTRUCTURE_FLG is 'インフラ業務有無';
comment on column MST_CUSTOMER.FIRST_AGREE_YMD is '初回契約年月日';
comment on column MST_CUSTOMER.AGREE_STATUS is '契約状況';
comment on column MST_CUSTOMER.DELETE_FLG is '削除フラグ:0:未削除、1:削除';
comment on column MST_CUSTOMER.CREATE_MODULE_ID is '作成モジュールＩＤ';
comment on column MST_CUSTOMER.CREATE_USER_ID is '作成ユーザＩＤ';
comment on column MST_CUSTOMER.CREATE_YMD is '作成年月日';
comment on column MST_CUSTOMER.UPDATE_MODULE_ID is '更新モジュールＩＤ';
comment on column MST_CUSTOMER.UPDATE_USER_ID is '更新ユーザＩＤ';
comment on column MST_CUSTOMER.UPDATE_YMD is '更新年月日';

comment on table MST_HIERARCHY is '階層マスタ';
comment on column MST_HIERARCHY.HIERARCHY_ID is '階層ID';
comment on column MST_HIERARCHY.HIERARCHY_NAME is '階層名';
comment on column MST_HIERARCHY.HIERARCHY_ORDER is '階層順序';
comment on column MST_HIERARCHY.DISP_FLG is '表示フラグ';
comment on column MST_HIERARCHY.DELETE_FLG is '削除フラグ:0:未削除、1:削除';
comment on column MST_HIERARCHY.CREATE_MODULE_ID is '作成モジュールＩＤ';
comment on column MST_HIERARCHY.CREATE_USER_ID is '作成ユーザＩＤ';
comment on column MST_HIERARCHY.CREATE_YMD is '作成年月日';
comment on column MST_HIERARCHY.UPDATE_MODULE_ID is '更新モジュールＩＤ';
comment on column MST_HIERARCHY.UPDATE_USER_ID is '更新ユーザＩＤ';
comment on column MST_HIERARCHY.UPDATE_YMD is '更新年月日';

comment on table MST_DEPARTMENT is '部署マスタ';
comment on column MST_DEPARTMENT.DEPARTMENT_ID is '部署ID';
comment on column MST_DEPARTMENT.DEPARTMENT_NAME is '所属名称';
comment on column MST_DEPARTMENT.DELETE_FLG is '削除フラグ:0:未削除、1:削除';
comment on column MST_DEPARTMENT.CREATE_MODULE_ID is '作成モジュールＩＤ';
comment on column MST_DEPARTMENT.CREATE_USER_ID is '作成ユーザＩＤ';
comment on column MST_DEPARTMENT.CREATE_YMD is '作成年月日';
comment on column MST_DEPARTMENT.UPDATE_MODULE_ID is '更新モジュールＩＤ';
comment on column MST_DEPARTMENT.UPDATE_USER_ID is '更新ユーザＩＤ';
comment on column MST_DEPARTMENT.UPDATE_YMD is '更新年月日';

comment on table MST_FUNCTION is '機能マスタ';
comment on column MST_FUNCTION.FUNCTION_ID is '機能ID';
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
comment on column MST_MENU.HIERARCHY_ID is '階層ID';
comment on column MST_MENU.FUNCTION_ID is '機能ID';
comment on column MST_MENU.FUNCTION_ORDER is '機能順序';
comment on column MST_MENU.DELETE_FLG is '削除フラグ:0:未削除、1:削除';
comment on column MST_MENU.CREATE_MODULE_ID is '作成モジュールＩＤ';
comment on column MST_MENU.CREATE_USER_ID is '作成ユーザＩＤ';
comment on column MST_MENU.CREATE_YMD is '作成年月日';
comment on column MST_MENU.UPDATE_MODULE_ID is '更新モジュールＩＤ';
comment on column MST_MENU.UPDATE_USER_ID is '更新ユーザＩＤ';
comment on column MST_MENU.UPDATE_YMD is '更新年月日';

comment on table MST_SECTION_GROUP is '区分グループマスタ:区分グループ情報';
comment on column MST_SECTION_GROUP.SECTION_GROUP_ID is '区分グループID';
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
comment on column MST_SECTION.SECTION_ID is '区分ID';
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
comment on column MST_EMPLOYEE.EMPLOYEE_ID is '社員ID';
comment on column MST_EMPLOYEE.EMPLOYEE_NAME is '社員氏名';
comment on column MST_EMPLOYEE.SEX is '性別';
comment on column MST_EMPLOYEE.BIRTH_YMD is '生年月日';
comment on column MST_EMPLOYEE.ZIP_CODE is '郵便番号';
comment on column MST_EMPLOYEE.ADDRESS is '住所';
comment on column MST_EMPLOYEE.TEL is '電話番号';
comment on column MST_EMPLOYEE.JOINED_YMD is '入社日';
comment on column MST_EMPLOYEE.RETIRE_YMD is '退職日';
comment on column MST_EMPLOYEE.DEPARTMENT_ID is '部署ID';
comment on column MST_EMPLOYEE.AUTHORIZED is '認証';
comment on column MST_EMPLOYEE.DELETE_FLG is '削除フラグ:0:未削除、1:削除';
comment on column MST_EMPLOYEE.CREATE_MODULE_ID is '作成モジュールＩＤ';
comment on column MST_EMPLOYEE.CREATE_USER_ID is '作成ユーザＩＤ';
comment on column MST_EMPLOYEE.CREATE_YMD is '作成年月日';
comment on column MST_EMPLOYEE.UPDATE_MODULE_ID is '更新モジュールＩＤ';
comment on column MST_EMPLOYEE.UPDATE_USER_ID is '更新ユーザＩＤ';
comment on column MST_EMPLOYEE.UPDATE_YMD is '更新年月日';

comment on table AUTHORIZED_USER is '認証ユーザ:システム認証用のユーザ情報を保持するテーブル';
comment on column AUTHORIZED_USER.USER_ID is 'ユーザID';
comment on column AUTHORIZED_USER.USER_PASSWORD is 'パスワード';
comment on column AUTHORIZED_USER.LAST_UPDATE_YMD is '前回更新年月日';
comment on column AUTHORIZED_USER.LAST_UPDATE_PASSWORD is '前回更新パスワード';
comment on column AUTHORIZED_USER.DELETE_FLG is '削除フラグ:0:未削除、1:削除';
comment on column AUTHORIZED_USER.CREATE_MODULE_ID is '作成モジュールＩＤ';
comment on column AUTHORIZED_USER.CREATE_USER_ID is '作成ユーザＩＤ';
comment on column AUTHORIZED_USER.CREATE_YMD is '作成年月日';
comment on column AUTHORIZED_USER.UPDATE_MODULE_ID is '更新モジュールＩＤ';
comment on column AUTHORIZED_USER.UPDATE_USER_ID is '更新ユーザＩＤ';
comment on column AUTHORIZED_USER.UPDATE_YMD is '更新年月日';
