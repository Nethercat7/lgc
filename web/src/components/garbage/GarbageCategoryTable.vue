<template>
  <el-row class="row">
    <el-col>
      <h3>分类类别</h3>
    </el-col>

    <el-col>
      <span>添加类别：</span>
      <el-input style="width:200px" v-model="garbageCategory.gcName"/>
      <el-button type="primary" @click="addCategory">添加</el-button>
    </el-col>

    <!--数据表格-->
    <el-col>
      <el-table :data="categories" stripe>
        <el-table-column prop="gcName" label="名称"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="info" @click="openDialog(scope.row.gcId,scope.row.gcName)">修改</el-button>
            <el-popconfirm
              confirm-button-text='好的'
              cancel-button-text='不用了'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除吗？"
              @confirm="delCategory(scope.row.gcId)"
            >
              <el-button type="danger" slot="reference">删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </el-col>

    <!--dialog 修改弹出框-->
    <el-col>
      <el-dialog title="修改名称" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
        <span>
          <el-input v-model="temp.gcName"></el-input>
        </span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="updCategory">确 定</el-button>
        </span>
      </el-dialog>
    </el-col>
  </el-row>
</template>

<script>
  import api from "../../api/api";

  export default {
    name: "GarbageCategoryTable",
    data() {
      return {
        categories: [],
        dialogVisible: false,
        temp: {
          gcName: ''
        },
        garbageCategory: {
          gcName: ''
        }
      }
    },
    methods: {
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      },
      openDialog(id, name) {
        this.temp.gcId = id;
        this.temp.gcName = name;
        this.dialogVisible = true;
      },
      addCategory() {
        api.addCategory(this.garbageCategory).then(resp => {
          if (resp.data.code === 1) {
            this.getCategories();
             this.garbageCategory.gcName = '';
          }
          this.$message({
            showClose: true,
            message: resp.data.msg,
            type: resp.data.type,
            duration: "1000"
          });
        })
      },
      delCategory(id) {
        api.delCategory(id).then(resp => {
          if(resp.data.code===1){
            this.getCategories();
          }
          this.$message({
            showClose: true,
            message: resp.data.msg,
            type: resp.data.type,
            duration: "1000"
          });
        })
      },
      getCategories() {
        api.getCategories().then(resp => {
          this.categories = resp.data.obj;
        })
      },
      updCategory(){
        api.updCategory(this.temp).then(resp=>{
          if(resp.data.code===1){
            this.getCategories();
            this.dialogVisible=false;
          }
          this.$message({
            showClose: true,
            message: resp.data.msg,
            type: resp.data.type,
            duration: "1000"
          });
        })
      }
    },
    created() {
      this.getCategories()
    }
  }
</script>

<style scoped>

</style>
