<template>
  <el-row class="row">
    <el-col>
      <h3>垃圾分类</h3>
    </el-col>
    <!--添加垃圾分类-->
    <el-col :span="7">
      <h5 v-text="type==='add'?'添加物品：':'修改物品：'"></h5>
      <el-form>
        <el-form-item label="物品名称">
          <el-input v-model="garbage.garbageName" style="width:200px" placeholder="请输入物品名称"/>
        </el-form-item>
        <el-form-item label="物品类别">
          <el-select v-model="garbage.gcId" placeholder="请选择类别">
            <el-option
              v-for="item in categories"
              :key="item.gcId"
              :label="item.gcName"
              :value="item.gcId">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <el-button type="primary" v-text="type==='add'?'添加':'修改'" @click="submit">添加</el-button>
      <el-button type="danger" v-text="type==='add'?'重置':'取消'" @click="cancel"></el-button>
    </el-col>

    <el-col :span="17">
      <el-table :data="garbages" stripe>
        <el-table-column prop="garbageName" label="名称" sortable></el-table-column>
        <el-table-column prop="gcName" label="类别"></el-table-column>
        <el-table-column prop="userName" label="添加用户"></el-table-column>
        <el-table-column>
          <template slot="header">
            <el-input
              placeholder="输入物品名称进行查询"/>
          </template>
          <template slot-scope="scope">
            <el-button type="info" @click="getGarbage(scope.row.garbageId,scope.row.garbageName,scope.row.gcId)">修改
            </el-button>
            <el-popconfirm
              title="确定删除吗？"
              icon="el-icon-info"
              icon-color="red"
              @confirm="delGarbage(scope.row.garbageId)"
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
  import storage from "../../assets/storage";

  export default {
    name: "GarbageTable",
    data() {
      return {
        categories: [],
        garbage: {
          garbageName: '',
          gcId: ''
        },
        garbages: [],
        type: 'add'
      }
    },
    methods: {
      submit() {
        if (this.type === 'add') {
          this.addGarbage();
        } else {
          this.updGarbage();
        }
      },
      cancel() {
        this.garbage = {};
        if (this.type === 'upd') {
          this.type = 'add'
        }
      },
      getCategories() {
        api.getCategories().then(resp => {
          this.categories = resp.data.obj;
        })
      },
      addGarbage() {
        this.garbage.garbageAddUser = storage.getUser().userId;
        api.addGarbage(this.garbage).then(resp => {
          if (resp.data.code === 1) {
            this.garbage = {};
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
      getGarbages() {
        api.getGarbages().then(resp => {
          this.garbages = resp.data.obj;
        })
      },
      updGarbage() {
        api.updGarbage(this.garbage).then(resp => {
          if (resp.data.code === 1) {
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
      delGarbage(id) {
        api.delGarbage(id).then(resp => {
          if (resp.data.code === 1) {
            this.getGarbages();
          }
          this.$message({
            showClose: true,
            message: resp.data.msg,
            type: resp.data.type,
            duration: "1000"
          })
        })
      }, getGarbage(id, name, gcId) {
        this.garbage.garbageId = id;
        this.garbage.garbageName = name;
        this.garbage.gcId = gcId;
        this.type = 'upd';
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
