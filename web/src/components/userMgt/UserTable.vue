<template>
  <el-table
    :data="users"
    stripe
    style="width: 100%">
    <el-table-column
      prop="userName"
      label="用户名">
    </el-table-column>
    <el-table-column
      prop="userGender"
      label="性别">
    </el-table-column>
    <el-table-column
      prop="userPhone"
      label="电话号码">
    </el-table-column>
    <el-table-column
      prop="userEmail"
      label="电子邮箱">
    </el-table-column>
    <el-table-column
      prop="userStatus"
      label="状态">
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button type="info" @click="updUser(scope.row.userName)">修改</el-button>
        <el-popconfirm
          title="确定删除吗？"
          icon="el-icon-info"
          icon-color="red"
          @confirm="delUser(scope.row.userId)"
        >
          <el-button type="danger" slot="reference">删除</el-button>
        </el-popconfirm>
      </template>
    </el-table-column>
    <router-view/>
  </el-table>
</template>

<script>
  import storage from "../../assets/storage";
  import api from "../../api/api";

  export default {
    name: "UserTable",
    data() {
      return {
        users: []
      }
    },
    methods: {
      getUsers() {
        api.getUsers().then(resp => {
          this.users = resp.data.obj;
        }).catch(err => {
          console.log(err);
        })
      },
      updUser(name) {
        this.$router.push({path: '/userMgt/upd', query: {name}})
      },
      delUser(id) {
        if (confirm("确认删除?")) {
          this.$http.get("/userMgt/del?id=" + id, {
            headers: {
              token: storage.get('token')
            }
          }).then(resp => {
            if (resp.data.code === 1) {
              this.getUsers();
            }
            this.$message({
              showClose: true,
              message: resp.data.msg,
              type: resp.data.obj,
              duration: "1000"
            });
          }).catch(err => {
            console.log(err);
          })
        }
      }
    },
    created() {
      this.getUsers();
    }
  }
</script>

<style scoped>

</style>
