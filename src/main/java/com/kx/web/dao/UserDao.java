/**
 * 
 */
/**
 * @author Prathap
 *
 */
package com.kx.web.dao;

import java.sql.SQLException;

public interface UserDao
{
	String ChkLoginDetails(String strEmailAddress, String strPassword) throws SQLException;
}