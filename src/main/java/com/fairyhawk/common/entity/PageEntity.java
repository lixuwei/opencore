package com.fairyhawk.common.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageEntity implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3428610204086857703L;

	 /** 总数据条数 */
    private int totalResultSize=0;
    
    /** 总页数 */
    private int totalPageSize=0;
    
    /** 每页条数 */
    private int pageSize=10;
    
    /** 当前页码 */
    private int currentPage=1;
    
    
    /** 是否首页 */
    @SuppressWarnings("unused")
	private boolean first=false;
    /** 是否尾页 */
    @SuppressWarnings("unused")
    private boolean last=false;
	
    public boolean isFirst() {
		 return getCurrentPage()<=1;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean isLast() {
		 return getCurrentPage()>=getTotalPageSize();
	}
	public void setLast(boolean last) {
		this.last = last;
	}

	/**获得页码
     * List<Integer>
     * @return
     */
    public List<Integer> getPageNums(){
        List<Integer> returnList = new ArrayList<Integer>();
        int startNum = getCurrentPage()-getPageSize()<1?1:getCurrentPage()-getPageSize();
        int endNum  = getCurrentPage()+getPageSize()>getTotalPageSize()?getTotalPageSize():getCurrentPage()+getPageSize();
        for(int i=startNum;i<=endNum;i++){
            returnList.add(i);
        }
        return returnList;
    }
    
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取起始记录数
	 * 
	 * @return
	 */
	public int getStartRecord() {
		return (currentPage - 1) * pageSize;
	}

	/**
	 * 获得结束记录数
	 * 
	 * @return
	 */
	public int getEndRecord() {
		return getStartRecord() + pageSize - 1;
	}

	public int getTotalResultSize() {
		return totalResultSize;
	}

	public void setTotalResultSize(int totalResultSize) {
		this.totalResultSize = totalResultSize;
	}

	public int getTotalPageSize() {
		return totalPageSize;
	}

	public void setTotalPageSize(int totalPageSize) {
		this.totalPageSize = totalPageSize;
	}

	
}
