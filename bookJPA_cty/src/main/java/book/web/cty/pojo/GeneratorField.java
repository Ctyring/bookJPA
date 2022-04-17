package book.web.cty.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * generatorField实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="e_generator_field")
public class GeneratorField implements Serializable{

	@Id
	private Long id;//id


	
	private String fieldName;//field_name
	private Boolean isShow;//is_show
	private String linkClass;//link_class
	private Boolean notNull;//not_null
	private Boolean query;//query
	private String showName;//show_name
	private Integer sort;//sort
	private Boolean sortable;//sortable
	private String type;//type
	private Long classId;//class_id

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Boolean getIsShow() {
		return isShow;
	}
	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	public String getLinkClass() {
		return linkClass;
	}
	public void setLinkClass(String linkClass) {
		this.linkClass = linkClass;
	}

	public Boolean getNotNull() {
		return notNull;
	}
	public void setNotNull(Boolean notNull) {
		this.notNull = notNull;
	}

	public Boolean getQuery() {
		return query;
	}
	public void setQuery(Boolean query) {
		this.query = query;
	}

	public String getShowName() {
		return showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}

	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getSortable() {
		return sortable;
	}
	public void setSortable(Boolean sortable) {
		this.sortable = sortable;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}


	
}
