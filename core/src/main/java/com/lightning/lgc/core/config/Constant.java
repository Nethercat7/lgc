package com.lightning.lgc.core.config;

public class Constant {
    //返回编码
    public static final int SUCCESS=1;
    public static final int FAILED=0;
    public static final int USERNAME=2;
    public static final int PHONE=3;
    public static final int EMAIL=4;
    public static final int PWD=5;

    //前端Alter Type
    public static final String TYPE_SUCCESS="success";
    public static final String TYPE_ERROR="error";

    //返回消息
    public static final String ADD_SUCCESS="添加成功";
    public static final String ADD_FAILED="添加失败";
    public static final String GET_SUCCESS="成功获取";
    public static final String NOT_FOUND="未找到相关信息";
    public static final String UPD_SUCCESS="修改成功";
    public static final String UPD_FAILED="修改失败";
    public static final String DEL_SUCCESS="删除成功";
    public static final String DEL_FAILED="删除失败";
    public static final String NO_PERMS="无权访问";
    public static final String REG_SUCCESS="注册成功";
    public static final String REG_FAILED="注册失败";
    public static final String ADD_FAVORITE_SUCCESS="成功添加至收藏";
    public static final String ADD_FAVORITE_FAIL="添加收藏时发生错误";
    public static final String DEL_FAVORITE_SUCCESS="已移除出收藏";
    public static final String DEL_FAVORITE_FAIL="移除物品时发生错误";

    //检验消息
    public static final String USERNAME_EXISTED="用户名已被注册";
    public static final String USERPHONE_EXISTED="手机号码已被使用";
    public static final String USEREMAIL_EXISTED="电子邮箱已被使用";
    public static final String PWD_NO_MATCH="密码不匹配";

    //用户模块
    public static final String LOGIN_SUCCESS="登入成功";

    //用户管理模块
    public static final String USER_NOT_REGISTER="用户未注册";
    public static final String WRONG_PWD="密码错误";

    //角色管理模块
    public static final String GET_ROLE_SUCCESS="找到角色信息";
    public static final String ROLE_NOT_FOUND="未找到相关角色信息";

    //权限
    public static final String MENU="目录";
    public static final String CATALOG="菜单";
    public static final String BUTTON="按钮";

    //性别设置
    public static final String MALE="男";
    public static final String FEMALE="女";

    //状态
    public static final String NORMAL="正常";
    public static final String ENABLE="启用";
    public static final String DISABLE="禁用";

    //积分
    public static final String UPD_INTEGRAL_FAILED="更新积分失败，请与管理员联系";
}
