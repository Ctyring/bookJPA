package exception;

import entity.Result;
import entity.StatusCode;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.connection.PoolException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 * 
 * @Author scott
 * @Date 2022
 */
@RestControllerAdvice
@Slf4j
public class BookExceptionHandler {

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(BookException.class)
	public Result handleException(BookException e){
		log.error(e.getMessage(), e);
		return new Result(false, StatusCode.ERROR,e.getMessage());
	}

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(Book401Exception.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public Result handle401Exception(Book401Exception e){
		log.error(e.getMessage(), e);
		return new Result(false, StatusCode.UNAUTHORIZED,e.getMessage());
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public Result handlerNoFoundException(Exception e) {
		log.error(e.getMessage(), e);
		return new Result(false, StatusCode.NOTFOUND,e.getMessage());
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public Result handleDuplicateKeyException(DuplicateKeyException e){
		log.error(e.getMessage(), e);
		return new Result(false, StatusCode.ERROR,e.getMessage());
	}

//	@ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
//	public Result handleAuthorizationException(AuthorizationException e){
//		log.error(e.getMessage(), e);
//		return new Result(false, StatusCode.UNAUTHORIZED,"没有权限");
//	}

	@ExceptionHandler(Exception.class)
	public Result handleException(Exception e){
		log.error(e.getMessage(), e);
		return new Result(false, StatusCode.ERROR,"操作失败");
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Result HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
		StringBuffer sb = new StringBuffer();
		sb.append("不支持");
		sb.append(e.getMethod());
		sb.append("请求方法，");
		sb.append("支持以下");
		String [] methods = e.getSupportedMethods();
		if(methods!=null){
			for(String str:methods){
				sb.append(str);
				sb.append("、");
			}
		}
		log.error(sb.toString(), e);
		//return Result.error("没有权限，请联系管理员授权");
		return new Result(false, StatusCode.ERROR,"操作失败");
	}
	
	 /** 
	  * spring默认上传大小100MB 超出大小捕获异常MaxUploadSizeExceededException 
	  */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Result handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
    	log.error(e.getMessage(), e);
		return new Result(false, StatusCode.ERROR,"文件大小超出10MB限制, 请压缩或降低文件质量! ");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result handleDataIntegrityViolationException(DataIntegrityViolationException e) {
    	log.error(e.getMessage(), e);
		return new Result(false, StatusCode.ERROR,"字段太长,超出数据库字段的长度");
    }

    @ExceptionHandler(PoolException.class)
    public Result handlePoolException(PoolException e) {
    	log.error(e.getMessage(), e);
		return new Result(false, StatusCode.ERROR,"Redis 连接异常!");
    }

}
