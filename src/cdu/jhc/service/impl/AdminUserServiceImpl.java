package cdu.jhc.service.impl;

import cdu.jhc.dao.AdminUserDao;
import cdu.jhc.dao.impl.AdminUserDaoImpl;
import cdu.jhc.model.AdminUser;
import cdu.jhc.model.User;
import cdu.jhc.model.UserStatus;
import cdu.jhc.service.AdminUserService;
import cdu.jhc.util.Encrypt;
import java.util.Date;
import java.util.List;

public class AdminUserServiceImpl implements AdminUserService{
    AdminUserDao adminUserDao = new AdminUserDaoImpl();
    @Override
    public AdminUser get(int id) {
        return adminUserDao.findById(id);
    }
    @Override
    public AdminUser getLoginUser(User user) {
        if (user == null) {
            return null;
        }
        //密码加密
        user.setPassword(Encrypt.toMd5(user.getPassword()));
        AdminUser admin = adminUserDao.find(user.getName(), user.getPassword());
        if (admin != null) {
            //更新用户最近一次访问时间
            admin.setLastAccessTime(new Date());
            adminUserDao.updateAccessTime(admin);
        }
        return admin;
    }
    @Override
    public List<AdminUser> get(AdminUser condition, int page, int pageSize) {
        return adminUserDao.query(condition, (page - 1) * pageSize, pageSize);
    }

    @Override
    public int count(AdminUser condition) {
        return adminUserDao.count(condition);
    }
    @Override
    public boolean add(AdminUser adminUser) {
        adminUser.setPassword(Encrypt.toMd5(adminUser.getPassword()));
        adminUser.setStatus(UserStatus.NORMAL);
        return adminUserDao.insert(adminUser) == 1;
    }
    @Override
    public boolean mod(AdminUser adminUser) {
        return adminUserDao.update(adminUser) == 1;
    }
    @Override
    public boolean modAccessTime(AdminUser adminUser) {
        adminUser.setLastAccessTime(new Date().getTime());
        return adminUserDao.updateAccessTime(adminUser) == 1;
    }
    @Override
    public boolean resetPwd(int id) {
        String newPwd = Encrypt.toMd5("123");
        return adminUserDao.updatePwd(id, newPwd) == 1;
    }
    @Override
    public boolean modStatus(int id, UserStatus status) {
        return adminUserDao.updateStatus(id, status.getName()) == 1;
    }

    @Override
    public boolean checkStatus(AdminUser adminUser) {
        return adminUser.getStatus() == UserStatus.NORMAL;
    }
    @Override
    public boolean del(int id) {
        return adminUserDao.delete(id) == 1;
    }
}

