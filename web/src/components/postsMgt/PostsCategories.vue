<template>
  <el-row class="row">
    <el-col :span="24">
      <h3>文章类别</h3>
    </el-col>
    <!--添加类别-->
    <el-col :span="7">
      <h5 v-if="add">添加类别</h5>
      <h5 v-if="!add">修改类别</h5>
      <el-form>
        <el-form-item label="名称">
          <el-input v-model="postsCategory.pcName" placeholder="请输入类别名称"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="postsCategory.pcDescription" type="textarea"></el-input>
        </el-form-item>
        <el-form-item v-if="!add" label="状态">
          <el-radio-group v-model="postsCategory.pcStatus">
            <el-radio :label="0">启用</el-radio>
            <el-radio :label="1">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item>
          <el-button v-if="add" type="primary" plain @click="submit('ADD')">添加</el-button>
          <el-button v-if="!add" type="primary" plain @click="submit('UPD')">修改</el-button>
          <el-button v-if="!add" type="primary" plain @click="cancel">取消</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <!--类别列表-->
    <el-col :span="17">
      <h5>类别列表</h5>
      <el-table :data="categories" stripe>
        <el-table-column prop="pcName" label="名称"></el-table-column>
        <el-table-column prop="pcDescription" label="描述"></el-table-column>
        <el-table-column prop="pcStatus2String" label="状态"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="info" @click="getCategory(scope.row.pcId)">修改</el-button>
            <el-popconfirm
              title="确定删除吗？"
              icon="el-icon-info"
              icon-color="red"
              @confirm="delCategory(scope.row.pcId)"
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
  import api from "../../api/api";

  export default {
    name: "PostsCategories",
    data() {
      return {
        postsCategory: {
          pcName: '',
          pcDescription: '',
          pcStatus: ''
        },
        categories: [],
        add: true
      }
    },
    methods: {
      submit(type) {
        if (type === 'ADD') {
          api.addPostsCategory(this.postsCategory).then(resp => {
            if (resp.data.code === 1) {
              this.postsCategory = {};
              this.getCategories();
            }
            this.$message({
              type: resp.data.type,
              message: resp.data.msg,
              duration: 1000
            })
          })
        } else {
          api.updPostsCategory(this.postsCategory).then(resp => {
            if (resp.data.code === 1) {
              this.getCategories();
            }
            this.$message({
              type: resp.data.type,
              message: resp.data.msg,
              duration: 1000
            })
          })
        }
      },
      cancel() {
        this.postsCategory = {};
        this.add = true;
      },
      getCategories() {
        api.getPostsCategories().then(resp => {
          this.categories = resp.data.obj;
        })
      },
      getCategory(id) {
        api.getPostsCategory(id).then(resp => {
          this.postsCategory = resp.data.obj;
          this.add = false;
          console.log(this.postsCategory);
        })
      },
      delCategory(id) {
        api.delPostsCategory(id).then(resp => {
          if (resp.data.code === 1) {
            this.getCategories();
          }
          this.$message({
            type: resp.data.type,
            message: resp.data.msg,
            duration: 1000
          })
        })
      }
    },
    created() {
      this.getCategories();
    }
  }
</script>

<style scoped>
  >>> .el-textarea__inner {
    height: 150px;
  }
</style>
