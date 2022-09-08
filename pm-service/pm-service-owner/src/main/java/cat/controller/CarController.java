package cat.controller;


import cat.entity.Car;
import cat.entity.Owner;
import cat.entity.QueryPageBean;
import cat.service.CarService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import result.R;

/**
 * <p>
 * 车辆表 前端控制器
 * </p>
 *
 * @author zxb
 * @since 2022-09-07
 */
@RestController
@RequestMapping("/owner/car")
public class CarController {

    @Autowired
    CarService service;

    //分页获取小区信息
    @PostMapping("/getAllCars")
    public R<Page<Car>> getAllCommunities(@RequestBody QueryPageBean queryPageBean){
        System.out.println(queryPageBean);
        //page对象需要接收当前页和每页条数
        Page<Car> pageInfo = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //可能会有额外的查询条件
        LambdaQueryWrapper<Car> queryWrapper = new LambdaQueryWrapper<>();
        //通过 车牌号 来查询
        queryWrapper.like(queryPageBean.getQueryString()!=null, Car::getCarNumber,queryPageBean.getQueryString());
        Page<Car> carList = service.page(pageInfo, queryWrapper);
//        System.out.println(carList.toString());
        return R.success(carList);
    }
    //新增
    @PostMapping("/add")
    public R<String> add(@RequestBody Car car){
        boolean save = service.save(car);
        if (save){
            return R.success("添加车辆成功");
        }
        return R.error("添加车辆失败");
    }
    //修改
    @PutMapping("/update")
    public R<String> update(@RequestBody Car car){
//        e.setUpdateUser(1L);
//        e.setUpdateTime(LocalDateTime.now());
        System.out.println("=============修改car============="+car);
        boolean update = service.updateById(car);


        if (update){
            return R.success("修改车辆信息成功");

        }
        return R.error("修改车辆信息失败");
    }

    //修改
    @DeleteMapping("/delete")
    public R<String> delete(@RequestBody Integer id){
//        e.setUpdateUser(1L);
//        e.setUpdateTime(LocalDateTime.now());
        boolean delete = service.removeById(id);


        if (delete){
            return R.success("删除车辆信息成功");

        }
        return R.error("删除车辆信息失败");
    }
}

