<template>
  <el-row class="row">
    <el-col :span="24">
      <h3>更新角色</h3>
    </el-col>
    <el-col>
      <el-form class="width-50-center" :model="role" :rules="rules" ref="roleUpdForm" label-width="100px">
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
        <el-form-item label="状态" prop="roleStatus">
          <el-radio-group v-model="role.roleStatus">
            <el-radio :label="0">正常</el-radio>
            <el-radio :label="1">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('roleUpdForm')">修改</el-button>
          <el-button type="danger" @click="resetForm('roleUpdForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>
  import node from "../../assets/node";
  import api from "../../api/api";

  export default {
    name: "RoleAdd",
    data() {
      return {
        role: {
          roleCode: '',
          roleName: ''
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
          label: 'permsName'
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.role.permsId = node.getCheckedKeys(this.$refs);
            this.updRole(this.role);
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        node.setCheckedKeys(this.$refs, []);
        this.$refs[formName].resetFields();
      },
      updRole(obj) {
        api.updRole(obj).then(resp => {
          this.$message({
            showClose: true,
            message: resp.data.msg,
            type: resp.data.obj,
            duration: "1000"
          })
        })
      },
      getRole(id) {
        api.getRoleById(id).then(res=>{
          this.role = res.data.obj;
          //设置已经拥有的权限
          let perms = this.role.perms;
          let permsData = [];
          for (let i = 0; i < perms.length; i++) {
            permsData.push(perms[i].permsId);
          }
          node.setCheckedKeys(this.$refs, permsData);
        })
      },
      getPerms() {
        api.getPerms().then(resp => {
          this.permsData = resp.data.obj;
        })
      }
    },
    created() {
      this.getRole(this.$route.query.id);
      this.getPerms()
    }
  }
</script>

<style scoped>

</style>
