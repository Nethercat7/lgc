<template>
  <el-row class="row">
    <!--标题-->
    <el-col :span="24">
      <h3>注册用户</h3>
    </el-col>

    <el-col :span="24">
      <el-form :model="user" :rules="rules" ref="userAddForm" label-width="100px" style="width: 700px;margin:auto">
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="user.userName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称" prop="userNickname">
          <el-input v-model="user.userNickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="userPwd">
          <el-input type="password" v-model="user.userPwd" show-password autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="userGender">
          <el-radio-group v-model="user.userGender">
            <el-radio label="1">男</el-radio>
            <el-radio label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="电话号码" prop="userPhone">
          <el-input v-model="user.userPhone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电子邮箱" prop="userEmail">
          <el-input v-model="user.userEmail" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色" prop="userRole">
          <el-transfer v-model="user.roleIds" :data="data" :titles="title"></el-transfer>
        </el-form-item>
        <el-form-item label="状态" prop="userStatus">
          <el-radio-group v-model="user.userStatus">
            <el-radio label="0">正常</el-radio>
            <el-radio label="1">冻结</el-radio>
            <el-radio label="2">封锁</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('userAddForm')">添加</el-button>
          <el-button type="danger" @click="resetForm('userAddForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>
  import storage from "../../assets/storage";
  import api from "../../api/api";

  export default {
    name: "UserAdd",
    data() {
      let validateName = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入用户名'));
        }
        callback()
      };
      let validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        }
        callback()
      };
      return {
        user: {},
        rules: {
          userName: [
            {required: true, validator: validateName, trigger: 'blur'}
          ],
          userPwd: [
            {required: true, validator: validatePass, trigger: 'blur'}
          ],
          userGender: [
            {required: true, message: '请选择性别', trigger: 'change'}
          ],
          userPhone: [
            {required: false, message: '请输入电话号码', trigger: 'blur'}
          ],
          userEmail: [
            {required: false, message: '请输入电子邮箱', trigger: 'blur'}
          ],
          roleIds: [
            {required: true, message: '请选择至少一个角色', trigger: 'blur'}
          ]
        },
        data: [],
        title: ['可分配角色', '已分配角色']
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            api.addUser(this.user).then(resp => {
              if (resp.data.code === 1) {
                this.resetForm(formName);
              }
              if (resp.data !== '') {
                this.$message({
                  showClose: true,
                  message: resp.data.msg,
                  type: resp.data.obj,
                  duration: "1000"
                });
              }
            }).catch(err => {
              console.log(err);
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      getRoles() {
        api.getRoles().then(resp => {
          let arr = [];
          for (let i = 0; i < resp.data.obj.length; i++) {
            arr.push({
              key: resp.data.obj[i].roleId,
              label: resp.data.obj[i].roleName,
            });
          }
          this.data = arr
        }).catch(err => {
          console.log(err);
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
