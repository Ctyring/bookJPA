package book.web.cty.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author cty
 * @date 2022/6/24
 */
@Table(name = "role_permission", indexes={
        @Index(name = "role_id", columnList = "")})
@Entity
@Data
@IdClass(value = RolePermissionKey.class)
public class RolePermission implements Serializable {

    @Id
    private Long roleId;

    @Id
    private Long permissionId;
}
