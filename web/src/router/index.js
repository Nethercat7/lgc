import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
//用户模块
import Login from '@/components/user/Login'
//用户管理模块
import UserAdd from "@/components/userMgt/UserAdd";
import UserTable from "@/components/userMgt/UserTable";
import UserUpd from "@/components/userMgt/UserUpd";

//角色管理模块
import RoleTable from '@/components/roleMgt/RoleTable';
import RoleAdd from '@/components/roleMgt/RoleAdd';
import RoleUpd from '@/components/roleMgt/RoleUpd';

Vue.use(Router)


const originalPush = Router.prototype.push
  Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index,
      children: [
        //用户管理
        {
          path: '/userMgt/users',
          name: 'UserTable',
          component: UserTable
        },
        {
          path: '/userMgt/add',
          name: 'UserAdd',
          component: UserAdd
        },
        {
          path: '/userMgt/upd',
          name: 'UserUpd',
          component: UserUpd
        },
        //角色管理
        {
          path: '/roleMgt/roles',
          name: 'RoleTable',
          component: RoleTable
        },
        {
          path: '/roleMgt/add',
          name: 'RoleAdd',
          component: RoleAdd
        },
        {
          path: '/roleMgt/upd',
          name: 'RoleUpd',
          component: RoleUpd
        }
      ]
    },
    {
      path: '/user/login',
      name: 'Login',
      component: Login
    }
  ]
})
