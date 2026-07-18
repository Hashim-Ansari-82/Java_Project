package com.library.dao;

import java.util.List;

import com.library.entity.Member;

public interface MemberDao {

	public void saveMember(Member member);
	public Member updateMember(Member member);
	public boolean deleteMember(Integer id);
	public Member getMemberById(Integer id);
	public List<Member> getAllMembers();
}
