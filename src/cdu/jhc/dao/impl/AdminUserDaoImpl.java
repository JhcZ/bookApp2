package cdu.jhc.dao.impl;

import cdu.jhc.dao.BaseDao;
import cdu.jhc.dao.AdminUserDao;
import cdu.jhc.model.UserStatus;
import cdu.jhc.model.AdminUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminUserDaoImpl extends BaseDao implements AdminUserDao{
    @Override
    public AdminUser find(String name, String password) {
        AdminUser adminUser = null;
        String sql = "SELECT * FROM admin_table WHERE name=? AND password=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                adminUser = new AdminUser();
                adminUser.setId(rs.getInt("id"));
                adminUser.setName(rs.getString("name"));
                adminUser.setPassword(rs.getString("password"));
                adminUser.setCreateTime(rs.getLong("createTime"));
                adminUser.setLastAccessTime(rs.getLong("lastAccessTime"));
                adminUser.setStatus(rs.getString("status"));
                System.out.println("DAO验证管理员: " + adminUser);
            }
        }catch (SQLException e) {
            System.out.println("DAO验证管理员出错：" + sql + "," + e.getMessage());
        }
        return adminUser;
    }

    @Override
    public int updateAccessTime(AdminUser adminUser) {
        int rows = 0;
        String sql = "UPDATE admin_table SET lastAccessTime=? WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, adminUser.getLastAccessTime());
            pstmt.setInt(2, adminUser.getId());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO修改管理员出错：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int updatePwd(int id, String newPwd) {
        int rows = 0;
        String sql = "UPDATE admin_table SET password=? WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPwd);
            pstmt.setInt(2, id);
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO重置管理员密码出错：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int updateStatus(int id, String status) {
        int rows = 0;
        String sql = "UPDATE admin_table SET status=? WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, status);
            pstmt.setInt(2, id);
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO修改管理员状态出错：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public AdminUser findById(int id) {
        AdminUser condition = new AdminUser();
        condition.setId(id);
        List<AdminUser> adminUserList = query(condition);
        if (adminUserList != null && adminUserList.size() == 1) {
            return adminUserList.get(0);
        }
        return null;
    }

    @Override
    public List<AdminUser> query(AdminUser condition) {
        List<AdminUser> adminUserList = new ArrayList<>();
        String sql = "SELECT * FROM admin_table";
        if (condition != null) {
            sql += " WHERE 1=1";
            if (condition.getId() != 0) {
                sql += " AND id='" + condition.getId() + "'";
            }
            if (condition.getName() != null && !condition.getName().isEmpty()) {
                sql += " AND name LIKE '%" + condition.getName() + "%'";
            }
            if (condition.getStatus() != null && condition.getStatus() != UserStatus.ALL) {
                sql += " AND status='" + condition.getStatus().getName() + "'";
            }
        }
        sql += " ORDER BY id DESC";
        System.out.println("DAO查询find(condition) : " + sql);
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                AdminUser adminUser = new AdminUser();
                adminUser.setId(rs.getInt("id"));
                adminUser.setName(rs.getString("name"));
                adminUser.setPassword(rs.getString("password"));
                adminUser.setCreateTime(rs.getLong("createTime"));
                adminUser.setLastAccessTime(rs.getLong("lastAccessTime"));
                adminUser.setStatus(rs.getString("status"));
                adminUserList.add(adminUser);
            }
        } catch (SQLException e) {
            System.out.println("DAO查询管理员出错：" + sql + "," + e.getMessage());
        }
        return adminUserList;
    }

    @Override
    public List<AdminUser> query(AdminUser condition, int start, int num) {
        List<AdminUser> adminUserList = new ArrayList<>();
        String sql = "SELECT * FROM admin_table";
        if (condition != null) {
            sql += " WHERE 1=1";
            if (condition.getId() != 0) {
                sql += " AND id='" + condition.getId() + "'";
            }
            if (condition.getName() != null && !condition.getName().isEmpty()) {
                sql += " AND name LIKE '%" + condition.getName() + "%'";
            }
            if (condition.getStatus() != null && condition.getStatus() != UserStatus.ALL) {
                sql += " AND status='" + condition.getStatus().getName() + "'";
            }
        }
        sql += " ORDER BY id DESC LIMIT ?,?";
        System.out.println("DAO查询find(condition, start, num) : " + sql);
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, start);
            pstmt.setInt(2, num);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                AdminUser adminUser = new AdminUser();
                adminUser.setId(rs.getInt("id"));
                adminUser.setName(rs.getString("name"));
                adminUser.setPassword(rs.getString("password"));
                adminUser.setCreateTime(rs.getLong("createTime"));
                adminUser.setLastAccessTime(rs.getLong("lastAccessTime"));
                adminUser.setStatus(rs.getString("status"));
                adminUserList.add(adminUser);
            }
        } catch (SQLException e) {
            System.out.println("DAO查询管理员出错：" + sql + "," + e.getMessage());
        }
        return adminUserList;
    }

    @Override
    public int insert(AdminUser adminUser) {
        int rows = 0;
        String sql = "INSERT INTO admin_table(name,password,createTime,status) VALUES(?,?,?,?) ";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, adminUser.getName());
            pstmt.setString(2, adminUser.getPassword());
            pstmt.setLong(3, new Date().getTime());
            pstmt.setString(4, adminUser.getStatus().getName());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO添加管理员出错：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int update(AdminUser adminUser) {
        int rows = 0;
        String sql = "UPDATE admin_table SET name=?,status=? WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, adminUser.getName());
            pstmt.setString(2, adminUser.getStatus().getName());
            pstmt.setInt(3, adminUser.getId());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO修改管理员出错：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int delete(int id) {
        int rows = 0;
        String sql = "DELETE FROM admin_table WHERE id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("DAO删除管理员出错：" + sql + "," + e.getMessage());
        }
        return rows;
    }

    @Override
    public int count() {
        return count(null);
    }

    @Override
    public int count(AdminUser condition) {
        int num = 0;
        String sql = "SELECT count(*) FROM admin_table WHERE 1=1";
        if (condition != null) {
            if (condition.getId() != 0) {
                sql += " AND id='" + condition.getId() + "'";
            }
            if (condition.getName() != null && !condition.getName().isEmpty()) {
                sql += " AND name LIKE '%" + condition.getName() + "%'";
            }
            if (condition.getStatus() != null && condition.getStatus() != UserStatus.ALL) {
                sql += " AND status='" + condition.getStatus().getName() + "'";
            }
        }
        System.out.println("DAO查询count(con): " + sql);
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                num = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("DAO查询管理员数量出错：" + sql + "," + e.getMessage());
        }
        return num;
    }
}
