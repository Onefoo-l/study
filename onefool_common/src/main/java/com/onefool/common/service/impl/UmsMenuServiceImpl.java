package com.onefool.common.service.impl;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.onefool.common.domain.entry.UmsMenu;
import com.onefool.common.domain.vo.RouterVO;
import com.onefool.common.mapper.UmsMenuMapper;
import com.onefool.common.mapper.UmsRoleMapper;
import com.onefool.common.pojo.Result;
import com.onefool.common.service.UmsMenuService;
import com.onefool.common.utils.OnefoolSecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author Onefool
* @description 针对表【ums_menu】的数据库操作Service实现
* @createDate 2023-12-24 10:51:50
*/
@Service
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper, UmsMenu> implements UmsMenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsMenuServiceImpl.class);

    @Autowired
    private UmsRoleMapper umsRoleMapper;

    /**
     * 查询自己的菜单
     */
    @Override
    public Result<List<RouterVO>> searchSelfMenu() {
        LOGGER.info("进入searchSelfMenu方法===>动态路由");
        //获取当前用户的id
        var userId = OnefoolSecurityUtil.getUserId();
        //获取当前用户角色id集合
        var umsRoleList = umsRoleMapper.selectRoleByUserId(userId);
        LOGGER.info("当前用户角色Id集合=====>" + umsRoleList);
        //获取当前用户菜单集合
        List<UmsMenu> umsMenus = baseMapper.selectRoleAndMenu(umsRoleList);
        LOGGER.info("当前用户菜单集合====>" +  umsMenus);
        //通过递归设置菜单的属性结构
        //1.获取所有的1级菜单【parentId = 0】
        //2. 遍历1级菜单，获取他所有的子元素【其他数据的parentId = 当前元素的Id】
        var router = getRouter(umsMenus);
        router.forEach(System.out::println);
        return Result.ok(router);
    }

    private List<RouterVO> getRouter(List<UmsMenu> menuList){
        var routerVOS = new ArrayList<RouterVO>();
        //获取所有的1级菜单【parentId = 0】
        var parentIdZero = menuList.stream().filter(m -> m.getParentId() == 0).toList();
        //转换对象类型
        parentIdZero.forEach(p -> {
            var routerVo = new RouterVO();
            BeanUtils.copyProperties(p,routerVo);
            routerVOS.add(routerVo);
        });
        //循环1级路由，获取所有的子菜单
        routerVOS.forEach(r -> {
            //获取所有的子节点
            List<RouterVO> childrenList = buildTree(menuList, r.getId());
            //设置子节点
            r.setChildren(childrenList);
        });
        return routerVOS;
    }

    /**
     * 获取所有子节点，递归获取【如果是2级不需要递归了】
     */
    private List<RouterVO> buildTree(List<UmsMenu> allMenu,Long id){
        var childrenList = new ArrayList<RouterVO>();
        //遍历所有的数据
        allMenu.forEach(p -> {
            //表结构为  id   parentId
            //判断p的parentId是否与传进来的主键id相同
            if (p.getParentId().equals(id)){
                //相同则说明是子节点
                var routerVO = new RouterVO();
                BeanUtils.copyProperties(p,routerVO);
                childrenList.add(routerVO);
            }
        });

        //递归childrenList 可能还有子节点
        childrenList.forEach(c -> c.setChildren(buildTree(allMenu,c.getId())));
        return childrenList;
    }
}




