package book.web.cty.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * upmsLoginLog实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="e_upms_login_log")
public class UpmsLoginLog implements Serializable{

	@Id
	private Long id;//id


	
	private String browser;//browser
	private String deviceType;//device_type
	private String ip;//ip
	private java.util.Date loginTime;//login_time
	private String region;//region
	private String systemName;//system_name
	private String token;//token
	private String userName;//user_name

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public java.util.Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(java.util.Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}

	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


	
}
