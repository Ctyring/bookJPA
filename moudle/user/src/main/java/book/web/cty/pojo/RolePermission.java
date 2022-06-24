package book.web.cty.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author cty
 * @date 2022/6/24
 */
@Table(name = "role_permission")
@Entity
@Data
public class RolePermission {
    @Id
    private Long RoleId;

    @Id
    private Long permissionId;
}
