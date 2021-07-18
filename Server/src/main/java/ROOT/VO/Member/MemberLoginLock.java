package ROOT.VO.Member;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberLoginLock {

    @JsonProperty("MEM_LOGIN_LOCK_IDX")
    private int loginLockIndex;

    @JsonProperty("MEM_LOGIN_LOCK_MEM_ID")
    private String loginLockMemberId;

    @JsonProperty("MEM_LOGIN_LOCK_TIME")
    private String loginLockTime;

    public int getLoginLockIndex() {
        return loginLockIndex;
    }

    public void setLoginLockIndex(int loginLockIndex) {
        this.loginLockIndex = loginLockIndex;
    }

    public String getLoginLockMemberId() {
        return loginLockMemberId;
    }

    public void setLoginLockMemberId(String loginLockMemberId) {
        this.loginLockMemberId = loginLockMemberId;
    }

    public String getLoginLockTime() {
        return loginLockTime;
    }

    public void setLoginLockTime(String loginLockTime) {
        this.loginLockTime = loginLockTime;
    }

    @Override
    public String toString() {
        return "MemberLoginLock{" +
                "loginLockIndex=" + loginLockIndex +
                ", loginLockMemberId='" + loginLockMemberId + '\'' +
                ", loginLockTime='" + loginLockTime + '\'' +
                '}';
    }
}
