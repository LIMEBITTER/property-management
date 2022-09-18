package cat.controller;


import cat.client.OwnerClient;
import cat.dto.CarDto;
import cat.dto.OwnerDto;
import cat.entity.Car;
import cat.entity.Community;
import cat.entity.Owner;
import cat.entity.QueryPageBean;
import cat.service.CarService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import result.R;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    OwnerClient ownerClient;

    //分页获取小区信息
    @PostMapping("/getAllCars")
    public R<Page<CarDto>> getAllCommunities(@RequestBody QueryPageBean queryPageBean){
        System.out.println(queryPageBean);
        //page对象需要接收当前页和每页条数
        Page<Car> pageInfo = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //可能会有额外的查询条件
        LambdaQueryWrapper<Car> queryWrapper = new LambdaQueryWrapper<>();
        //通过 车牌号 来查询
        queryWrapper.like(queryPageBean.getQueryString()!=null, Car::getCarNumber,queryPageBean.getQueryString());
        Page<Car> carList = service.page(pageInfo, queryWrapper);

        //跨表查询！！！！
        Page<CarDto> carDtoPage = new Page<>();
        BeanUtils.copyProperties(carList, carDtoPage,"records");

        List<Car> records = carList.getRecords();

        List<CarDto> list = records.stream().map(car -> {
            CarDto carDto = new CarDto();
            //从owner表获取社区id
            Integer ownerId = car.getOwnerId();
            System.out.println("========ownerid======"+ownerId);
            //通过feign调用community controller方法查询社区名称
            String ownerName = ownerClient.findNameById(ownerId);

            BeanUtils.copyProperties(car, carDto);
            carDto.setOwnerName(ownerName);
            return carDto;
        }).collect(Collectors.toList());

        carDtoPage.setRecords(list);

        return R.success(carDtoPage);
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


//    //根据ownerid查询业主信息
//    @PostMapping("/selectById")
//    public R<Car> selectById(@RequestBody Car car){
//        Integer id = car.getId();
//        System.out.println(id);
//        Car car1 = service.getById(id);
//
//
//
//
//        System.out.println(carList);
//        if (car1==null){
//            return R.error("用户不存在");
//        }
////        System.out.println(R.success(owner1));
//        return R.success(car1);
//    }

}

