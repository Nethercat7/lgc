# Basic management system

## 说明

BMS是一款以Spirng boot为核心，集成Vue、Shiro、JWT等框架开发的前后端分离基础后台管理系统。系统包含用户管理，角色管理二大功能模块。

## 技术栈

### 前端

* JS框架：Vue
* UI框架：Element UI
* 异步请求：Axios
* 跨域认证：JSON Web Token

### 后台

* 核心框架：Spring Boot
* 安全框架：Apace Shiro + JWT
* 持久层框架：Mybaits
* 数据连接池：Durid

## 开发环境

* OS：Windows 10
* 编译软件：IDEA
* 数据库 ：MySQL 57
* JDK：JDK11
* Node：Node.js 14

## 模块功能

### 用户管理

- [x] 添加用户

- [x] 用户列表
  - [x] 修改用户
  - [x] 删除用户
- [x] 用户角色分配
- [ ] 禁用用户

### 角色管理

- [x] 添加角色
- [x] 角色列表
  - [x] 修改角色
  - [x] 删除角色
- [x] 角色权限分配
- [ ] 禁用角色

### 系统
- [ ] Shiro权限认证

## BUG
- [X] 删除角色时未删除角色与用户之间的关系

