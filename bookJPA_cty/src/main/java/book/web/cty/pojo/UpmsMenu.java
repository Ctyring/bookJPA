package book.web.cty.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * upmsMenu实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="e_upms_menu")
public class UpmsMenu implements Serializable{

	@Id
	private Long id;//id


	
	private String createBy;//create_by
	private java.util.Date createTime;//create_time
	private String updateBy;//update_by
	private java.util.Date updateTime;//update_time
	private String code;//code
	private String icon;//icon
	private String name;//name
	private String param;//param
	private Integer sort;//sort
	private Integer status;//status
	private String type;//type
	private String value;//value
	private Long parentMenuId;//parent_menu_id

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}

	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	public Long getParentMenuId() {
		return parentMenuId;
	}
	public void setParentMenuId(Long parentMenuId) {
		this.parentMenuId = parentMenuId;
	}


	
}
