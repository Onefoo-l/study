package com.onefool.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分页查询的DTO类
 *
 * @author Onefool
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageRequestDto<T> implements Serializable {
    /**
     * //当前页码
     */
    private Long page = 1L;
    /**
     * //每页显示的行
     */
    private Long size = 10L;
    /**
     * //请求体实体对象
     */
    private T body;

}
