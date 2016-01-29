package com.kx.web.dao;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.kx.web.bean.LoginBean;

import java.sql.CallableStatement;



public class UserDaoImpl implements UserDao{
	
	private DataSource dataSource;
	
	
	public DataSource getDataSource()
	{
			return this.dataSource;
	}

	public void setDataSource(DataSource dataSource)
	{
			this.dataSource = dataSource;
	}
	

	
	public String ChkLoginDetails(String strEmailAddress, String strPassword) throws SQLException {
		
		CallableStatement cs = dataSource.getConnection().prepareCall("{call UA_SP_CHK_Login(?,?,?)}");
		cs.setString(1, strEmailAddress);
		cs.setString(2, strPassword);
		cs.registerOutParameter(3, java.sql.Types.VARCHAR);
		cs.execute();
		
		//System.out.println("has been deleted");
		String dbReturnValErrorMessage = cs.getString(3);
		
		return dbReturnValErrorMessage;
		
	}
	
	public String ChkSignUpEmail(String strEmailAddress) throws SQLException {
		
		CallableStatement cs = dataSource.getConnection().prepareCall("{call UA_SP_CHK_EmailExistError(?,?)}");
		cs.setString(1, strEmailAddress);
		cs.registerOutParameter(3, java.sql.Types.VARCHAR);
		cs.execute();
		
		
		String dbReturnValErrorMessage = cs.getString(2);
		
		return dbReturnValErrorMessage;
		
	}

	public String InsertSignUpdetails(LoginBean loginBean) throws SQLException {
		
		CallableStatement cs = dataSource.getConnection().prepareCall("{call UA_SP_SAV_SignUp(?,?,?,?)}");
		cs.setString(1, loginBean.getName());
		cs.setString(2, loginBean.getEmail());
		cs.setString(3, loginBean.getPassword());
		cs.registerOutParameter(4, java.sql.Types.VARCHAR);
		cs.execute();
		
		
		String dbReturnValErrorMessage = cs.getString(4);
		
		return dbReturnValErrorMessage;
		
	}
	
	
}
