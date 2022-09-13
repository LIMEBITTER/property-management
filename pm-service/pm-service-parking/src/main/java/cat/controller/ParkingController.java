package cat.controller;



import cat.client.OwnerClient;
import cat.dto.OwnerDto;
import cat.dto.ParkingDto;
import cat.entity.Owner;
import cat.entity.Parking;
import cat.entity.QueryPageBean;
import cat.service.ParkingService;
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

    @Autowired
    OwnerClient ownerClient;

    //分页获取小区信息
    @PostMapping("/getAllParking")
    public R<Page<ParkingDto>> getAllCommunities(@RequestBody QueryPageBean queryPageBean){
        System.out.println(queryPageBean);
        //page对象需要接收当前页和每页条数
        Page<Parking> pageInfo = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //可能会有额外的查询条件
        LambdaQueryWrapper<Parking> queryWrapper = new LambdaQueryWrapper<>();
        //通过 车位编号 来查询
        queryWrapper.like(queryPageBean.getQueryString()!=null, Parking::getCode,queryPageBean.getQueryString());
        Page<Parking> parkingList = service.page(pageInfo, queryWrapper);

        //跨表查询！！！！
        Page<ParkingDto> parkingDtoPage = new Page<>();
        BeanUtils.copyProperties(parkingList, parkingDtoPage,"records");

        List<Parking> records = parkingList.getRecords();

        List<ParkingDto> list = records.stream().map(parking -> {
            ParkingDto parkingDto = new ParkingDto();
            //从owner表获取id
            Integer ownerId = parking.getOwnerId();
            System.out.println("========ownerid======"+ownerId);
            //通过feign调用community controller方法查询社区名称
            String ownerName = ownerClient.findNameById(ownerId);

            BeanUtils.copyProperties(parking, parkingDto);
            parkingDto.setOwnerName(ownerName);
            return parkingDto;
        }).collect(Collectors.toList());

        parkingDtoPage.setRecords(list);

        return R.success(parkingDtoPage);
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

