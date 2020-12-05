<template>
  <div>
    <el-form :model="role" :rules="rules" ref="roleAddForm" label-width="100px" style="width: 700px;margin:auto">
      <el-form-item label="角色代号" prop="roleCode">
        <el-input v-model="role.roleCode" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="角色名称" prop="roleName">
        <el-input v-model="role.roleName" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="权限分配">
        <el-tree
          :data="permsData"
          :props="defaultProps"
          node-key="id2String"
          ref="tree"
          show-checkbox
          default-expand-all
          ></el-tree>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('roleAddForm')">添加</el-button>
        <el-button type="danger" @click="resetForm('roleAddForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import storage from "../../assets/storage";
  import node from "../../assets/node";
  import api from "../../api/api";

  export default {
    name: "RoleAdd",
    data() {
      return {
        role: {
          roleCode: '',
          roleName: '',
        },
        rules: {
          roleCode: [
            {required: true, message: '请输入角色代号', trigger: 'blur'}
          ],
          roleName: [
            {required: true, message: '请输入角色名称', trigger: 'blur'}
          ]
        },
        permsData: [],
        defaultProps: {
          children: 'children',
          label: 'permsName',
        }
      }
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.role.permsId=this.getCheckedKeys();
            this.addRole(this.role);
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        node.setCheckedKeys(this.$refs,[]);
        this.$refs[formName].resetFields();
      },
      addRole(obj) {
        api.addRole(obj).then(resp => {
          if (resp.data.code === 1) {
            this.role = {}
          }
          this.$message({
            showClose: true,
            message: resp.data.msg,
            type: resp.data.obj,
            duration: "1000"
          })
        })
      },
      getPerms() {
        api.getPerms().then(resp => {
          this.permsData = resp.data.obj;
        })
      },
      getCheckedKeys() {
        return this.$refs.tree.getCheckedKeys(true,false);
      }
    },
    created() {
      this.getPerms();
    }
  }
</script>

<style scoped>

</style>
