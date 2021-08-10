package com.peg.dao.interceptor;

/**
 * 分页参数类
 * 
 */
public class PageParameter {

    public static final int DEFAULT_PAGE_SIZE = 100;

    private int numPerPage;
    
    //currentPage
    private int pageNum;
    private int prePage;
    private int nextPage;
    private int totalPage;
    private int totalCount;

    public PageParameter() {
        this.pageNum = 1;
        this.numPerPage = DEFAULT_PAGE_SIZE;
        this.nextPage = pageNum + 1;
        this.prePage = pageNum - 1;
    }

    /**
     * 
     * @param currentPage
     * @param pageSize
     */
    public PageParameter(int currentPage, int pageSize) {
        this.pageNum = currentPage;
        this.numPerPage = pageSize;
        this.nextPage = currentPage + 1;
        this.prePage = currentPage - 1;
    }


    public int getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}

	public void setCurrentPage(int currentPage) {
        this.pageNum = currentPage;
        this.nextPage = currentPage + 1;
        this.prePage = currentPage - 1;
    }


    public int getNumPerPage()
	{
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage)
	{
		this.numPerPage = numPerPage;
	}

	public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

}
