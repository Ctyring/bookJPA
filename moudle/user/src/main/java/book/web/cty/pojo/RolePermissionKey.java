package book.web.cty.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author cty
 * @date 2022/6/24
 */
@Getter
@Setter
public class RolePermissionKey implements Serializable {
    private static final long serialVersionUID = -5482200454871393530L;
    /**联合主键，字段名称与MemberDeptPO 类中一致*/
    private long roleId;
    /**联合主键，字段名称与MemberDeptPO 类中一致*/
    private long permissionId;

    public RolePermissionKey() { }

    public RolePermissionKey(long roleId, long permissionId) {
        this.roleId = roleId;
        this.permissionId = permissionId;
    }
}
