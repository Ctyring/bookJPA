package book.web.cty.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * user实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="user")
public class User implements Serializable{

	@Id
	private Long id;//id


	
	private String birthday;//birthday
	private String category;//category
	private LONGTEXT des;//des
	private Integer gender;//gender
	private String name;//name
	private String password;//password
	private String picture;//picture
	private Integer status;//status

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public LONGTEXT getDes() {
		return des;
	}
	public void setDes(LONGTEXT des) {
		this.des = des;
	}

	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


	
}
