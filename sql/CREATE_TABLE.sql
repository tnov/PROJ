-- Project Name : �Ј�DB
-- Date/Time    : 2019/12/05 3:04:16
-- Author       : ryzen
-- RDBMS Type   : PostgreSQL
-- Application  : A5:SQL Mk-2

/*
  BackupToTempTable, RestoreFromTempTable�^�����߂��t������Ă��܂��B
  ����ɂ��Adrop table, create table ����f�[�^���c��܂��B
  ���̋@�\�͈ꎞ�I�� $$TableName �̂悤�Ȉꎞ�e�[�u�����쐬���܂��B
*/

-- �g�D�}�X�^
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

-- �@�\�}�X�^
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

-- ���j���[�}�X�^
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

-- �����}�X�^
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

-- �敪�O���[�v�}�X�^
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

-- �敪�}�X�^
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

-- �Ј��}�X�^
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

-- �F�؃��[�U
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

comment on table MST_ORGANIZATION is '�g�D�}�X�^';
comment on column MST_ORGANIZATION.ORGANIZATION_ID is '�g�D�h�c';
comment on column MST_ORGANIZATION.ORGANIZATION_NAME is '�g�D����';
comment on column MST_ORGANIZATION.ORGANIZATION_COMMENT is '�g�D����';
comment on column MST_ORGANIZATION.ORGANIZATION_PARENT_ID is '�e�g�D�h�c';
comment on column MST_ORGANIZATION.DELETE_FLG is '�폜�t���O:0:���폜�A1:�폜';
comment on column MST_ORGANIZATION.CREATE_MODULE_ID is '�쐬���W���[���h�c';
comment on column MST_ORGANIZATION.CREATE_USER_ID is '�쐬���[�U�h�c';
comment on column MST_ORGANIZATION.CREATE_YMD is '�쐬�N����';
comment on column MST_ORGANIZATION.UPDATE_MODULE_ID is '�X�V���W���[���h�c';
comment on column MST_ORGANIZATION.UPDATE_USER_ID is '�X�V���[�U�h�c';
comment on column MST_ORGANIZATION.UPDATE_YMD is '�X�V�N����';

comment on table MST_FUNCTION is '�@�\�}�X�^';
comment on column MST_FUNCTION.FUNCTION_ID is '�@�\�h�c';
comment on column MST_FUNCTION.FUNCTION_NAME is '�@�\����';
comment on column MST_FUNCTION.FUNCTION_PATH is '�@�\�p�X';
comment on column MST_FUNCTION.FUNCTION_COMMENT is '�@�\����';
comment on column MST_FUNCTION.AUTHORIZED_ID is '�����h�c';
comment on column MST_FUNCTION.DELETE_FLG is '�폜�t���O:0:���폜�A1:�폜';
comment on column MST_FUNCTION.CREATE_MODULE_ID is '�쐬���W���[���h�c';
comment on column MST_FUNCTION.CREATE_USER_ID is '�쐬���[�U�h�c';
comment on column MST_FUNCTION.CREATE_YMD is '�쐬�N����';
comment on column MST_FUNCTION.UPDATE_MODULE_ID is '�X�V���W���[���h�c';
comment on column MST_FUNCTION.UPDATE_USER_ID is '�X�V���[�U�h�c';
comment on column MST_FUNCTION.UPDATE_YMD is '�X�V�N����';

comment on table MST_MENU is '���j���[�}�X�^';
comment on column MST_MENU.HIERARCHY is '�K�w';
comment on column MST_MENU.FUNCTION_ORDER is '����';
comment on column MST_MENU.FUNCTION_ID is '�@�\�h�c';
comment on column MST_MENU.DELETE_FLG is '�폜�t���O:0:���폜�A1:�폜';
comment on column MST_MENU.CREATE_MODULE_ID is '�쐬���W���[���h�c';
comment on column MST_MENU.CREATE_USER_ID is '�쐬���[�U�h�c';
comment on column MST_MENU.CREATE_YMD is '�쐬�N����';
comment on column MST_MENU.UPDATE_MODULE_ID is '�X�V���W���[���h�c';
comment on column MST_MENU.UPDATE_USER_ID is '�X�V���[�U�h�c';
comment on column MST_MENU.UPDATE_YMD is '�X�V�N����';

comment on table MST_AUTHORIZED is '�����}�X�^';
comment on column MST_AUTHORIZED.AUTHORIZED_ID is '�����h�c';
comment on column MST_AUTHORIZED.AUTHORIZED_NAME is '��������';
comment on column MST_AUTHORIZED.AUTHORIZED_COMMENT is '�����R�����g';
comment on column MST_AUTHORIZED.AUTHORIZED_LEVEL is '�������x��:TDB';
comment on column MST_AUTHORIZED.ORGANIZETION_ID is '�g�D�h�c';
comment on column MST_AUTHORIZED.DELETE_FLG is '�폜�t���O:0:���폜�A1:�폜';
comment on column MST_AUTHORIZED.CREATE_MODULE_ID is '�쐬���W���[���h�c';
comment on column MST_AUTHORIZED.CREATE_USER_ID is '�쐬���[�U�h�c';
comment on column MST_AUTHORIZED.CREATE_YMD is '�쐬�N����';
comment on column MST_AUTHORIZED.UPDATE_MODULE_ID is '�X�V���W���[���h�c';
comment on column MST_AUTHORIZED.UPDATE_USER_ID is '�X�V���[�U�h�c';
comment on column MST_AUTHORIZED.UPDATE_YMD is '�X�V�N����';

comment on table MST_SECTION_GROUP is '�敪�O���[�v�}�X�^:�敪�O���[�v���';
comment on column MST_SECTION_GROUP.SECTION_GROUP_ID is '�敪�O���[�v�h�c';
comment on column MST_SECTION_GROUP.SECTION_GROUP_NAME is '�敪�O���[�v����';
comment on column MST_SECTION_GROUP.SECTION_GROUP_COMMENT is '�敪�O���[�v�R�����g';
comment on column MST_SECTION_GROUP.DELETE_FLG is '�폜�t���O:0:���폜�A1:�폜';
comment on column MST_SECTION_GROUP.CREATE_MODULE_ID is '�쐬���W���[���h�c';
comment on column MST_SECTION_GROUP.CREATE_USER_ID is '�쐬���[�U�h�c';
comment on column MST_SECTION_GROUP.CREATE_YMD is '�쐬�N����';
comment on column MST_SECTION_GROUP.UPDATE_MODULE_ID is '�X�V���W���[���h�c';
comment on column MST_SECTION_GROUP.UPDATE_USER_ID is '�X�V���[�U�h�c';
comment on column MST_SECTION_GROUP.UPDATE_YMD is '�X�V�N����';

comment on table MST_SECTION is '�敪�}�X�^:�敪�l�Ƌ敪���̏��';
comment on column MST_SECTION.SECTION_GROUP_ID is '�敪�O���[�v';
comment on column MST_SECTION.SECTION_ID is '�敪�h�c';
comment on column MST_SECTION.SECTION_NAME is '�敪����';
comment on column MST_SECTION.SECTION_COMMENT is '�敪�R�����g';
comment on column MST_SECTION.DELETE_FLG is '�폜�t���O:0:���폜�A1:�폜';
comment on column MST_SECTION.CREATE_MODULE_ID is '�쐬���W���[���h�c';
comment on column MST_SECTION.CREATE_USER_ID is '�쐬���[�U�h�c';
comment on column MST_SECTION.CREATE_YMD is '�쐬�N����';
comment on column MST_SECTION.UPDATE_MODULE_ID is '�X�V���W���[���h�c';
comment on column MST_SECTION.UPDATE_USER_ID is '�X�V���[�U�h�c';
comment on column MST_SECTION.UPDATE_YMD is '�X�V�N����';

comment on table MST_EMPLOYEE is '�Ј��}�X�^:�Ј����';
comment on column MST_EMPLOYEE.EMPLOYEE_ID is '�Ј��h�c';
comment on column MST_EMPLOYEE.EMPLOYEE_NAME is '�Ј�����';
comment on column MST_EMPLOYEE.BIRTH_YMD is '���N����';
comment on column MST_EMPLOYEE.SEX is '����';
comment on column MST_EMPLOYEE.JOINED_YMD is '���Г�';
comment on column MST_EMPLOYEE.DEPARTMENT_ID is '�����h�c';
comment on column MST_EMPLOYEE.AUTHORIZED is '�F��';
comment on column MST_EMPLOYEE.DELETE_FLG is '�폜�t���O:0:���폜�A1:�폜';
comment on column MST_EMPLOYEE.CREATE_MODULE_ID is '�쐬���W���[���h�c';
comment on column MST_EMPLOYEE.CREATE_USER_ID is '�쐬���[�U�h�c';
comment on column MST_EMPLOYEE.CREATE_YMD is '�쐬�N����';
comment on column MST_EMPLOYEE.UPDATE_MODULE_ID is '�X�V���W���[���h�c';
comment on column MST_EMPLOYEE.UPDATE_USER_ID is '�X�V���[�U�h�c';
comment on column MST_EMPLOYEE.UPDATE_YMD is '�X�V�N����';

comment on table AUTHORIZED_USER is '�F�؃��[�U:�V�X�e���F�ؗp�̃��[�U����ێ�����e�[�u��';
comment on column AUTHORIZED_USER.USER_ID is '���[�U�h�c';
comment on column AUTHORIZED_USER.USER_PASSWORD is '�p�X���[�h';
comment on column AUTHORIZED_USER.ORGANIZATION_ID is '�g�D�h�c';
comment on column AUTHORIZED_USER.AUTHORIZED_GROUP_ID is '�����O���[�v�h�c';
comment on column AUTHORIZED_USER.LAST_UPDATE_YMD is '�O��X�V�N����';
comment on column AUTHORIZED_USER.LAST_UPDATE_PASS is '�O��X�V�p�X���[�h';
comment on column AUTHORIZED_USER.DELETE_FLG is '�폜�t���O:0:���폜�A1:�폜';
comment on column AUTHORIZED_USER.CREATE_MODULE_ID is '�쐬���W���[���h�c';
comment on column AUTHORIZED_USER.CREATE_USER_ID is '�쐬���[�U�h�c';
comment on column AUTHORIZED_USER.CREATE_YMD is '�쐬�N����';
comment on column AUTHORIZED_USER.UPDATE_MODULE_ID is '�X�V���W���[���h�c';
comment on column AUTHORIZED_USER.UPDATE_USER_ID is '�X�V���[�U�h�c';
comment on column AUTHORIZED_USER.UPDATE_YMD is '�X�V�N����';

