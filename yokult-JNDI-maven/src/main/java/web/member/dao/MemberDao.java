package web.member.dao;

import java.util.Set;

import web.member.vo.Member;

public interface MemberDao {
	Integer insert (Member member);
	Member selectByMemberIdAndPassword(Member member);
	Set<Member> selectAll();
	Integer update (Member member);
	Integer delete (Member member);
}
