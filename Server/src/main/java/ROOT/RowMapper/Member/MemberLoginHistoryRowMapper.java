package ROOT.RowMapper.Member;

import ROOT.VO.Member.MemberLoginHistory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberLoginHistoryRowMapper implements RowMapper<MemberLoginHistory> {
    @Override
    public MemberLoginHistory mapRow(ResultSet resultSet, int i) throws SQLException {
        MemberLoginHistory loginHistory = new MemberLoginHistory();
        loginHistory.setLoginHistoryIndex(resultSet.getInt("MEM_LOGIN_HISTORY_IDX"));
        loginHistory.setLoginMemberId(resultSet.getString("MEM_LOGIN_MEM_ID"));
        loginHistory.setLoginDate(resultSet.getString("MEM_LOGIN_DT"));
        loginHistory.setLoginIP(resultSet.getString("MEM_LOGIN_IP"));
        loginHistory.setLoginYN(resultSet.getString("MEM_LOGIN_YN"));
        return loginHistory;
    }
}
