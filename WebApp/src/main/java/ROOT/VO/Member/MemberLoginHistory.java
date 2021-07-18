package ROOT.VO.Member;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberLoginHistory {

    @JsonProperty("MEM_LOGIN_HISTORY_IDX")
    private int loginHistoryIndex;

    @JsonProperty("MEM_LOGIN_MEM_ID")
    private String loginMemberId;

    @JsonProperty("MEM_LOGIN_DT")
    private String loginDate;

    @JsonProperty("MEM_LOGIN_IP")
    private String loginIP;

    @JsonProperty("MEM_LOGIN_YN")
    private String loginYN;

    public int getLoginHistoryIndex() {
        return loginHistoryIndex;
    }

    public void setLoginHistoryIndex(int loginHistoryIndex) {
        this.loginHistoryIndex = loginHistoryIndex;
    }

    public String getLoginMemberId() {
        return loginMemberId;
    }

    public void setLoginMemberId(String loginMemberId) {
        this.loginMemberId = loginMemberId;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public String getLoginYN() {
        return loginYN;
    }

    public void setLoginYN(String loginYN) {
        this.loginYN = loginYN;
    }

    @Override
    public String toString() {
        return "MemberLoginHistory{" +
                "loginHistoryIndex=" + loginHistoryIndex +
                ", loginMemberId='" + loginMemberId + '\'' +
                ", loginDate='" + loginDate + '\'' +
                ", loginIP='" + loginIP + '\'' +
                ", loginYN='" + loginYN + '\'' +
                '}';
    }
}
