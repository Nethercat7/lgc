<template>
  <el-row class="row">
    <el-col>
      <h3>分类类别</h3>
    </el-col>

    <el-col :span="7">
      <h5 v-text="type==='add'?'添加类别：':'修改类别：'"></h5>
      <el-form ref="category">
        <el-form-item label="类别名称">
          <el-input v-model="garbageCategory.gcName" placeholder="请输入类别名称"/>
        </el-form-item>
        <el-form-item label="类别类型">
          <el-input v-model="garbageCategory.gcType" placeholder="请输入类别类型"/>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="garbageCategory.gcStatus">
            <el-radio :label="0">正常</el-radio>
            <el-radio :label="1">禁用</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <el-button type="primary" @click="submit(type)" v-text="type==='add'?'添加':'修改'"></el-button>
      <el-button type="danger" v-text="type==='add'?'重置':'取消'" @click="cancel(type)"></el-button>
    </el-col>

    <!--数据表格-->
    <el-col :span="17">
      <el-table :data="categories" stripe>
        <el-table-column prop="gcName" label="名称" sortable></el-table-column>
        <el-table-column prop="gcType" label="类型"></el-table-column>
        <el-table-column prop="status" label="状态"></el-table-column>
        <el-table-column>
          <template slot="header">
            <el-input
              placeholder="输入类别名称进行查询"/>
          </template>
          <template slot-scope="scope">
            <el-button type="info"
                       @click="getCategory(scope.row.gcId,scope.row.gcName,scope.row.gcType,scope.row.gcStatus)">修改
            </el-button>
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
  </el-row>
</template>

<script>
  import api from "../../api/api";

  export default {
    name: "GarbageCategoryTable",
    data() {
      return {
        categories: [],
        garbageCategory: {
          gcName: null,
          gcType: null,
          gcStatus: null
        },
        type: 'add'
      }
    },
    methods: {
      submit(type) {
        if (type === 'add') {
          this.addCategory();
        } else {
          this.updCategory();
        }
      },
      cancel(type) {
        this.garbageCategory = {};
        if (type === 'upd') {
          this.type = 'add';
        }
      },
      addCategory() {
        api.addCategory(this.garbageCategory).then(resp => {
          if (resp.data.code === 1) {
            this.getCategories();
            this.garbageCategory = {};
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
          if (resp.data.code === 1) {
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
        api.getCategories().then(res => {
          let data = res.data.obj;
          for (let i = 0; i < data.length; i++) {
            if (data[i].gcStatus === 0) {
              data[i].status = '正常'
            } else if (data[i].gcStatus === 1) {
              data[i].status = '禁用'
            }
          }
          this.categories = data;
        })
      },
      updCategory() {
        api.updCategory(this.garbageCategory).then(resp => {
          if (resp.data.code === 1) {
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
      getCategory(id, name, type, status) {
        this.garbageCategory.gcId = id;
        this.garbageCategory.gcName = name;
        this.garbageCategory.gcType = type;
        this.garbageCategory.gcStatus = status;
        this.type = 'upd';
      }
    },
    created() {
      this.getCategories()
    }
  }
</script>

<style scoped>

</style>
