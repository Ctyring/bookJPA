package book.web.cty.config;

import book.web.cty.pojo.Role;
import book.web.cty.pojo.RolePermission;
import book.web.cty.service.PermissionService;
import book.web.cty.service.RolePermissionService;
import book.web.cty.service.RoleService;
import book.web.cty.service.UserService;
import cn.dev33.satoken.stp.StpInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private RolePermissionService rolePermissionService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        List<String> perList = new ArrayList<>();
        List<String> list = getRoleList(loginId, loginType);
        List<String> permissionList = new ArrayList<>();
        for (String role: list) {
            Map map = new HashMap();
            map.put("role", role);
            List<Role> roles = roleService.findSearch(map);
            for (Role role1 : roles){
                Map map1 = new HashMap();
                map1.put("roleId", role1.getId());
                List<RolePermission> permissions = rolePermissionService.findSearch(map1);
                for (RolePermission p : permissions){
                    permissionList.add(permissionService.findById(p.getPermissionId()).getName());
                }
            }
        }
        return permissionList;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> list = new ArrayList<>();
        list.add(roleService.findById((userService.findUserByUsername(loginId.toString()).getRole())).getName());
        return list;
    }

}

