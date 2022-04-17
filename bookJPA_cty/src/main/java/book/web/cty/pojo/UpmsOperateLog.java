package book.web.cty.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * upmsOperateLog实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="e_upms_operate_log")
public class UpmsOperateLog implements Serializable{

	@Id
	private Long id;//id


	
	private String apiName;//api_name
	private java.util.Date createTime;//create_time
	private String errorInfo;//error_info
	private String ip;//ip
	private String operateUser;//operate_user
	private String region;//region
	private String reqAddr;//req_addr
	private String reqMethod;//req_method
	private String reqParam;//req_param
	private Boolean status;//status
	private Long totalTime;//total_time

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}

	public String getReqAddr() {
		return reqAddr;
	}
	public void setReqAddr(String reqAddr) {
		this.reqAddr = reqAddr;
	}

	public String getReqMethod() {
		return reqMethod;
	}
	public void setReqMethod(String reqMethod) {
		this.reqMethod = reqMethod;
	}

	public String getReqParam() {
		return reqParam;
	}
	public void setReqParam(String reqParam) {
		this.reqParam = reqParam;
	}

	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}


	
}
