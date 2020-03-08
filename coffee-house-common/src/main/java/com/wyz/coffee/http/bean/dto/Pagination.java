package com.wyz.coffee.http.bean.dto;

import java.util.List;

/**
 * 分页基础类
 */
public class Pagination<T> {

    // 页数：第几页
    private int pageNum;
    // 每页多少条
    private int pageSize;
    // 总页数
    private int pages;
    // 总条数
    private long total;
    // 分页内容集合
    private List<T> result;

    public Pagination() { }

    public Pagination(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Pagination(int pageNum, int pageSize, long total) {
        this(pageNum, pageSize);
        this.total = total;
        int divisor = (int) (total / this.getPageSize());
        int remainder = (int) (total % this.getPageSize());
        this.setPages(remainder == 0 ? divisor == 0 ? 1 : divisor : divisor + 1);
    }

    public Pagination(int pageNum, int pageSize, long total, List<T> result) {
        this(pageNum, pageSize, total);
        this.result = result;
    }


    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    /**
     * @author chenzj 20181128
     *  链式，辅助
     * @param result
     * @return
     */
    public Pagination<T> build(List<T> result) {
        this.setResult(result);
        return this;
    }

    /**
     * @author chenzj 20181128
     *
     * 校验是否超过当前所要页数
     * @return
     */
    public boolean outnumber() {
        return this.pages - this.pageNum < 0;
    }
}
