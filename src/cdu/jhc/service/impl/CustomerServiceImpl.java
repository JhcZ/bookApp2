package cdu.jhc.service.impl;

import cdu.jhc.dao.CustomerDao;
import cdu.jhc.dao.impl.CustomerDaoImpl;
import cdu.jhc.model.Customer;
import cdu.jhc.model.User;
import cdu.jhc.model.UserStatus;
import cdu.jhc.service.CustomerService;
import cdu.jhc.util.Encrypt;
import java.util.Date;
import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    CustomerDao customerDao = new CustomerDaoImpl();
    @Override
    public Customer get(int id) {
        return (Customer) customerDao.findById(id);
    }

    @Override
    public Customer check(User user) {
        // 将顾客在登录表单中输入的密码加密后再在数据库中进行查询
        user.setPassword(Encrypt.toMd5(user.getPassword()));
        return customerDao.find(user.getName(), user.getPassword());
    }

    @Override
    public List<Customer> get(Customer condition, int page, int pageSize) {
        return customerDao.query(condition, (page - 1) * pageSize, pageSize);
    }

    @Override
    public int count(Customer condition) {
        return customerDao.count(condition);
    }

    @Override
    public boolean register(Customer customer) {
        // 顾客注册时将密码加密后再存入数据库中
        customer.setPassword(Encrypt.toMd5(customer.getPassword()));
        customer.setStatus(UserStatus.NORMAL);
        return customerDao.insert(customer) == 1;
    }

    @Override
    public boolean mod(Customer customer) {
        return customerDao.update(customer) == 1;
    }

    @Override
    public boolean modAccessTime(Customer customer) {
        // 修改顾客的最后一次访问时间
        customer.setLastAccessTime(new Date().getTime());
        return customerDao.updateAccessTime(customer) == 1;
    }

    @Override
    public boolean resetPwd(int id) {
        // 重置密码为123,加密密码
        String newPwd = Encrypt.toMd5("123");
        return customerDao.updatePwd(id, newPwd) == 1;
    }

    @Override
    public boolean modStatus(int id, UserStatus status) {
        return customerDao.updateStatus(id, status.getName()) == 1;
    }

    @Override
    public boolean checkStatus(Customer customer) {
        if (customer.getStatus() != null && customer.getStatus() == UserStatus.NORMAL) {
            return true;
        }
        return false;
    }

    @Override
    public boolean del(int id) {
        return customerDao.delete(id) == 1;
    }
}
