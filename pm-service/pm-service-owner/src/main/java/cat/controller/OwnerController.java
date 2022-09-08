package cat.controller;


import cat.entity.Community;
import cat.entity.Owner;
import cat.entity.QueryPageBean;
import cat.service.OwnerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import result.R;

/**
 * <p>
 * 业主表 前端控制器
 * </p>
 *
 * @author zxb
 * @since 2022-09-07
 */
@RestController
@RequestMapping("/owner/info")
public class OwnerController {

    @Autowired
    OwnerService service;

    //分页获取小区信息
    @PostMapping("/getAllOwners")
    public R<Page<Owner>> getAllCommunities(@RequestBody QueryPageBean queryPageBean){
        System.out.println(queryPageBean);
        //page对象需要接收当前页和每页条数
        Page<Owner> pageInfo = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //可能会有额外的查询条件
        LambdaQueryWrapper<Owner> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(queryPageBean.getQueryString()!=null, Owner::getName,queryPageBean.getQueryString());
        Page<Owner> carList = service.page(pageInfo, queryWrapper);
        System.out.println(carList.toString());
        return R.success(carList);
    }
    //新增
    @PostMapping("/add")
    public R<String> add(@RequestBody Owner owner){
        boolean save = service.save(owner);
        if (save){
            return R.success("添加业主成功");
        }
        return R.error("添加业主失败");
    }
    //修改
    @PutMapping("/update")
    public R<String> update(@RequestBody Owner owner){
//        e.setUpdateUser(1L);
//        e.setUpdateTime(LocalDateTime.now());
        System.out.println("=============修改ownerinfo============="+owner);
        boolean update = service.updateById(owner);


        if (update){
            return R.success("修改业主信息成功");

        }
        return R.error("修改业主信息失败");
    }

    //修改
    @DeleteMapping("/delete")
    public R<String> delete(@RequestBody Integer id){
//        e.setUpdateUser(1L);
//        e.setUpdateTime(LocalDateTime.now());
        boolean delete = service.removeById(id);


        if (delete){
            return R.success("删除业主信息成功");

        }
        return R.error("删除业主信息失败");
    }


}

