package cdu.jhc.dao;

import cdu.jhc.model.AdminUser;

public interface AdminUserDao extends SimpleDao<AdminUser>{
    //用于登录验证
    AdminUser find(String name, String password);
    //更新用户最近一次登录时间
    int updateAccessTime(AdminUser adminUser);
    //密码重置
    int updatePwd(int id, String newPwd);
    //更新帐号状态
    int updateStatus(int id, String status);
}
