package ROOT.RowMapper.Member;

import ROOT.VO.Article.Comment;
import ROOT.VO.Member.Member;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRowMapper implements RowMapper<Member> {

    @Override
    public Member mapRow(ResultSet resultSet, int i) throws SQLException {
        Member member = new Member();
        member.setMemberId(resultSet.getString("memberId"));
        member.setNickName(resultSet.getString("nickName"));
        member.setPassword(resultSet.getString("password"));
        return member;
    }
}
