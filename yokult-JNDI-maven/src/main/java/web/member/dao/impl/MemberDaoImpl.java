package web.member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.member.dao.MemberDao;
import web.member.vo.Member;

public class MemberDaoImpl implements MemberDao {

	private DataSource datasource;

	public MemberDaoImpl() throws NamingException {
		datasource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/Yokult");
	}
	
	final String SELECTALL = "Select MEMID, FIRSTNAME, LASTNAME, EMAIL, BIRTH, CELLPHONE, ADDR from MEMBER";
	@Override
	public Set<Member> selectAll() {
		try(Connection conn = datasource.getConnection();
				PreparedStatement ps = conn.prepareStatement(SELECTALL);) {
			try (ResultSet rs = ps.executeQuery()) {
				Set<Member> members = new HashSet<Member>();
				System.out.println("Show member list:");
				while(rs.next()) {
					Member m = new Member();
					m.setMemID(rs.getString("MEMID"));
					m.setMemFirstName(rs.getString("FIRSTNAME"));
					m.setMemLastName(rs.getString("LASTNAME"));
					m.setMemEmail(rs.getString("EMAIL"));
					m.setMemBirth(rs.getDate("BIRTH"));
					m.setMemCellPhone(rs.getString("CELLPHONE"));
					m.setMemAddress(rs.getString("ADDR"));
					members.add(m);
					System.out.println(m);
				}
				return members;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer insert(Member member) {
		try (Connection conn = datasource.getConnection();
				PreparedStatement ps = conn.prepareStatement(
						"insert into MEMBER (MEMID, EMAIL, PASSWORD, FIRSTNAME, LASTNAME, BIRTH, CELLPHONE, ADDR)"
								+ "values (?, ?, ?, ?, ?, ?, ?, ?);");) {
			ps.setString(1, member.getMemID());
			ps.setString(2, member.getMemEmail());
			ps.setString(3, member.getMemPassword());
			ps.setString(4, member.getMemFirstName());
			ps.setString(5, member.getMemLastName());
			ps.setDate(6, member.getMemBirth());
			ps.setString(7, member.getMemCellPhone());
			ps.setString(8, member.getMemAddress());
			int rowCount = ps.executeUpdate();
			System.out.println("insert " + rowCount + "member.");
			return rowCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	final String SELECT = "Select MEMID, FIRSTNAME, LASTNAME, EMAIL, BIRTH, CELLPHONE, ADDR from MEMBER where MEMID = ? and PASSWORD = ?";
	@Override
	public Member selectByMemberIdAndPassword(Member member) {
		try (Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement(SELECT);) {
			pstmt.setString(1, member.getMemID());
			pstmt.setString(2, member.getMemPassword());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					System.out.println("存取成功");
					Member resultMember = new Member();
					resultMember.setMemID(rs.getString("MEMID"));
					resultMember.setMemFirstName(rs.getString("FIRSTNAME"));
					resultMember.setMemLastName(rs.getString("LASTNAME"));
					resultMember.setMemEmail(rs.getString("EMAIL"));
					resultMember.setMemBirth(rs.getDate("BIRTH"));
					resultMember.setMemCellPhone(rs.getString("CELLPHONE"));
					resultMember.setMemAddress(rs.getString("ADDR"));
					return resultMember;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	final String UPDATE = "update MEMBER set EMAIL = ?, FIRSTNAME = ?, LASTNAME = ?, BIRTH = ?, CELLPHONE = ?, ADDR = ? where MEMID = ?;";
	@Override
	public Integer update(Member member) {
		try (Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn
						.prepareStatement(UPDATE);){
			pstmt.setString(1, member.getMemEmail());
			pstmt.setString(2, member.getMemFirstName());
			pstmt.setString(3, member.getMemLastName());
			pstmt.setDate(4, member.getMemBirth());
			pstmt.setString(5, member.getMemCellPhone());
			pstmt.setString(6, member.getMemAddress());
			pstmt.setString(7, member.getMemID());
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) updated!!");
			return rowCount;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	final String DELETE = "delete from MEMBER where MEMID = ?;";
	@Override
	public Integer delete(Member member) {
		try (Connection conn = datasource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(DELETE);) {
			pstmt.setString(1, member.getMemID());
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + " row(s) deleted!!");
			return rowCount;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
