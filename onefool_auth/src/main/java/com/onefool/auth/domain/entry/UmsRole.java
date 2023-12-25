package com.onefool.auth.domain.entry;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.*;

/**
 * 
 * @TableName ums_role
 */
@TableName(value ="ums_role")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UmsRole implements Serializable {
    /**
     * 角色id
     */
    @TableId(type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色标识
     */
    private String roleLabel;

    /**
     * 角色名字
     */
    private String roleName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态：0：可用，1：不可用
     */
    private Integer status;

    /**
     * 是否删除：0: 未删除，1：已删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 备注
     */
    private String remark;

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