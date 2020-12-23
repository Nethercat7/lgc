<template>
  <el-row class="row">
    <!--标题-->
    <el-col :span="24">
      <h3 v-text="title"></h3>
    </el-col>
    <!--编辑器-->
    <el-col :span="19">
      <el-row class="editor">
        <el-col :span="24">
          <el-input v-model="posts.title" placeholder="请输入标题"/>
        </el-col>
        <el-col :span="24">
          <quill-editor v-model="posts.postsContent"/>
        </el-col>
      </el-row>
    </el-col>
    <!--右侧操作栏-->
    <el-col :span="5">
      <!--设置封图片-->
      <el-row class="operate-bar">
        <el-col :span="24" class="title">
          <span>设置封面图片</span>
        </el-col>
        <el-col :span="24">
          <el-upload
            ref="upload"
            :action="action"
            :limit="1"
            :file-list="fileList"
            :before-remove="beforeRemove"
            :on-remove="handelRemove"
            :on-success="handleSuccess"
            :on-exceed="handleExceed"
            :auto-upload="false"
            :on-change="handelChange">
            <el-button size="small" type="primary" plain>点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
          </el-upload>
        </el-col>
      </el-row>
      <!--发布-->
      <el-row class="operate-bar">
        <el-col :span="24" class="title">
          <span>发布</span>
        </el-col>
        <el-col :span="24">
          <div style="padding-bottom: 10px;">选择文章类别：</div>
          <el-select v-model="posts.pcId" placeholder="请选择文章类别">
            <el-option
              v-for="item in categories"
              :key="item.pcId"
              :label="item.pcName"
              :value="item.pcId"
              v-if="item.pcStatus==0"></el-option>
          </el-select>
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
  import storage from "@/assets/storage";

  export default {
    name: "AddPosts",
    components: {
      quillEditor
    },
    data() {
      return {
        posts: {
          title: '',
          postsContent: ''
        },
        fileList: [],
        categories: [],
        action: 'http://127.0.0.1:8080/file/upload?type=posts&id=' + storage.getUser().userId,
        hasPic: false,
        picNoChange: true,
        title:''
      }
    },
    methods: {
      submit() {
        //校验
        if (this.posts.title === '') {
          this.$message.error('请输入文章标题')
        } else if (this.posts.postsContent === '') {
          this.$message.error('请输入文章内容')
        } else if (!this.hasPic) {
          this.$message.error('请添加标题图片')
        } else if (this.posts.pcId == null) {
          this.$message.error('请选择文章类别')
        } else {
          //3种情况
          //1.只修改文章内容，封面图片不改=不走上传通道
          //2.封面图片更换=走上传通道
          //3.新添加文章=走上传通道
          if (this.$route.query.id && this.picNoChange) {
          } else if (this.$route.query.id && !this.picNoChange) {
            this.$refs.upload.submit();
          } else {
            this.$refs.upload.submit();
          }
        }
      },
      getPostsById(id) {
        api.getPostsById(id).then(resp => {
          this.posts = resp.data.obj;
          //将封面图片添加至fileList中
          let start = this.posts.image.lastIndexOf('/') + 1;
          let end = this.posts.image.length;
          this.fileList.push({name: this.posts.image.substr(start, end), url: this.posts.image})
          this.hasPic = true;
        })
      },
      beforeRemove(file) {
        return this.$confirm(`确定移除 ${file.name}？`);
      },
      handleSuccess(res) {
        //封面图片上传成功后，上传文章内容。
        if (res.code === 1) {
          this.posts.image = res.obj;
          if (this.$route.query.id) {
            this.updPosts();
          } else {
            this.addPosts();
          }
        } else {
          this.$message({
            type: res.type,
            message: res.msg
          })
        }
      },
      handleExceed() {
        this.$message.warning(`最多只能上传一张封面图片`);
      },
      handelRemove() {
        let key = this.posts.image.substr(this.posts.image.lastIndexOf('posts'));
        api.delFile(key).then(res => {
          if (res.data.code === 1) {
            this.posts.image = null;
            this.hasPic = false;
            this.picNoChange = false;
          }
          this.$message({
            type: res.data.type,
            message: res.data.msg
          })
        })
      },
      getCategories() {
        api.getPostsCategories().then(resp => {
          this.categories = resp.data.obj;
        })
      },
      addPosts() {
        api.addPosts(this.posts).then(resp => {
          if (resp.data.code === 1) {
            this.posts = {};
            this.fileList = [];
          }
          this.$message({
            type: resp.data.type,
            message: resp.data.msg,
            duration: 1000
          })
        })
      },
      handelChange() {
        this.hasPic = true;
      },
      updPosts() {
        api.updPosts(this.posts).then(resp => {
          this.$message({
            type: resp.data.type,
            message: resp.data.msg,
            duration: 1000
          })
        })
      }
    },
    created() {
      if (this.$route.query.id) {
        this.getPostsById(this.$route.query.id);
        this.title='修改文章'
      }else{
        this.title='发布文章'
      }
      this.getCategories();
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

  >>> .ql-editor {
    min-height: 300px;
  }
</style>

