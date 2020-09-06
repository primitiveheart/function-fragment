package com.zgb.function.fragment.entity;

import lombok.Data;

import java.util.List;

/**
 * 分页数据
 * @author xmly
 * @email guanbao.zhou@ximalaya.com
 * @Date 2020/9/6 10:28 上午
 * @Created By guanbao.zhou
 */
@Data
public class PageData {
  /**
   * 存放数据
   */
  private List list;
  /**
   * 记住总页数
   */
  private int totalPage;
  /**
   * 总记录数
   */
  private int totalRecord;
  /**
   * 页面大小
   */
  private int pageSize;
  /**
   * 代表用户想看的页码
   */
  private int pageNum;
  /**
   * 开始页
   */
  private int startPage;

  /**
   * 结束页
   */
  private int endPage;

  public PageData(int totalRecord, int pageSize, int pageNum) {
    this.pageSize = pageSize;
    this.totalRecord = totalRecord;
    this.pageNum = pageNum;
    if (this.totalRecord % this.pageSize == 0) {
      this.totalPage = this.totalRecord / this.pageSize;
    } else {
      this.totalPage = this.totalRecord / this.pageSize + 1;
    }
    if (this.pageNum - 2 <= 0) {
      this.startPage = 1;
    } else {
      this.startPage = this.pageNum - 2;
    }
    if (this.pageNum + 2 > this.totalPage) {
      this.endPage = this.totalPage;
    } else {
      this.endPage = this.pageNum + 2;
    }
  }
}

