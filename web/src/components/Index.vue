<template>
  <el-row>
    <el-col :span="3">
      <el-menu :default-openeds="['1','2','3','4']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-message"></i>用户管理</template>
          <el-menu-item @click="userTable">用户列表</el-menu-item>
          <el-menu-item @click="addUser">添加用户</el-menu-item>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title"><i class="el-icon-menu"></i>角色管理</template>
          <el-menu-item @click="roleTable">角色列表</el-menu-item>
          <el-menu-item @click="addRole">添加角色</el-menu-item>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title"><i class="el-icon-menu"></i>分类管理</template>
          <el-menu-item @click="garbageCategoryTable">类别列表</el-menu-item>
          <el-menu-item @click="garbageTable">分类列表</el-menu-item>
        </el-submenu>
        <el-submenu index="4">
          <template slot="title"><i class="el-icon-menu"></i>文章管理</template>
          <el-menu-item @click="postsTable">所有文章</el-menu-item>
          <el-menu-item @click="addPosts">发布文章</el-menu-item>
          <el-menu-item @click="postsCategories">文章类别</el-menu-item>
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
      userTable() {
        this.$router.push("/userMgt/users");
      },
      addUser() {
        this.$router.push("/userMgt/add");
      },
      roleTable() {
        this.$router.push("/roleMgt/roles");
      },
      addRole() {
        this.$router.push("/roleMgt/add");
      },
      garbageCategoryTable() {
        this.$router.push("/garbage/category");
      },
      garbageTable() {
        this.$router.push("/garbage/table");
      },
      postsTable() {
        this.$router.push("/postsMgt/table");
      },
      addPosts() {
        this.$router.push("/postsMgt/add");
      },
      postsCategories(){
        this.$router.push("/postsMgt/categories");
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
