package result;

import lombok.*;

import java.io.Serializable;

/**
 * @Auth: zhuan
 * @Desc: 不分页-标准返回结果
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
public class Result implements Serializable {

    private static final long serialVersionUID = -2435089504958177374L;

    private boolean flag;//是否成功
    private Integer code;//返回码
    private String message;//返回消息
    private Object data;
    /**
     * 功能描述: 无参构造
     * @return :
     */
    public Result() {
    }
    /**
     * 功能描述: 没有数据集结果的有参构造
     * @param flag  请求是否成功
     * @param code  请求返回状态码
     * @param message   请求返回消息提示
     * @return : 返回结果对象
     */
    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
    /**
     * 功能描述: 有数据集结果的有参构造
     * @param flag  请求是否成功
     * @param code  请求返回状态码
     * @param message   请求返回消息提示
     * @param data  返回页面数据
     * @return : 返回结果对象
     */


}
