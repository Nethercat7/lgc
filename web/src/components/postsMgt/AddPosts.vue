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
          <quill-editor
            ref="myQuillEditor"
            v-model="posts.postsContent"
            :options="editorOption"/>
        </el-col>
        <!-- 编辑器图片上传 -->
        <el-col>
          <el-upload
            style="display: none"
            class="quill-uploader"
            ref="quillUploader"
            :action="action"
            :show-file-list="false"
            :on-success="handlePostsPicUpload">
          </el-upload>
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
            :on-change="handelChange"
            :auto-upload="false">
            <el-button size="small" type="primary" plain>添加图片</el-button>
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
              v-if="item.pcStatus===0"></el-option>
          </el-select>
        </el-col>
        <el-col>
          <div style="padding-bottom: 10px;">文章状态：</div>
          <el-select v-model="posts.postsStatus" placeholder="文章状态">
            <el-option
              label="已发布"
              :value="0"></el-option>
            <el-option
              label="草稿"
              :value="1"></el-option>
          </el-select>
        </el-col>
        <el-col :span="24">
          <el-button type="primary" plain @click="submit">{{title==='修改文章'?'修改':'发布'}}</el-button>
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
        posts: {},
        fileList: [],
        categories: [],
        action: 'http://127.0.0.1:8080/file/upload?type=posts&id=' + storage.getUser().userId,
        hasPic: false,
        title: '',
        key: '',
        editorOption: {
          placeholder: "在此输入内容",
          modules: {
            toolbar: {
              //配置工具栏
              container: [
                ["bold", "italic", "underline", "strike"], // 加粗 斜体 下划线 删除线
                ["blockquote", "code-block"], // 引用  代码块
                [{header: 1}, {header: 2}], // 1、2 级标题
                [{list: "ordered"}, {list: "bullet"}], // 有序、无序列表
                [{script: "sub"}, {script: "super"}], // 上标/下标
                [{indent: "-1"}, {indent: "+1"}], // 缩进
                // [{'direction': 'rtl'}],                         // 文本方向
                [{size: ["small", false, "large", "huge"]}], // 字体大小
                [{header: [1, 2, 3, 4, 5, 6, false]}], // 标题
                [{color: []}, {background: []}], // 字体颜色、字体背景颜色
                [{font: []}], // 字体种类
                [{align: []}], // 对齐方式
                ["clean"], // 清除文本格式
                ["link", "image", "video"] // 链接、图片、视频
              ],
              handlers: {
                image: function (val) {
                  if (val) {
                    document.querySelector('.quill-uploader input').click()
                  } else {
                    this.quill.format('image', false);
                  }
                }
              }
            }
          }
        },
        status: ''
      }
    },
    methods: {
      submit() {
        //校验
        if (this.posts.title === '') {
          this.$message.error('请输入文章标题')
        } else if (this.posts.postsContent === '') {
          this.$message.error('请输入文章内容')
        } else if (this.posts.pcId == null) {
          this.$message.error('请选择文章类别')
        } else {
          if (this.hasPic) {
            this.$refs.upload.submit();
          } else {
            if (this.$route.query.id) {
              this.updPosts();
            } else {
              this.addPosts();
            }
          }
        }
      },
      getPostsById(id) {
        api.getPostsById(id).then(resp => {
          this.posts = resp.data.obj;
          //将封面图片添加至fileList中
          if (this.posts.postsHasPic === 1) {
            let name = this.posts.image.lastIndexOf('/') + 1;
            this.fileList.push({name: this.posts.image.substr(name), url: this.posts.image});
          }
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
        this.hasPic = false;
        if (this.$route.query.id) {
          this.key = this.posts.image.substr(this.posts.image.lastIndexOf('posts'));
        }
        this.posts.image = null;
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
        api.updPosts(this.posts).then(res => {
          if (res.data.code === 1) {
            //删除图片服务器上的标题图片
            if (!this.hasPic) {
              api.delFile(this.key);
            }
          }
          this.$message({
            type: res.data.type,
            message: res.data.msg,
            duration: 1000
          })
        })
      },
      /*编辑器设置*/
      handlePostsPicUpload(res) {
        if (res.code === 1) {
          // 获取富文本组件实例
          let quill = this.$refs["myQuillEditor"].quill;
          // 获取光标所在位置
          let length = quill.getSelection().index;
          // 插入图片insertEmbed()三个参数，前两个固定即可
          quill.insertEmbed(length, "image", res.obj);
          // 调整光标到最后
          quill.setSelection(length + 1);
        } else {
          this.$message.error(res.msg);
        }
      }
    },
    created() {
      if (this.$route.query.id) {
        this.getPostsById(this.$route.query.id);
        this.title = '修改文章'
      } else {
        this.title = '发布文章'
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
</style>

