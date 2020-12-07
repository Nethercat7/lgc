import http from '../utils/http'

let request='http://127.0.0.1:8080'

let api={
  //用户管理模块
  getUsers(){
    return http.get(`${request}/userMgt/getUsers`)
  },
  addUser(params){
    return http.post(`${request}/userMgt/add`,params)
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
  }
};

export default api
