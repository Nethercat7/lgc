<template>
  <el-row class="row">
    <el-col :span="24">
      <h3>文章</h3>
    </el-col>
    <el-col :span="24">
      <el-table :data="postsList" stripe>
        <el-table-column prop="title" label="标题"></el-table-column>
        <el-table-column prop="postsAuthor" label="作者"></el-table-column>
        <el-table-column prop="pcName" label="类型"></el-table-column>
        <el-table-column prop="postsAddTime" label="发布日期"></el-table-column>
        <el-table-column prop="postsUpdTime" label="修改日期"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="info" @click="updPosts(scope.row.postsId)">修改</el-button>
            <el-popconfirm
              title="确定删除吗？"
              icon="el-icon-info"
              icon-color="red"
              @confirm="delPosts(scope.row.postsId)">
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
    name: "PostsList",
    data() {
      return {
        postsList: []
      }
    },
    methods: {
      getPosts() {
        api.getPosts().then(resp => {
          this.postsList = resp.data.obj;
        })
      },
      updPosts(id) {
        this.$router.push({path: "/postsMgt/add", query: {id: id}})
      },
      delPosts(id) {
        api.delPosts(id).then(resp => {
          if (resp.data.code === 1) {
            this.getPosts();
            this.$message({
              type: resp.data.type,
              message: resp.data.msg,
              duration: 1000
            })
          }
        })
      }
    },
    created() {
      this.getPosts();
    }
  }
</script>

<style scoped>

</style>
