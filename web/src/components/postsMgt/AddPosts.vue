<template>
  <el-row class="row">
    <!--标题-->
    <el-col :span="24">
      <h3>添加文章</h3>
    </el-col>
    <!--编辑器-->
    <el-col :span="19">
      <el-row class="editor">
        <el-col :span="24">
          <el-input v-model="posts.postsTitle" placeholder="请输入标题"/>
        </el-col>
        <el-col :span="24">
          <quill-editor v-model="posts.postsContent"/>
        </el-col>
      </el-row>
    </el-col>
    <!--右侧操作栏-->
    <el-col :span="5">
      <el-row class="operate-bar">
        <el-col :span="24" class="title">
          <span>发布</span>
        </el-col>
        <el-col :span="24">
          <el-button type="primary" plain @click="submit">发布</el-button>
        </el-col>
      </el-row>
    </el-col>
  </el-row>
</template>

<script>
  import {quillEditor} from 'vue-quill-editor'
  import 'quill/dist/quill.core.css'
  import 'quill/dist/quill.snow.css'
  import 'quill/dist/quill.bubble.css'
  import api from "../../api/api";

  export default {
    name: "AddPosts",
    components: {
      quillEditor
    },
    data() {
      return {
        posts: {
          postsTitle: '',
          postsContent: ''
        }
      }
    },
    methods: {
      submit() {
        api.addPosts(this.posts).then(resp => {
          if (resp.data.code === 1) {
            this.$message({
              type: resp.data.type,
              message: resp.data.msg,
              duration: 1000
            })
          }
          console.log(resp);
        })
      }
    }
  }

</script>

<style scoped>
  .row > .el-col {
    padding: 15px;
  }

  .editor > .el-col {
    padding-bottom: 10px;
  }

  .title {
    width: 100%;
    height: 30px;
    border-bottom: 1px solid #ccd0d4;
  }

  .operate-bar > .el-col {
    margin: 10px;
  }
</style>

