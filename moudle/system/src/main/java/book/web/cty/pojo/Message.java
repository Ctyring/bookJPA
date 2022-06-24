package book.web.cty.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * message实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "message")
public class Message implements Serializable {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator", strategy = "native")
    private Long id;//id
    private String content;//content
    private java.util.Date createTime;//create_time
    private String name;//name


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
