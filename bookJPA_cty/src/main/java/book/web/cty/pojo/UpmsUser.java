package book.web.cty.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * upmsUser实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="e_upms_user")
public class UpmsUser implements Serializable{

	@Id
	private Long id;//id


	
	private String name;//name
	private java.util.Date createTime;//create_time
	private java.util.Date updateTime;//update_time
	private String account;//account
	private String email;//email
	private Boolean isAdmin;//is_admin
	private Boolean isMd5;//is_md5
	private String password;//password
	private String phone;//phone
	private String remark;//remark
	private java.util.Date resetPwdTime;//reset_pwd_time
	private Boolean status;//status
	private String whiteIp;//white_ip
	private Long eruptOrgId;//erupt_org_id
	private Long eruptPostId;//erupt_post_id
	private Long createUserId;//create_user_id
	private Long updateUserId;//update_user_id
	private Long eruptMenuId;//erupt_menu_id

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Boolean getIsMd5() {
		return isMd5;
	}
	public void setIsMd5(Boolean isMd5) {
		this.isMd5 = isMd5;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public java.util.Date getResetPwdTime() {
		return resetPwdTime;
	}
	public void setResetPwdTime(java.util.Date resetPwdTime) {
		this.resetPwdTime = resetPwdTime;
	}

	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getWhiteIp() {
		return whiteIp;
	}
	public void setWhiteIp(String whiteIp) {
		this.whiteIp = whiteIp;
	}

	public Long getEruptOrgId() {
		return eruptOrgId;
	}
	public void setEruptOrgId(Long eruptOrgId) {
		this.eruptOrgId = eruptOrgId;
	}

	public Long getEruptPostId() {
		return eruptPostId;
	}
	public void setEruptPostId(Long eruptPostId) {
		this.eruptPostId = eruptPostId;
	}

	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Long getEruptMenuId() {
		return eruptMenuId;
	}
	public void setEruptMenuId(Long eruptMenuId) {
		this.eruptMenuId = eruptMenuId;
	}


	
}
