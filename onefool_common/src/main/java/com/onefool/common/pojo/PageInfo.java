package com.onefool.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页相关的封装对象
 *
 * @author Onefool
 * @version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> implements Serializable {
    /**
     * //当前页码
     */
    private Long page;
    /**
     * //每页显示行
     */
    private Long size;
    /**
     * //总记录数
     */
    private Long total;
    /**
     * //总页数
     */
    private Long totalPages;
    /**
     * //当前页记录
     */
    private List<T> list;


}
