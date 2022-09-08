package cat.entity;

import lombok.Data;

@Data
public class QueryPageBean {
    private Integer currentPage;
    private Integer pageSize;

    //这是额外的查询条件
    //select * from category where categoryName = ? limit 10
    private String queryString;
}
