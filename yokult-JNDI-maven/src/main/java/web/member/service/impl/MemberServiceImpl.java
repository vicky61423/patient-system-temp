package web.member.service.impl;

import java.util.Objects;
import java.util.Set;

import javax.naming.NamingException;

import web.member.dao.MemberDao;
import web.member.dao.impl.MemberDaoImpl;
import web.member.service.MemberService;
import web.member.vo.Member;

public class MemberServiceImpl implements MemberService {
	private MemberDao dao;

	public MemberServiceImpl() throws NamingException {
		dao = new MemberDaoImpl();
	}
	
	@Override
	public Set<Member> getAll() {
		return dao.selectAll();
	}

	@Override
	public Integer remove(Member member) {
		if (!checkValue(member.getMemID())) {
			System.out.println("帳號錯誤");
			return -1;
		}
		return dao.delete(member);
	}

	@Override
	public Integer modify(Member member) {
		// 1. check if there is any null column in the not-null column
		if (!checkValue(member.getMemID())) {
			System.out.println("帳號錯誤");
			return null;
		}
		return dao.update(member);
	}

	@Override
	public Member login(Member member) {
		String account = member.getMemID();
		String password = member.getMemPassword();
//		System.out.println(account + " " + password);
		if (!checkValue(account) || !checkValue(password)) {
			System.out.println("帳號或密碼錯誤");
			return null;
		}
		member = dao.selectByMemberIdAndPassword(member);
		return member;
	}

	@Override
	public Integer register(Member member) {
		Integer status = dao.insert(member);
		return status;
	}

	private boolean checkValue(String value) {
		if (value == null || Objects.equals(value, "")) {
			System.out.println(value);
			return false;
		}
		return true;
	}
}
