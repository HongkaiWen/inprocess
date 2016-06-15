package audit.vo;

import java.util.Date;

public class AuditLog {

    private Date dCreate;
    private String user;
    private String action;
    private String actionMsg;
    private String bizRefNo;


    public AuditLog(String user, Date dCreate, String bizRefNo, String action, String actionMsg) {
        this.dCreate = dCreate;
        this.user = user;
        this.action = action;
        this.actionMsg = actionMsg;
        this.bizRefNo = bizRefNo;
    }

    public AuditLog() {
    }

    @Override
    public String toString() {
        return "AuditLog{" +
                "dCreate=" + dCreate +
                ", user='" + user + '\'' +
                ", action='" + action + '\'' +
                ", actionMsg='" + actionMsg + '\'' +
                ", bizRefNo='" + bizRefNo + '\'' +
                '}';
    }

    public Date getdCreate() {
        return dCreate;
    }

    public void setdCreate(Date dCreate) {
        this.dCreate = dCreate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getBizRefNo() {
        return bizRefNo;
    }

    public void setBizRefNo(String bizRefNo) {
        this.bizRefNo = bizRefNo;
    }

    public String getActionMsg() {
        return actionMsg;
    }

    public void setActionMsg(String actionMsg) {
        this.actionMsg = actionMsg;
    }

}
