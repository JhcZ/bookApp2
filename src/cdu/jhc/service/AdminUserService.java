package cdu.jhc.service;

import cdu.jhc.model.AdminUser;
import cdu.jhc.model.User;
import cdu.jhc.model.UserStatus;

import java.util.List;

public interface AdminUserService {
    //获取指定id的管理员用户
    AdminUser get(int id);
    //查找登录管理员用户
    AdminUser getLoginUser(User user);
    //分页模糊查询管理员用户列表
    List<AdminUser> get(AdminUser condition, int page, int pageSize);
    //获取查询管理员数量
    int count(AdminUser condition);
    //添加管理员用户
    boolean add(AdminUser adminUser);
    //修改管理员用户
    boolean mod(AdminUser adminUser);
    //更新管理员用户最近一次的访问时间
    boolean modAccessTime(AdminUser adminUser);
    //重置管理员用户登录密码
    boolean resetPwd(int id);
    //修改管理员用户状态
    boolean modStatus(int id, UserStatus status);
    //检查管理员用户状态
    boolean checkStatus(AdminUser adminUser);
    //删除管理员用户
    boolean del(int id);
}
