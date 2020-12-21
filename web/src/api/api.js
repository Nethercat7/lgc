import http from '../utils/http'

let request='http://127.0.0.1:8080'

let api={
  //用户管理模块
  getUsers(){
    return http.get(`${request}/userMgt/getUsers`)
  },
  addUser(params){
    return http.post(`${request}/user/add`,params)
  },
  updUser(params){
    return http.post(`${request}/userMgt/upd`,params)
  },
  //角色管理模块
  getRoles(){
    return http.get(`${request}/roleMgt/getRoles`)
  },
  addRole(params){
    return http.post(`${request}/roleMgt/add`,params)
  },
  getPerms(){
    return http.get(`${request}/roleMgt/getPerms`)
  },
  updRole(params){
    return http.post(`${request}/roleMgt/upd`,params)
  },
  //分类管理模块
  addCategory(params) {
    return http.post(`${request}/garbage/addCategory`,params)
  },
  getCategories(){
    return http.get(`${request}/garbage/getCategories`)
  },
  delCategory(params){
    return http.get(`${request}/garbage/delCategory?id=`+params)
  },
  updCategory(params){
    return http.post(`${request}/garbage/updCategory`,params)
  },
  addGarbage(params){
    return http.post(`${request}/garbage/addGarbage`,params)
  },
  getGarbages(){
    return http.get(`${request}/garbage/getGarbages`)
  },
  updGarbage(params){
    return http.post(`${request}/garbage/updGarbage`,params)
  },
  delGarbage(params){
    return http.get(`${request}/garbage/delGarbage?garbageId=`+params)
  },
  //文章管理模块
  addPosts(params){
    return http.post(`${request}/postsMgt/addPosts`,params)
  },
  getPosts(){
    return http.get(`${request}/postsMgt/getPosts`)
  },
  updPosts(params) {
    return http.post(`${request}/postsMgt/updPosts`,params)
  },
  getPostsById(params){
    return http.get(`${request}/postsMgt/getPostsById?id=`+params)
  },
  delPosts(params){
    return http.get(`${request}/postsMgt/delPosts?id=`+params)
  },
  addPostsCategory(params){
    return http.post(`${request}/postsMgt/addCategory`,params)
  },
  getPostsCategories(){
    return http.get(`${request}/postsMgt/getCategories`)
  },
  getPostsCategory(params){
    return http.get(`${request}/postsMgt/getCategoryById?id=`+params)
  },
  updPostsCategory(params){
    return http.post(`${request}/postsMgt/updCategory`,params)
  },
  delPostsCategory(params){
    return http.get(`${request}/postsMgt/delCategory?id=`+params)
  },
  //文件模块
  delFile(params){
    return http.post(`${request}/file/del`,params)
  }
};

export default api
