package com.blessingsoftware.chatterbeta.Models.Beans;

/*

    有关申请好友，应该没有问题
    written by CAT from/to Dec,2nd,2018

 */

public class InvitationInfo {

    private String Account;//获取对方ID -12.2
    private String Reason;//获取对方回复 -12.2
    private String Status;//状态（判定是否在等待回复） -12.2

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
