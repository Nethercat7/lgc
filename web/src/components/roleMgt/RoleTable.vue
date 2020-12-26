<template>
  <el-row class="row">
    <el-col :span="24">
      <h3>所有角色</h3>
    </el-col>
    <el-col>
      <el-table
        :data="roles"
        stripe
        style="width: 100%">
        <el-table-column
          prop="roleCode"
          label="代号"
          sortable>
        </el-table-column>
        <el-table-column
          prop="roleName"
          label="名称"
          sortable>
        </el-table-column>
        <el-table-column
          prop="roleAddTime"
          label="添加时间"
          sortable
        ></el-table-column>
        <el-table-column
          prop="roleUpdTime"
          label="更新时间"
          sortable
        ></el-table-column>
        <el-table-column
          prop="roleStatus"
          label="状态"
        ></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="info" @click="updRole(scope.row.roleId)">修改</el-button>
            <el-popconfirm
              title="确定删除吗？"
              icon="el-icon-info"
              icon-color="red"
              @confirm="delRole(scope.row.roleId)"
            >
              <el-button type="danger" slot="reference">删除</el-button>
            </el-popconfirm>

          </template>
        </el-table-column>
      </el-table>
    </el-col>
  </el-row>
</template>

<script>
  import storage from "../../assets/storage";
  import api from "../../api/api";

  export default {
    name: "Edit",
    data() {
      return {
        roles: [],
        role: {}
      }
    },
    methods: {
      getRoles() {
        api.getRoles().then(res => {
          let data = res.data.obj;
          for (let i = 0; i < data.length; i++) {
            if (data[i].roleStatus === 0) {
              data[i].roleStatus = '正常'
            } else if (data[i].roleStatus === 1) {
              data[i].roleStatus = '禁用'
            }
          }
          this.roles = data;
        })
      },
      updRole(id) {
        this.$router.push({path: "/roleMgt/upd", query: {id: id}})
      },
      delRole(id) {
        api.delRole(id).then(resp => {
          if (resp.data.code === 1) {
            this.getRoles();
          }
          this.$message({
            showClose: true,
            message: resp.data.msg,
            type: resp.data.obj,
            duration: "1000"
          })
        })
      }
    },
    created() {
      this.getRoles()
    }
  }
</script>

<style scoped>

</style>
