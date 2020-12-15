<template>
  <el-row class="row">
    <el-col :span="24">
      <el-table :data="postsList" stripe>
        <el-table-column prop="title" label="标题"></el-table-column>
        <el-table-column prop="postsAuthor" label="作者"></el-table-column>
        <el-table-column prop="postsAddTime" label="发布日期"></el-table-column>
        <el-table-column prop="postsUpdTime" label="修改日期"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="info">修改</el-button>
            <el-button type="danger">删除</el-button>
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
    data(){
      return{
        postsList:[]
      }
    },
    methods:{
      getPosts(){
        api.getPosts().then(resp=>{
          this.postsList=resp.data.obj;
          console.log(this.postsList);
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
