package cat.vo;

import lombok.Data;
//分页类
@Data
public class QueryPageBean {
    //当前页码
    private Integer currentPage;
    //分多少页
    private Integer pageSize;

    //这是额外的查询条件
    //select * from category where categoryName = ? limit 10
    private String queryString;
}
