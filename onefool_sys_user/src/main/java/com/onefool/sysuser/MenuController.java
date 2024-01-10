package com.onefool.sysuser;

import com.onefool.common.domain.entry.UmsMenu;
import com.onefool.common.domain.entry.UmsRole;
import com.onefool.common.domain.vo.RouterVO;
import com.onefool.common.mapper.UmsRoleMapper;
import com.onefool.common.pojo.Result;
import com.onefool.common.service.UmsMenuService;
import com.onefool.common.utils.OnefoolSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author linjiawei
 * @Date 2023/12/26 20:05
 */
@RestController
@RequestMapping("/sys/menu")
public class MenuController {

    @Autowired
    private UmsMenuService umsMenuService;

    /**
     * 查询自己的菜单
     */
    @GetMapping("/self")
    public Result<List<RouterVO>> searchSelfMenu(){
        return umsMenuService.searchSelfMenu();
    }
}
