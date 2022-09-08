package cat.controller;



import cat.entity.Parking;
import cat.entity.QueryPageBean;
import cat.service.ParkingService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import result.R;

/**
 * <p>
 * 车位表 前端控制器
 * </p>
 *
 * @author zxb
 * @since 2022-09-07
 */
@RestController
@RequestMapping("/parking/info")
public class ParkingController {

    @Autowired
    ParkingService service;

    //分页获取小区信息
    @PostMapping("/getAllParking")
    public R<Page<Parking>> getAllCommunities(@RequestBody QueryPageBean queryPageBean){
        System.out.println(queryPageBean);
        //page对象需要接收当前页和每页条数
        Page<Parking> pageInfo = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //可能会有额外的查询条件
        LambdaQueryWrapper<Parking> queryWrapper = new LambdaQueryWrapper<>();
        //通过 车位编号 来查询
        queryWrapper.like(queryPageBean.getQueryString()!=null, Parking::getCode,queryPageBean.getQueryString());
        Page<Parking> parkingList = service.page(pageInfo, queryWrapper);
//        System.out.println(carList.toString());
        return R.success(parkingList);
    }
    //新增
    @PostMapping("/add")
    public R<String> add(@RequestBody Parking parking){
        boolean save = service.save(parking);
        if (save){
            return R.success("添加停车位成功");
        }
        return R.error("添加停车位失败");
    }
    //修改
    @PutMapping("/update")
    public R<String> update(@RequestBody Parking parking){
//        e.setUpdateUser(1L);
//        e.setUpdateTime(LocalDateTime.now());
        System.out.println("=============修改parkinginfo============="+parking);
        boolean update = service.updateById(parking);


        if (update){
            return R.success("修改停车位信息成功");

        }
        return R.error("修改停车位信息失败");
    }

    //修改
    @DeleteMapping("/delete")
    public R<String> delete(@RequestBody Integer id){
//        e.setUpdateUser(1L);
//        e.setUpdateTime(LocalDateTime.now());
        boolean delete = service.removeById(id);


        if (delete){
            return R.success("删除停车位信息成功");

        }
        return R.error("删除停车位信息失败");
    }

}

