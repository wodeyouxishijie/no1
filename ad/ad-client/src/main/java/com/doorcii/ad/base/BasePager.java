package com.doorcii.ad.base;

import java.util.List;

/**
 * 翻页相关
 * @author Jacky
 */
public class BasePager<T> {
	
	/** 
	 * 默认第一页
	 */
	public static final Integer DEFAULT_CURRENT_PAGE = 1;
	
	/**
	 * 默认一页10条
	 */
	public static final Integer DEFAULT_PAGESIZE = 10;
	
	/**
	 * 当前页是第几页
	 */
	private Integer currentPage = DEFAULT_CURRENT_PAGE;
	
	/**
	 * 一页条数
	 */
	private Integer pageSize = DEFAULT_PAGESIZE;

	/**
	 * 总记录数
	 */
	private Long totalRecord;
	
	/**
	 * 结果列表
	 */
	private List<T> resultList;
	
	public void buildPagingParam(int pageNo,int pageSize) {
		this.currentPage = pageNo;
		this.pageSize = pageSize;
	}
	
	public Long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Long totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
