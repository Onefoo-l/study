package com.onefool.common.domain.entry;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @TableName ums_menu
 */
@TableName(value ="ums_menu")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UmsMenu implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
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
     * 排序
     */
    private Integer sort;

    /**
     * 类型：0，目录，1菜单，2：按钮
     */
    private Integer menuType;

    /**
     * 路由路径   umsUser
     */
    private String path;

    /**
     * 组件路径  如：ums/user/index
     */
    private String componentPath;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 图标
     */
    private String icon;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 状态：0：可用，1：不可用
     */
    private Integer status;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 更新者
     */
    private String updater;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}