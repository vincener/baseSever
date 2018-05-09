package com.hq.CloudPlatform.CA.restful.view;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class Page implements Serializable {

    /**
     * 系统允许的最大单页记录数
     */
    public static final int MAX_PAGE_SIZE = 500;

    /**
     * 每页记录数
     */
    private int pageSize = 10;

    /**
     * 当前页数
     */
    private int pageNumber = 1;

    /**
     * 总记录数
     */
    private int total = 0;

    /**
     * 查询条件
     */
    private Map<String, Object> conditions;

	private List<Map<String, Object>> orderFields;

	private String orderBy;

    @SuppressWarnings("rawtypes")
    private List rows;

    public Page() {
    }

    public Page(int pageSize, int total) {
        this.pageSize = pageSize;
        this.total = total;
    }

    /**
     * 开始行
     * @return
     */
    public int getStartRowNum(){
        return (pageSize * (pageNumber - 1));
    }

    /**
     * 结束行
     * @return
     */
    public int getEndRowNum(){
        return (pageSize * pageNumber);
    }

    /**
     * 获取总页数
     * @return
     */
    public int getTotalPageNum(){
        return total % pageSize == 0 ? (total / pageSize) : (total / pageSize) + 1;
    }

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Map<String, Object> getConditions() {
		return conditions;
	}

	public void setConditions(Map<String, Object> conditions) {
		this.conditions = conditions;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public static int getMaxPageSize() {
		return MAX_PAGE_SIZE;
	}

}
