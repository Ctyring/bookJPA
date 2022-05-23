package book.web.cty.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * storage实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="storage")
public class Storage implements Serializable{

	@Id
	private Long id;//id


	
	private java.util.Date storageTime;//storage_time
	private Long userId;//user_id

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public java.util.Date getStorageTime() {
		return storageTime;
	}
	public void setStorageTime(java.util.Date storageTime) {
		this.storageTime = storageTime;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}


	
}
