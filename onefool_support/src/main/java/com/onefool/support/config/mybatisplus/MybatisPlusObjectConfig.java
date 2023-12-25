package com.onefool.support.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author linjiawei
 * @Date 2023/12/25 0:39
 * @description 自动填充创建时间和更新时间
 */
@Component
public class MybatisPlusObjectConfig implements MetaObjectHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisPlusObjectConfig.class);

    /**
     * 插入时自动填充创建时间
     * @param metaObject
     */
        @Override
        public void insertFill(MetaObject metaObject) {
            LOGGER.info("开启填充创建时间=====>");
            var now = LocalDateTime.now();
            this.strictInsertFill(metaObject, "createTime", () -> now, LocalDateTime.class); // 起始版本 3.3.3(推荐)
            this.strictUpdateFill(metaObject, "updateTime", () -> now, LocalDateTime.class); // 起始版本 3.3.3(推荐)
        }

    /**
     * 插入时自动填充更新时间
     * @param metaObject
     */
    @Override
        public void updateFill(MetaObject metaObject) {
            LOGGER.info("开启填充更新时间========>");
            this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class); // 起始版本 3.3.3(推荐)
        }

}
