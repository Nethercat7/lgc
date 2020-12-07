<template>
  <div>
    <div>
      <span>添加类别：</span>
      <el-input style="width:200px" v-model="garbageCategory.gcName"/>
      <el-button type="primary" @click="addCategory">添加</el-button>
    </div>
    <!--数据表格-->
    <div>
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
    </div>

    <!--dialog 修改弹出框-->
    <div>
      <el-dialog title="修改名称" :visible.sync="dialogVisible" width="30%" :before-close="handleClose">
        <span>
          <el-input v-model="temp.gcName"></el-input>
        </span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="updCategory">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
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
          for (let i = 0; i < resp.data.obj.length; i++) {
            resp.data.obj[i].gcId = resp.data.obj[i].gcId.toFixed(0);
          }
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
