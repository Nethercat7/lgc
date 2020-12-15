<template>
  <div>
    <!--添加垃圾分类-->
    <div>
      <span>添加分类：</span>
      <el-input v-model="garbage.garbageName" style="width:200px" placeholder="请输入物品名称"/>
      <el-select v-model="garbage.gcId" placeholder="请选择类别">
        <el-option
          v-for="item in categories"
          :key="item.gcId"
          :label="item.gcName"
          :value="item.gcId">
        </el-option>
      </el-select>
      <el-button type="primary" @click="addGarbage">添加</el-button>
    </div>

    <!--垃圾分类列表-->
    <div>
      <el-table :data="garbages" stripe>
        <el-table-column prop="garbageName" label="名称"></el-table-column>
        <el-table-column prop="gcName" label="类别"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="info" @click="openDialog(scope.row.garbageId,scope.row.garbageName,scope.row.gcId)">修改</el-button>
            <el-popconfirm
              confirm-button-text='好的'
              cancel-button-text='不用了'
              icon="el-icon-info"
              icon-color="red"
              title="确定删除吗？"
              @confirm="delGarbage(scope.row.garbageId)"
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
          <el-input v-model="garbage2.garbageName" style="width:49%" placeholder="请输入物品名称"/>
          <el-select v-model="garbage2.gcId" style="width:49%" placeholder="请选择类别">
            <el-option
              v-for="item in categories"
              :key="item.gcId"
              :label="item.gcName"
              :value="item.gcId">
            </el-option>
          </el-select>
        </span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="updGarbage">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import api from "../../api/api";

  export default {
    name: "GarbageTable",
    data() {
      return {
        categories:[],
        garbage:{
          garbageName:''
        },
        garbages:[],
        garbage2:{
          garbageName:'',
          gcId:''
        },
        dialogVisible:false
      }
    },
    methods:{
      getCategories() {
        api.getCategories().then(resp => {
          this.categories = resp.data.obj;
        })
      },
      addGarbage(){
        api.addGarbage(this.garbage).then(resp=>{
          if(resp.data.code===1){
            this.garbage={};
            this.getGarbages();
          }
          this.$message({
            showClose: true,
            message: resp.data.msg,
            type: resp.data.type,
            duration: "1000"
          });
        })
      },
      getGarbages(){
        api.getGarbages().then(resp=>{
          this.garbages=resp.data.obj;
        })
      },
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      },
      openDialog(garbageId,garbageName,gcId){
        this.garbage2.garbageId=garbageId;
        this.garbage2.garbageName=garbageName;
        this.garbage2.gcId=gcId;
        this.dialogVisible=true;
      },
      updGarbage(){
        api.updGarbage(this.garbage2).then(resp=>{
          if(resp.data.code===1){
            this.getGarbages();
            this.dialogVisible=false;
          }
          this.$message({
            showClose: true,
            message: resp.data.msg,
            type: resp.data.type,
            duration: "1000"
          });
        })
      },
      delGarbage(id){
        api.delGarbage(id).then(resp=>{
          if(resp.data.code===1) {
            this.getGarbages();
          }
          this.$message({
            showClose:true,
            message:resp.data.msg,
            type:resp.data.type,
            duration:"1000"
          })
        })
      }
    },
    created() {
      this.getCategories();
      this.getGarbages();
    }
  }
</script>

<style scoped>

</style>
