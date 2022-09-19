package cat.controller;


import cat.entity.Community;
import cat.entity.QueryPageBean;
import cat.service.CommunityService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import result.R;
import result.Result;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 小区表 前端控制器
 * </p>
 *
 * @author zxb
 * @since 2022-09-05
 */
@RestController
@RequestMapping("/community/info")
public class CommunityController {
    @Autowired
    private CommunityService service;

    //分页获取小区信息
    @PostMapping("/getAllCommunities")
    public R<Page<Community>> getAllCommunities(@RequestBody QueryPageBean queryPageBean){
        System.out.println(queryPageBean);
        //page对象需要接收当前页和每页条数
        Page<Community> pageInfo = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //可能会有额外的查询条件
        LambdaQueryWrapper<Community> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(queryPageBean.getQueryString()!=null, Community::getName,queryPageBean.getQueryString());
        Page<Community> categoryList = service.page(pageInfo, queryWrapper);

        return R.success(categoryList);
    }
    //新增
    @PostMapping("/add")
    public R<String> add(@RequestBody Community community){
        boolean save = service.save(community);
        if (save){
            return R.success("添加小区成功");
        }
        return R.error("添加小区失败");
    }
    //修改
    @PutMapping("/update")
    public R<String> update(@RequestBody Community community){
//        e.setUpdateUser(1L);
//        e.setUpdateTime(LocalDateTime.now());
        boolean update = service.updateById(community);


        if (update){
            return R.success("修改小区信息成功");

        }
        return R.error("修改小区信息失败");
    }

    //修改
    @DeleteMapping("/delete")
    public R<String> delete(@RequestBody Integer id){
//        e.setUpdateUser(1L);
//        e.setUpdateTime(LocalDateTime.now());
        boolean delete = service.removeById(id);


        if (delete){
            return R.success("删除小区信息成功");

        }
        return R.error("删除小区信息失败");
    }

    //feign  owner业务调用community方法
    @PostMapping("/{communityId}")
    public String findNameById(@PathVariable Integer communityId){

        Community byId = service.getById(communityId);
        String name = byId.getName();

        return name;
    }

    //获取所有小区的名称
    @GetMapping("/getCommunityList")
    public R<List<Community>> getCommunityList(){

        List<Community> list = service.list();

        return R.success(list);
    }

}

