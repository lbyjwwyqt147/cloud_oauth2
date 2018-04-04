/*
Navicat PGSQL Data Transfer

Source Server         : 虚拟机 130
Source Server Version : 100300
Source Host           : 192.168.213.130:5432
Source Database       : cloud_oauth2
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 100300
File Encoding         : 65001

Date: 2018-04-04 11:03:41
*/


-- ----------------------------
-- Table structure for sys_account
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_account";
CREATE TABLE "public"."sys_account" (
"id" int8 NOT NULL,
"user_account" varchar(32) COLLATE "default" NOT NULL,
"user_pwd" varchar(50) COLLATE "default" NOT NULL,
"create_time" timestamp(6) NOT NULL,
"create_id" int8,
"update_time" timestamp(6),
"update_id" int8,
"status" int2 NOT NULL,
"user_email" varchar(50) COLLATE "default",
"binding_phone" int4
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."sys_account" IS '账户表';
COMMENT ON COLUMN "public"."sys_account"."id" IS 'id';
COMMENT ON COLUMN "public"."sys_account"."user_account" IS '用户账号';
COMMENT ON COLUMN "public"."sys_account"."user_pwd" IS '密码';
COMMENT ON COLUMN "public"."sys_account"."create_id" IS '创建人';
COMMENT ON COLUMN "public"."sys_account"."update_time" IS '最后更新时间';
COMMENT ON COLUMN "public"."sys_account"."update_id" IS '最后修改人';
COMMENT ON COLUMN "public"."sys_account"."status" IS '状态  0：禁用  1：启用  ';
COMMENT ON COLUMN "public"."sys_account"."user_email" IS '电子邮箱';
COMMENT ON COLUMN "public"."sys_account"."binding_phone" IS '绑定手机号';

-- ----------------------------
-- Records of sys_account
-- ----------------------------

-- ----------------------------
-- Table structure for sys_module
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_module";
CREATE TABLE "public"."sys_module" (
"id" int4 NOT NULL,
"module_code" int8 NOT NULL,
"module_name" varchar(50) COLLATE "default" NOT NULL,
"module_type" int2 NOT NULL,
"module_pid" int8 NOT NULL,
"sequence_number" int2,
"menu_icon" varchar(32) COLLATE "default",
"menu_url" varchar(255) COLLATE "default",
"authorized_signs" varchar(255) COLLATE "default",
"status" int2 NOT NULL,
"create_time" timestamp(6) NOT NULL,
"create_id" int8,
"update_time" timestamp(6),
"update_id" int8
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."sys_module" IS '资源菜单模块表';
COMMENT ON COLUMN "public"."sys_module"."module_code" IS '模块编号';
COMMENT ON COLUMN "public"."sys_module"."module_name" IS '模块名称';
COMMENT ON COLUMN "public"."sys_module"."module_type" IS '模块类型 1:目录  2：菜单   3：功能按钮';
COMMENT ON COLUMN "public"."sys_module"."module_pid" IS '父级编号';
COMMENT ON COLUMN "public"."sys_module"."sequence_number" IS '序号';
COMMENT ON COLUMN "public"."sys_module"."menu_icon" IS 'icon图标';
COMMENT ON COLUMN "public"."sys_module"."menu_url" IS '菜单url';
COMMENT ON COLUMN "public"."sys_module"."authorized_signs" IS '授权标识(多个用逗号分隔，如：user:list,user:create)';
COMMENT ON COLUMN "public"."sys_module"."status" IS '状态  0：禁用  1：启用  ';
COMMENT ON COLUMN "public"."sys_module"."create_id" IS '创建人';
COMMENT ON COLUMN "public"."sys_module"."update_time" IS '最后更新时间';
COMMENT ON COLUMN "public"."sys_module"."update_id" IS '最后修改人';

-- ----------------------------
-- Records of sys_module
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role";
CREATE TABLE "public"."sys_role" (
"id" int4 NOT NULL,
"role_code" varchar(10) COLLATE "default" NOT NULL,
"role_name" varchar(40) COLLATE "default" NOT NULL,
"role_description" varchar(50) COLLATE "default",
"create_time" timestamp(6) NOT NULL,
"create_id" int8,
"update_time" timestamp(6),
"update_id" int8,
"status" int2 NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."sys_role" IS '角色表';
COMMENT ON COLUMN "public"."sys_role"."id" IS 'id';
COMMENT ON COLUMN "public"."sys_role"."role_code" IS '角色编码';
COMMENT ON COLUMN "public"."sys_role"."role_name" IS '角色名称';
COMMENT ON COLUMN "public"."sys_role"."role_description" IS '描述';
COMMENT ON COLUMN "public"."sys_role"."create_id" IS '创建人';
COMMENT ON COLUMN "public"."sys_role"."update_time" IS '最后更新时间';
COMMENT ON COLUMN "public"."sys_role"."update_id" IS '最后修改人';
COMMENT ON COLUMN "public"."sys_role"."status" IS '状态  0：禁用  1：启用  ';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_module
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role_module";
CREATE TABLE "public"."sys_role_module" (
"id" int8 NOT NULL,
"role_id" int8 NOT NULL,
"module_id" int8 NOT NULL,
"create_time" timestamp(6) NOT NULL,
"create_id" int8 NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."sys_role_module" IS '角色模块分配表';
COMMENT ON COLUMN "public"."sys_role_module"."role_id" IS '角色id';
COMMENT ON COLUMN "public"."sys_role_module"."module_id" IS '模块id';
COMMENT ON COLUMN "public"."sys_role_module"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."sys_role_module"."create_id" IS '创建人';

-- ----------------------------
-- Records of sys_role_module
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user_role";
CREATE TABLE "public"."sys_user_role" (
"id" int8 NOT NULL,
"user_id" int8 NOT NULL,
"role_id" int8 NOT NULL,
"create_time" timestamp(6) NOT NULL,
"create_id" int8 NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."sys_user_role" IS '用户角色分配表';
COMMENT ON COLUMN "public"."sys_user_role"."id" IS 'id';
COMMENT ON COLUMN "public"."sys_user_role"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."sys_user_role"."role_id" IS '角色id';
COMMENT ON COLUMN "public"."sys_user_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."sys_user_role"."create_id" IS '创建人';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS "public"."user_info";
CREATE TABLE "public"."user_info" (
"id" int8 NOT NULL,
"account_id" int8 NOT NULL,
"user_name" varchar(32) COLLATE "default" NOT NULL,
"user_sex" int2,
"create_time" timestamp(6) NOT NULL,
"create_id" int8,
"update_time" timestamp(6),
"update_id" int8
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."user_info"."id" IS 'id';
COMMENT ON COLUMN "public"."user_info"."account_id" IS '账号id';
COMMENT ON COLUMN "public"."user_info"."user_name" IS '用户姓名';
COMMENT ON COLUMN "public"."user_info"."user_sex" IS '性别  0:男  1:女  2:其他';
COMMENT ON COLUMN "public"."user_info"."create_id" IS '创建人';
COMMENT ON COLUMN "public"."user_info"."update_time" IS '最后更新时间';
COMMENT ON COLUMN "public"."user_info"."update_id" IS '最后修改人';

-- ----------------------------
-- Records of user_info
-- ----------------------------

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Indexes structure for table sys_account
-- ----------------------------
CREATE INDEX "idx_status" ON "public"."sys_account" USING btree ("status");
CREATE INDEX "idx_user_pwd" ON "public"."sys_account" USING btree ("user_pwd");
CREATE UNIQUE INDEX "uk_user_account" ON "public"."sys_account" USING btree ("user_account");

-- ----------------------------
-- Primary Key structure for table sys_account
-- ----------------------------
ALTER TABLE "public"."sys_account" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_module
-- ----------------------------
CREATE INDEX "idx_module_status" ON "public"."sys_module" USING btree ("status");
CREATE INDEX "idx_module_type" ON "public"."sys_module" USING btree ("module_type");
CREATE UNIQUE INDEX "uk_module_code" ON "public"."sys_module" USING btree ("module_code");

-- ----------------------------
-- Primary Key structure for table sys_module
-- ----------------------------
ALTER TABLE "public"."sys_module" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_role
-- ----------------------------
CREATE INDEX "idx_role_status" ON "public"."sys_role" USING btree ("status");
CREATE UNIQUE INDEX "uk_role_code" ON "public"."sys_role" USING btree ("role_code");

-- ----------------------------
-- Primary Key structure for table sys_role
-- ----------------------------
ALTER TABLE "public"."sys_role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_role_module
-- ----------------------------
CREATE INDEX "idx_module_id" ON "public"."sys_role_module" USING btree ("module_id");
CREATE INDEX "idx_module_role_id" ON "public"."sys_role_module" USING btree ("role_id");

-- ----------------------------
-- Primary Key structure for table sys_role_module
-- ----------------------------
ALTER TABLE "public"."sys_role_module" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_user_role
-- ----------------------------
CREATE INDEX "idx_role_id" ON "public"."sys_user_role" USING btree ("role_id");
CREATE INDEX "idx_user_id" ON "public"."sys_user_role" USING btree ("user_id");

-- ----------------------------
-- Primary Key structure for table sys_user_role
-- ----------------------------
ALTER TABLE "public"."sys_user_role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table user_info
-- ----------------------------
CREATE INDEX "idx_account_id" ON "public"."user_info" USING btree ("account_id");
CREATE INDEX "idx_user_name" ON "public"."user_info" USING btree ("user_name");

-- ----------------------------
-- Primary Key structure for table user_info
-- ----------------------------
ALTER TABLE "public"."user_info" ADD PRIMARY KEY ("id");
