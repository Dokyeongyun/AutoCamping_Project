package ROOT.RowMapper.Member;

import ROOT.VO.Member.MemberLoginLock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberLoginLockRowMapper implements RowMapper<MemberLoginLock> {
    @Override
    public MemberLoginLock mapRow(ResultSet resultSet, int i) throws SQLException {
        MemberLoginLock loginLock = new MemberLoginLock();
        loginLock.setLoginLockIndex(resultSet.getInt("MEM_LOGIN_LOCK_IDX"));
        loginLock.setLoginLockMemberId(resultSet.getString("MEM_LOGIN_LOCK_MEM_ID"));
        loginLock.setLoginLockTime(resultSet.getString("MEM_LOGIN_LOCK_TIME"));
        return loginLock;
    }
}
