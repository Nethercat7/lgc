<template>
  <el-row class="row">
    <el-col>
      <h3>所有文章</h3>
    </el-col>
    <el-col>
      <el-table :data="postsList" stripe>
        <el-table-column prop="title" label="标题" sortable></el-table-column>
        <el-table-column prop="username" label="发布者" sortable></el-table-column>
        <el-table-column prop="pcName" label="类型" sortable></el-table-column>
        <el-table-column prop="postsAddTime" label="发布日期" sortable></el-table-column>
        <el-table-column prop="postsUpdTime" label="修改日期" sortable></el-table-column>
        <el-table-column prop="postsStatus" label="状态"></el-table-column>
        <el-table-column>
          <template slot="header" slot-scope="scope">
            <el-input
              placeholder="输入文章标题或者发布者用户名进行检索"/>
          </template>
          <template slot-scope="scope">
            <el-button type="info" @click="updPosts(scope.row.postsId)">修改</el-button>
            <el-popconfirm
              title="确定删除吗？"
              icon="el-icon-info"
              icon-color="red"
              @confirm="delPosts(scope.row.postsId,scope.row.image,scope.row.postsHasPic)">
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
        api.getPosts().then(res => {
          let data=res.data.obj;
          for(let i=0;i<data.length;i++){
            if(data[i].postsStatus===0){
              data[i].postsStatus='正常'
            }else{
              data[i].postsStatus='草稿'
            }
          }
          this.postsList=data;
        })
      },
      updPosts(id) {
        this.$router.push({path: "/postsMgt/add", query: {id: id}})
      },
      delPosts(id, key, hasPic) {
        api.delPosts(id).then(resp => {
          if (resp.data.code === 1) {
            this.getPosts();
            if (hasPic === 1) {
              key = key.substr(key.lastIndexOf('posts'));
              api.delFile(key);
              console.log('del');
            }
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
      this.getPosts();
    }
  }
</script>

<style scoped>

</style>
