package book.web.cty.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import book.web.cty.pojo.UpmsOperateLog;
/**
 * upmsOperateLog数据访问接口
 * @author Administrator
 *
 */
public interface UpmsOperateLogDao extends JpaRepository<UpmsOperateLog,Long>,JpaSpecificationExecutor<UpmsOperateLog>{
	
}
