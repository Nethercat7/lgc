<template>
  <el-row class="row">
    <el-col :span="24">
      <h3>分类目录</h3>
    </el-col>
    <!--添加类别-->
    <el-col :span="7">
      <h5>添加类别</h5>
      <el-form>
        <el-form-item label="名称">
          <el-input v-model="postsCategory.pcName" placeholder="请输入类别名称"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="postsCategory.pcDescription" type="textarea"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" plain @click="submit">添加</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <!--类别列表-->
    <el-col :span="17">
      <h5>类别列表</h5>
      <el-table stripe>
        <el-table-column label="名称"></el-table-column>
        <el-table-column label="描述"></el-table-column>
        <el-table-column label="状态"></el-table-column>
        <el-table-column label="操作"></el-table-column>
      </el-table>
    </el-col>
  </el-row>
</template>

<script>
  import api from "../../api/api";

  export default {
    name: "PostsCategories",
    data() {
      return {
        postsCategory: {
          pcName: '',
          pcDescription: ''
        }
      }
    },
    methods: {
      submit() {
        api.addPostsCategory(this.postsCategory).then(resp => {
          if(resp.data.code===1){
            this.postsCategory={};
          }
          this.$message({
            type: resp.data.type,
            message: resp.data.msg,
            duration: 1000
          })
        })
      }
    }
  }
</script>

<style scoped>
  >>> .el-textarea__inner {
    height: 150px;
  }
</style>
