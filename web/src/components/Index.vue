<template>
  <div>
    <el-row>
      <el-col class="nav" :span="24">
        <span v-text="username"></span>
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 17px;color:#ffffff"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <span @click="exit">退出登入</span></el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-col>
    </el-row>

    <el-row type="flex">
      <el-col class="content" :span="3">
        <el-menu :default-openeds="['1','2','3','4','5']" background-color="#23282D" text-color="#ffffff">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-user-solid"></i>用户管理</template>
            <el-menu-item @click="goto('/userMgt/users')">所有用户</el-menu-item>
            <el-menu-item @click="goto('/userMgt/add')">注册用户</el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-s-custom"></i>角色管理</template>
            <el-menu-item @click="goto('/roleMgt/roles')">所有角色</el-menu-item>
            <el-menu-item @click="goto('/roleMgt/add')">添加角色</el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title"><i class="el-icon-notebook-1"></i>分类管理</template>
            <el-menu-item @click="goto('/garbage/category')">分类类别</el-menu-item>
            <el-menu-item @click="goto('/garbage/table')">垃圾分类</el-menu-item>
          </el-submenu>

          <el-submenu index="4">
            <template slot="title"><i class="el-icon-edit"></i>文章管理</template>
            <el-menu-item @click="goto('/postsMgt/table')">所有文章</el-menu-item>
            <el-menu-item @click="goto('/postsMgt/add')">发布文章</el-menu-item>
            <el-menu-item @click="goto('/postsMgt/categories')">文章类别</el-menu-item>
          </el-submenu>

            <el-submenu index="5">
              <template slot="title"><i class="el-icon-s-tools"></i>系统管理</template>
              <el-menu-item>小程序设置</el-menu-item>
              <el-menu-item>后台系统设置</el-menu-item>
              <el-menu-item>单点登录设置</el-menu-item>
              <el-menu-item>查看反馈意见</el-menu-item>
              <el-menu-item>查看操作日志</el-menu-item>
              <el-menu-item>查看登录日志</el-menu-item>
            </el-submenu>
        </el-menu>
      </el-col>

      <el-col :span="21">
        <router-view/>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import storage from "../assets/storage";

  export default {
    name: "Index",
    data() {
      return {
        username: ""
      };
    },
    methods: {
      exit() {
        storage.removeUser();
        this.$router.push("/user/login");
      },
      goto(url) {
        this.$router.push(url);
      }
    },
    created() {
      if (storage.getUser() == null) {
        this.$router.push("/user/login");
      } else {
        this.username = storage.getUser().username;
      }
    }
  }
</script>

<style scoped>
  .nav {
    background-color: #23282D;
    height: 50px;
    text-align: right;
    font-size: 17px;
    line-height: 50px;
    color: #ffffff;
  }

  .content {
    background-color: #F1F1F1;
  }

  .el-menu{
    height:100%;
    min-height: 100vh;
  }
</style>
