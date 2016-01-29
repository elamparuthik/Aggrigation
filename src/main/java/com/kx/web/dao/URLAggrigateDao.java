/**
 * 
 */
/**
 * @author Prathap
 *
 */
package com.kx.web.dao;

import java.sql.SQLException;
import java.util.Map;

import com.kx.web.bean.URLAggrigateBean;

public interface URLAggrigateDao
{
	Map<String, Object> GetURLAggrigateDetails(String EmailAddress) throws SQLException;
	String InsertURLdetails(URLAggrigateBean urlAggrigateBean, String strEmailAddress) throws SQLException;
}