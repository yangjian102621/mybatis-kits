package org.rockyang.mybatis.plus.plugins.page;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 分页数据
 *
 * @author chenzhaoju
 * @author yangjian
 * @param <T> model
 */
public class Page<T> implements Serializable {
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

    public Page() {}

    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

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

    /**
     * 获取下一页
     * @return
     */
    public int getNextPage() {
        return pageNo + 1;
    }

    /**
     * 获取上一页
     * @return
     */
    public int getPrevPage() {
        return pageNo - 1;
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
