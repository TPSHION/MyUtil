package com.wsyy.util;

public class PageHelper{
	
	private static final String PRESQL = " SELECT B.* FROM ("+
										 "SELECT A.*, ROWNUM pageNum FROM( ";
	private static final String SUFSQL = ") A WHERE ROWNUM <= ?"+
										 ") B WHERE pageNum > ?";
	private static final String SUFSQL_DEFAULT = ") A WHERE ROWNUM <= ROWNUM"+
			 							         ") B WHERE pageNum > ?";

	public static PageHelp getPageHelp(Integer pageSize, Integer pageNum){
		PageHelp pageHelp = new PageHelp();
		if("".equals(pageNum) || pageNum == null || pageNum == 0 || "0".equals(pageNum)){
			pageNum = 1;
		}
		if("".equals(pageSize) || pageSize == null ){
			pageSize = 0;
		}
		pageHelp.setPageNum((pageNum - 1) * pageSize);
		pageHelp.setPageSize(pageSize * pageNum);
		return pageHelp;
	}
	
	public static String pageList(String sql,PageHelp pageHelp) throws Exception{
		String SUFSQL = ") A WHERE ROWNUM <= "+pageHelp.getPageSize()+") B WHERE pageNum > "+pageHelp.getPageNum();
		StringBuffer strSql = new StringBuffer();
		strSql.append(PRESQL);
		strSql.append(sql);
		strSql.append(SUFSQL);
		return strSql.toString();
	}
	
	public static String pageList(String sql, Integer pageSize, Integer pageNum) throws Exception{
		PageHelp pageHelp = getPageHelp(pageSize, pageNum);
		String SUFSQL = ") A WHERE ROWNUM <= "+pageHelp.getPageSize()+") B WHERE pageNum > "+pageHelp.getPageNum();
		StringBuffer strSql = new StringBuffer();
		strSql.append(PRESQL);
		strSql.append(sql);
		strSql.append(SUFSQL);
		return strSql.toString();
	}
	
	
}
