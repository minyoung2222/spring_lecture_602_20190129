package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.mybatis.sqlSession.OracleMyBatisSqlSession;
import com.spring.dto.ReplyVO;
import com.spring.request.Criteria;

public class ReplyDAOImpl implements ReplyDAO{
	private static ReplyDAOImpl instance=new ReplyDAOImpl();
	private ReplyDAOImpl() {}
	public static ReplyDAOImpl getInstance() {
		return instance;
	}
	
	private SqlSessionFactory sessionFactory=
			OracleMyBatisSqlSession.getSqlSessionFactory();
	
	
	@Override
	public void insertReply(ReplyVO reply) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		session.update("Reply-Mapper.insertReply",reply);
		session.close();
		
	}
	@Override
	public void updateReply(ReplyVO reply) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		session.update("Reply-Mapper.updateReply",reply);
		session.close();
		
	}
	@Override
	public void deleteReply(int rno) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		session.update("Reply-Mapper.deleteReply",rno);
		session.close();
		
	}
	@Override
	public List<ReplyVO> selectReplyListPage(int bno, Criteria cri) throws SQLException {
		SqlSession session=sessionFactory.openSession();
		
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		
		List<ReplyVO> replyList=
		session.selectList("Reply-Mapper.selectReplyList",bno,rowBounds);
		
		session.close();
		return replyList;
	}
	@Override
	public int countReply(int bno) throws SQLException {
		SqlSession session=sessionFactory.openSession();
		int count=
				(Integer)session.selectOne("Reply-Mapper.countReply",bno);
		session.close();
		return count;
	}
	
		
}




