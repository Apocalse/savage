package com.kaltsit.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel(description = "分页")
@Data
public class PageUtils<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(notes = "总记录数")
    private long totalCount;
    @ApiModelProperty(notes = "每页记录数")
    private long pageSize;
    @ApiModelProperty(notes = "总页数")
    private long totalPage;
    @ApiModelProperty(notes = "当前页数")
    private long currPage;
    @ApiModelProperty(notes = "列表数据")
    private List<T> list;

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageUtils(List<T> list, int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }

    /**
     * 分页
     */
    public PageUtils(IPage<T> page) {
        this.list = page.getRecords();
        this.totalCount = page.getTotal();
        this.pageSize = page.getSize();
        this.currPage = page.getCurrent();
        this.totalPage = page.getPages();
    }

}
