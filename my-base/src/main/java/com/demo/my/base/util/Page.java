package com.demo.my.base.util;

import java.util.List;  

/** 
 * 封装分页信息 
 * @author Administrator 
 * 
 */  
public class Page<E> {  

	//�?大查询数
	private static int MAX_PAGE_SIZE = Integer.MAX_VALUE;
	//默认每页10�?
	private static int DEFAULT_PAGE_SIZE = 10;
	//默认第一�?
	private static int DEFAULT_PAGE_NO = 1;
    //结果�?  
    private List<E> list;  
    //查询记录�?  
    private int totalRecords;
    //总页数
    private int totalPages;
    //当前�?
    private int currentPage;
    //每页多少条数�?  
    private int pageSize=DEFAULT_PAGE_SIZE;  
    //第几�?  
    private int pageNo=DEFAULT_PAGE_NO;
    
    private int startRow;
    private String orderBy;

    public Page() {
    	
    }
    
    public Page(int pageNo, Integer pageSize) {
    	if(pageSize==null || pageSize < 0){
    		this.pageSize = MAX_PAGE_SIZE;
    	} else {
    		this.pageSize = pageSize;
    	}
		if(pageNo<=0){
			this.pageNo =1;
			this.startRow = 0;
		} else {
			this.pageNo = pageNo;
			this.startRow = (pageNo-1)*pageSize;
		}
		this.currentPage = this.pageNo;
	}

	/** 
     * 总页�? 
     * @return 
     */  
    public int getPageCount() {  
        return (totalRecords + pageSize - 1) / pageSize;  
    }  
      
    /** 
     * 取得首页 
     * @return 
     */  
    public int getTopPageNo() {  
        return 1;  
    }  
      
    /** 
     * 上一�? 
     * @return 
     */  
    public int getPreviousPageNo() {  
        if (pageNo <= 1) {  
            return 1;  
        }  
        return pageNo - 1;  
    }  
      
    /** 
     * 下一�? 
     * @return 
     */  
    public int getNextPageNo() {  
        if (pageNo >= getBottomPageNo()) {  
            return getBottomPageNo();  
        }  
        return pageNo + 1;    
    }  
      
    /** 
     * 取得尾页 
     * @return 
     */  
    public int getBottomPageNo() {  
        return getPageCount();  
    }  
      
    public List<E> getList() {  
        return list;  
    }  
  
    public void setList(List<E> list) {  
        this.list = list;  
    }  
  
    public int getTotalRecords() {  
        return totalRecords;  
    }  
  
    public void setTotalRecords(int totalRecords) {  
        this.totalRecords = totalRecords;  
    }  
  
    public int getPageSize() {  
        return pageSize;  
    }  
  
    public void setPageSize(int pageSize) {  
        this.pageSize = pageSize;  
    }  
  
    public int getPageNo() {  
        return pageNo;  
    }  
  
    public void setPageNo(int pageNo) {  
        this.pageNo = pageNo;  
    }

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}  
}  
