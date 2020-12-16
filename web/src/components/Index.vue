<template>
  <el-row>
    <el-col :span="3">
      <el-menu :default-openeds="['1','2','3','4','5']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>用户管理</template>
          <el-menu-item @click="goto('/userMgt/users')">用户列表</el-menu-item>
          <el-menu-item @click="goto('/userMgt/add')">添加用户</el-menu-item>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-menu"></i>角色管理</template>
          <el-menu-item @click="goto('/roleMgt/roles')">角色列表</el-menu-item>
          <el-menu-item @click="goto('/roleMgt/add')">添加角色</el-menu-item>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-menu"></i>分类管理</template>
          <el-menu-item @click="goto('/garbage/category')">类别列表</el-menu-item>
          <el-menu-item @click="goto('/garbage/table')">分类列表</el-menu-item>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title"><i class="el-icon-menu"></i>文章管理</template>
          <el-menu-item @click="goto('/postsMgt/table')">所有文章</el-menu-item>
          <el-menu-item @click="goto('/postsMgt/add')">发布文章</el-menu-item>
          <el-menu-item @click="goto('/postsMgt/categories')">文章类别</el-menu-item>
        </el-submenu>
        <el-submenu index="5">
          <template slot="title"><i class="el-icon-menu"></i>系统设置</template>
          <el-menu-item @click="goto('/sys/titleMgt')">称号设置</el-menu-item>
        </el-submenu>
      </el-menu>
    </el-col>

    <el-col :span="21">
      <el-row>
        <el-col :span="24" class="nav">
          <span v-text="username"></span>
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 17px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <span @click="exit">退出登入</span></el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-col>
      </el-row>

      <router-view/>
    </el-col>
  </el-row>
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
      goto(url){
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
    background-color: grey;
    height: 50px;
    text-align: right;
    font-size: 17px;
    line-height: 50px;
  }
</style>
