package com.onefool.common.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.onefool.common.domain.entry.UmsMenu;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author linjiawei
 * @Date 2024/1/9 7:18
 * @Description 返回给前端的路由
 */
@Data
public class RouterVO implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 路由路径   umsUser
     */
    private String path;

    /**
     * 组件路径  如：ums/user/index
     */
    private String componentPath;

    /**
     * 图标
     */
    private String icon;

    /**
     * 类型：0，目录，1菜单，2：按钮
     */
    private Integer menuType;

    /**
     * 子菜单
     */
    private List<RouterVO> children = new ArrayList<>();

}
