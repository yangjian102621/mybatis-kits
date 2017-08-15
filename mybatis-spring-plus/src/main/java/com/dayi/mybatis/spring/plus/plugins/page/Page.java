package com.dayi.mybatis.spring.plus.plugins.page;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 分页数据
 *
 * @author chenzhaoju
 * @param <T> model
 */
public class Page<T> extends AbstractList<T> implements Serializable {
    /** 页码，默认是第一页 */
    private int pageNo = 1;
    /** 每页显示的记录数，默认是10 */
    private int pageSize = 10;
    /** 总记录数 */
    private long totalRecord;
    /** 总页数 */
    private int totalPage;
    /** 对应的当前页记录 */
    private List<T> results;

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getStartRow() {
        int startRow = this.pageNo > 0 ? (pageNo - 1) * pageSize : 0;
        return startRow;
    }

    public int getEndRow() {
        int endRow = this.pageNo * pageSize;
        return endRow;
    }

    public List<T> getResults() {
        return results;
    }

    public Page setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Page setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Page setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
        if (this.totalRecord != 0) {
            this.totalPage = (int) (this.totalRecord / this.pageSize);
            if (this.totalRecord % this.pageSize != 0) {
                this.totalPage++;
            }
        }
        return this;
    }

    public Page setResults(List<T> results) {
        this.results = results;
        return this;
    }

    @Override
    public int size() {
        return null != getResults() ? getResults().size() : 0;
    }

    @Override
    public T get(int index) {
        return null != getResults() ? getResults().get(index) : null;
    }

    @Override
    public boolean add(T t) {
        if(null == this.results){
            this.results = new ArrayList<T>();
        }
        return this.results.add(t);
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", totalRecord=" + totalRecord +
                ", totalPage=" + totalPage +
                '}';
    }
}
