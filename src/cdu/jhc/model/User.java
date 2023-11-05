package cdu.jhc.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private int id;
    private String name; //用户名
    private String password; //密码
    private long createTime; //账号创建时间
    private long lastAccessTime; //账号最近一次登录时间
    private UserStatus status; //帐号状态

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setCreateTime(Date createTime) {
        setCreateTime(createTime.getTime());
    }

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(long lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        setLastAccessTime(lastAccessTime.getTime());

    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void setStatus(String statusName) {
        if(statusName == null || "".equals(statusName)){
            setStatus(UserStatus.ALL);
            return;
        }
        switch (statusName){
            case"正常":
                setStatus(UserStatus.NORMAL);
                break;
            case"冻结":
                setStatus(UserStatus.FREEZE);
                break;
            case"异常":
                setStatus(UserStatus.UNKNOWN);
                break;
            default:
                setStatus(UserStatus.ALL);
        }
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", lastAccessTime=" + lastAccessTime +
                ", status=" + status.getName() +
                '}';
    }
}
