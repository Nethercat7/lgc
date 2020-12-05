<template>
  <div>
    <el-container style="min-height: 100vh;">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1','2']">
          <el-submenu index="1">
            <template slot="title"><i class="el-icon-message"></i>用户管理</template>
            <el-menu-item @click="userTable">用户列表</el-menu-item>
            <el-menu-item @click="addUser">添加用户</el-menu-item>
<!--            <el-menu-item-group>-->
<!--              <template slot="title">分组一</template>-->
<!--              <el-menu-item index="1-1">选项1</el-menu-item>-->
<!--              <el-menu-item index="1-2">选项2</el-menu-item>-->
<!--            </el-menu-item-group>-->
<!--            <el-menu-item-group title="分组2">-->
<!--              <el-menu-item index="1-3">选项3</el-menu-item>-->
<!--            </el-menu-item-group>-->
<!--            <el-submenu index="1-4">-->
<!--              <template slot="title">选项4</template>-->
<!--              <el-menu-item index="1-4-1">选项4-1</el-menu-item>-->
<!--            </el-submenu>-->
          </el-submenu>
          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>角色管理</template>
            <el-menu-item @click="roleTable">角色列表</el-menu-item>
            <el-menu-item @click="addRole">添加角色</el-menu-item>
<!--            <el-menu-item-group>-->
<!--              <template slot="title">分组一</template>-->
<!--              <el-menu-item index="2-1">选项1</el-menu-item>-->
<!--              <el-menu-item index="2-2">选项2</el-menu-item>-->
<!--            </el-menu-item-group>-->
<!--            <el-menu-item-group title="分组2">-->
<!--              <el-menu-item index="2-3">选项3</el-menu-item>-->
<!--            </el-menu-item-group>-->
<!--            <el-submenu index="2-4">-->
<!--              <template slot="title">选项4</template>-->
<!--              <el-menu-item index="2-4-1">选项4-1</el-menu-item>-->
<!--            </el-submenu>-->
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">系统设置</template>
            <el-submenu index="3-1">
              <template slot="title">开发人员选项</template>
              <el-menu-item index="3-1-1">添加模块</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style="height:50px;text-align: right; font-size: 17px">
          <span v-text="username"></span>
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 17px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <span @click="exit">退出登入</span></el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-header>

        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
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
      exit(){
        storage.removeUser();
        this.$router.push("/user/login");
      },
      userTable(){
        this.$router.push("/userMgt/users");
      },
      addUser(){
        this.$router.push("/userMgt/add");
      },
      roleTable(){
        this.$router.push("/roleMgt/roles");
      },
      addRole(){
        this.$router.push("/roleMgt/add")
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
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 50px;
  }

  .el-aside {
    color: #333;
  }
</style>
