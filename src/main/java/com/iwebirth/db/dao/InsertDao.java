package com.iwebirth.db.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iwebirth.db.model.ErrorModel;
import com.iwebirth.db.model.ErrorReferrence;
import com.iwebirth.db.model.RunInfoModel;
import com.iwebirth.interact.model.TerminalError;
import com.iwebirth.interact.model.TerminalRunInfo;
import com.iwebirth.util.CRUDEvent;

/**
 * 2015-1-19
 * need spring-hibernate.xml
 * **/
@Component
@SuppressWarnings("finally")
public class InsertDao {
	@Autowired
	SessionFactory sf;
	
	public int insertRunInfo(TerminalRunInfo tRunInfo){
		int res = 0;
		Session session = null;
		try{
			RunInfoModel runinfo = new RunInfoModel(tRunInfo.gettId(), tRunInfo.getSpeed(), tRunInfo.getRotateSpped(), tRunInfo.getWaterTemperature());
			session= sf.getCurrentSession();
			Integer id = (Integer)session.save(runinfo);	
			if(id != null && id > 0)
				res = CRUDEvent.SAVE_SUCCESS.getValue();
			else
				res = CRUDEvent.SAVE_FAIL.getValue();
		}catch(Exception e){
			res = CRUDEvent.SAVE_EXCEPTION.getValue();
			e.printStackTrace();
		}finally{
			return res;
		}

	}
	public int insertErrorModel(TerminalError tError){
		int res = 0;
		Session session = null;
		try{
			session = sf.getCurrentSession();
			Criteria c = session.createCriteria(ErrorReferrence.class).add(Restrictions.eq("errorCode", tError.getErrorCode()));
			ErrorReferrence ref = (ErrorReferrence)c.uniqueResult();
			if(ref == null){
				System.out.println("未找到该错误码对应的错误");
				res = CRUDEvent.SAVE_FAIL.getValue();
			}else{
				ErrorModel error = new ErrorModel(tError.gettId(), ref);
				Integer id = (Integer)session.save(error);
				if(id != null && id > 0)
					res = CRUDEvent.SAVE_SUCCESS.getValue();
				else
					res = CRUDEvent.SAVE_FAIL.getValue();
			}
		}catch(Exception e){
			res = CRUDEvent.SAVE_EXCEPTION.getValue();
			e.printStackTrace();
		}finally{
			return res;
		}
	}

	/**
	 * 初始化OBD ERROR
	 * **/
	@SuppressWarnings("unchecked")
	public void initialErrorReferrence(){
		Session session = null;
		BufferedReader br = null;
		long start = System.currentTimeMillis();
		try{
			session= sf.getCurrentSession();
			Criteria c = session.createCriteria(ErrorReferrence.class);
			c.setReadOnly(true);
			List<ErrorReferrence> list = c.list();
			if(list.size() > 0){
				String del = "delete from error_referrence";
				session.createSQLQuery(del).executeUpdate();
			}
			br = new BufferedReader(new FileReader(new File("src/main/resources/obd-error.txt")));
			String line = "";
			while((line=br.readLine()) != null){
				System.out.println(line);
				String[] fragments = line.split("=");
				ErrorReferrence ref = new ErrorReferrence(fragments[0], fragments[1]);
				session.save(ref);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("initialErrorReferrence耗时:"+(System.currentTimeMillis()-start)/1000f);
		}
	}
	
}
