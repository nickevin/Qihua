package com.qihua.util;

/**
 * Class description goes here.
 *	
 * @author  zhen.ni@ebaotech.com
 * @since   2013-10-11
 * @version 1.0
 * @see     
 */
public class SQLBuilder {

	private final String sql;
	private final static String VARIABLE_NAME = "sql";

	public SQLBuilder(String sql) {
		this.sql = sql;
	}

	public String bulider() {
		String sqlArr[] = sql.split("\r\n");
		int length = 0;
		for (int i = 0; i < sqlArr.length; i++)
			if (sqlArr[i].length() > length)
				length = sqlArr[i].length();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < sqlArr.length; i++)
			sb.append(new StringBuffer(String.valueOf(VARIABLE_NAME)).append(".append( \" ").append(sqlArr[i])
					.append(fillSpace(length - sqlArr[i].length())).append(" \");").append("\r\n").toString());

		return sb.toString();
	}

	private String fillSpace(int number) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < number; i++)
			sb.append(" ");

		return sb.toString();
	}
}