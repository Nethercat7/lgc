<template>
  <div style="display: flex">
    <el-container style="min-height: 100vh;">
      <el-main style="align-self:center">
        <el-form :model="loginForm" :rules="rules" ref="loginForm" label-width="70px" class="loginForm">
          <el-form-item label="用户名" prop="name">
            <el-input type="text" v-model="loginForm.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="pass">
            <el-input type="password" v-model="loginForm.pass" show-password autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="login('loginForm')">登入</el-button>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: "Login",
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
        loginForm: {
          name: '',
          pass: '',
        },
        rules: {
          name: [
            {validator: validateName, trigger: 'blur'}
          ],
          pass: [
            {validator: validatePass, trigger: 'blur'}
          ],
        },
      };
    },
    methods: {
      login(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            //发送登入请求
            this.$http.post('/user/login?name=' + this.loginForm.name + "&pwd=" + this.loginForm.pass,).then(resp => {
              if (resp.data.code === 1) {
                console.log(resp);
                localStorage.setItem("token",resp.data.obj);
                this.$router.push("/");//登入成功后跳转到主页
              }else{
                this.$message({
                  showClose: true,
                  message: resp.data.msg,
                  type: "error",
                  duration: "1000"
                });
              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      }
    }
  }
</script>

<style scoped>
  .loginForm {
    width: 500px;
    margin: 0 auto;
  }
</style>
