package result;

import lombok.Data;

import java.io.Serializable;
/**
 * @Auth: zhuan
 * @Desc: 分页-标准返回结果
 */
@Data
public class PageResult implements Serializable {

    private static final long serialVersionUID = -6250130355901431732L;

    private boolean flag;//是否成功
    private Integer code;//返回码
    private String message;//返回消息
    private Object data;
    private Long total;
    /**
     * 功能描述: 无参构造
     * @return :
     */


    /**
     * 功能描述:
     * @param flag  请求是否成功
     * @param code  状态码
     * @param message   消息提示
     * @param data      结果集列表
     * @param total     总条数
     * @return :
     */


}
