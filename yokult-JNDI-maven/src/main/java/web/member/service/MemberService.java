package web.member.service;

import java.util.Set;

import web.member.vo.Member;

public interface MemberService {
	Set<Member> getAll();
	
	Integer register(Member member);

	Member login(Member member);

	Integer modify(Member member);

	Integer remove(Member member);
}
