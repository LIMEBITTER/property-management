package cat.controller;


import cat.entity.Activity;
import cat.vo.QueryPageBean;
import cat.service.ActivityService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import result.R;

import java.util.List;

/**
 * <p>
 * 活动表 前端控制器
 * </p>
 *
 * @author zxb
 * @since 2022-09-07
 */
@RestController
@RequestMapping("/property/activity")
public class ActivityController {

    @Autowired
    ActivityService service;

    //分页获取小区信息
    @PostMapping("/getAllActivities")
    public R<Page<Activity>> getAllCommunities(@RequestBody QueryPageBean queryPageBean){
        System.out.println(queryPageBean);
        //page对象需要接收当前页和每页条数
        Page<Activity> pageInfo = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //可能会有额外的查询条件
        LambdaQueryWrapper<Activity> queryWrapper = new LambdaQueryWrapper<>();
        //通过 活动标题 来查询
        queryWrapper.like(queryPageBean.getQueryString()!=null, Activity::getTitle,queryPageBean.getQueryString());
        Page<Activity> activityList = service.page(pageInfo, queryWrapper);
//        System.out.println(carList.toString());
        return R.success(activityList);
    }


    //新增
    @PostMapping("/add")
    public R<String> add(@RequestBody Activity activity){
        boolean save = service.save(activity);
        if (save){
            return R.success("添加活动成功");
        }
        return R.error("添加活动失败");
    }
    //修改
    @PutMapping("/update")
    public R<String> update(@RequestBody Activity activity){
//        e.setUpdateUser(1L);
//        e.setUpdateTime(LocalDateTime.now());
        System.out.println("=============修改Activity============="+activity);
        boolean update = service.updateById(activity);


        if (update){
            return R.success("修改活动信息成功");

        }
        return R.error("修改活动信息失败");
    }

    //修改
    @DeleteMapping("/delete")
    public R<String> delete(@RequestBody Integer id){
//        e.setUpdateUser(1L);
//        e.setUpdateTime(LocalDateTime.now());
        boolean delete = service.removeById(id);


        if (delete){
            return R.success("删除活动信息成功");

        }
        return R.error("删除活动信息失败");
    }

    //获取所有活动信息（不分页）
    @GetMapping("/getAllActivities")
    public R<List<Activity>> getAllActivities(){
        List<Activity> list = service.list();
        return R.success(list);
    }

}

