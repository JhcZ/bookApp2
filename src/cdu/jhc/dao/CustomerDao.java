package cdu.jhc.dao;

import cdu.jhc.model.Customer;

public interface CustomerDao extends SimpleDao<Customer>{
    //用于登录验证
    Customer find(String name, String password);
    //更新用户最近一次登录时间
    int updateAccessTime(Customer customer);
    //密码重置
    int updatePwd(int id, String newPwd);
    //更新帐号状态
    int updateStatus(int id, String status);
}
