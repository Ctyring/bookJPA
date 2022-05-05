package book.web.cty.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import book.web.cty.pojo.Permission;
import book.web.cty.service.PermissionService;
import book.web.cty.service.RoleService;
import book.web.cty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.dev33.satoken.stp.StpInterface;

/**
 * 自定义权限验证接口扩展
 */
@Component    // 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> perList = new ArrayList<>();
        List<String> list = getRoleList(loginId, loginType);

        for (String role: list) {
            Map map = new HashMap();
            map.put("role", role);
            List<Permission> permissionList = permissionService.findSearch(map);
            for(Permission permission: permissionList){
                if(!perList.contains(permission.getName())){
                    perList.add(permission.getName());
                }
            }
        }

        return perList;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        list.add(roleService.findById(Long.parseLong(userService.findById(Long.parseLong(loginId.toString())).getRole())).getName());
        return list;
    }

}

