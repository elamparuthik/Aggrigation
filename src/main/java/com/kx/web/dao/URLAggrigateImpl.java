package com.kx.web.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;


import com.kx.web.bean.LoginBean;
import com.kx.web.bean.URLAggrigateBean;

import java.sql.CallableStatement;
import java.sql.ResultSet;



public class URLAggrigateImpl implements URLAggrigateDao{
	
	private DataSource dataSource;
	
	
	public DataSource getDataSource()
	{
			return this.dataSource;
	}

	public void setDataSource(DataSource dataSource)
	{
			this.dataSource = dataSource;
	}
	

	public String InsertURLdetails(URLAggrigateBean urlAggrigateBean, String strEmailAddress) throws SQLException {
		
		CallableStatement cs = dataSource.getConnection().prepareCall("{call UA_SP_INS_AggrigateDetails(?,?,?,?,?)}");
		cs.setString(1, urlAggrigateBean.getTitle());
		cs.setString(2, urlAggrigateBean.getDescription());
		cs.setString(3, urlAggrigateBean.getUrl());
		cs.setString(4, strEmailAddress);
		cs.registerOutParameter(5, java.sql.Types.VARCHAR);
		cs.execute();
		
		String dbReturnValErrorMessage = cs.getString(5);
		
		return dbReturnValErrorMessage;
	}
	
	@Override
	public Map<String, Object> GetURLAggrigateDetails(String strEmailAddress) throws SQLException{
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		CallableStatement cs = dataSource.getConnection().prepareCall("{call Kx_SP_GET_URLAggrigate(?,?)}");
		cs.setString(1, strEmailAddress);
		cs.registerOutParameter(2, java.sql.Types.VARCHAR);
		Boolean isResultSet = cs.execute();
		
		String dbReturnValErrorMessage = cs.getString(2);
		
		
		ResultSet rsEvent = cs.getResultSet();
		List<URLAggrigateBean> AggriList = new ArrayList<URLAggrigateBean>();
		

		if(!isResultSet){
			return model;	
		}
		
		
		while(rsEvent.next()){
			URLAggrigateBean bean = new URLAggrigateBean();
			
			bean.setTitle(rsEvent.getString("Name"));
			bean.setDescription(rsEvent.getString("Description"));
			bean.setUrl(rsEvent.getString("URL"));
			AggriList.add(bean);
		}
	
		rsEvent.close();
		
		model.put("AggriDBList", AggriList);
		
		
		return model;
	}
	
	
}
