package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.spring.dto.PdsVO;
import com.spring.request.Criteria;

public class PdsDAOImpl implements PdsDAO {
	private static PdsDAOImpl instance=new PdsDAOImpl();
	private PdsDAOImpl() {}
	public static PdsDAOImpl getInstance() {
		return instance;		
	}
	
	private SqlSessionFactory sessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	@Override
	public List<PdsVO> selectPdsCriteria(Criteria cri) throws SQLException {
		SqlSession session = sessionFactory.openSession();
		
		int offset=cri.getPageStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		
		List<PdsVO> pdsList=
		   session.selectList("Pds-Mapper.selectSearchPdsList",cri,rowBounds);	
			
		session.close();
		return pdsList;
	}

	@Override
	public int selectPdsCriteriaTotalCount(Criteria cri) throws SQLException {
		SqlSession session = sessionFactory.openSession();
		
		List<PdsVO> pdsList= 
				session.selectList("Pds-Mapper.selectSearchPdsList",cri);
		int count=pdsList.size();
		
		session.close();
		return count;
	}

	@Override
	public PdsVO selectPdsByPno(int pno) throws SQLException {
		SqlSession session = sessionFactory.openSession();
		
		PdsVO pds=session.selectOne("Pds-Mapper.selectPdsByPno",pno);
		
		session.close();
		return pds;
	}

	@Override
	public void insertPds(PdsVO pds) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		
		session.update("Pds-Mapper.insertPds",pds);
		
		session.close();

	}

	@Override
	public void updatePds(PdsVO pds) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		
		session.update("Pds-Mapper.updatePds",pds);
		
		session.close();

	}

	@Override
	public void deletePds(int pno) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		
		session.update("Pds-Mapper.deletePds",pno);
		
		session.close();

	}

	@Override
	public void increaseViewCnt(int pno) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		
		session.update("Pds-Mapper.increaseViewCnt",pno);
		
		session.close();

	}

	@Override
	public int getSeqNextValue() throws SQLException {
		SqlSession session = sessionFactory.openSession();
		
		int pno=session.selectOne("Pds-Mapper.selectPdsSeqNext");
		
		session.close();
		return pno;
	}

}
