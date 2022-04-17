package book.web.cty.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * dictItem实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="e_dict_item")
public class DictItem implements Serializable{

	@Id
	private Long id;//id


	
	private String createBy;//create_by
	private java.util.Date createTime;//create_time
	private String updateBy;//update_by
	private java.util.Date updateTime;//update_time
	private String code;//code
	private String name;//name
	private String remark;//remark
	private Integer sort;//sort
	private Long eruptDictId;//erupt_dict_id

	
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

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getEruptDictId() {
		return eruptDictId;
	}
	public void setEruptDictId(Long eruptDictId) {
		this.eruptDictId = eruptDictId;
	}


	
}