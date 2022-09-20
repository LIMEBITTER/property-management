package cat.controller;



import cat.client.CommunityClient;
import cat.client.OwnerClient;
import cat.dto.ParkingDto;
import cat.entity.Parking;
import cat.vo.QueryPageBean;
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
    CommunityClient communityClient;

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
//            Integer communityId = parking.getCommunityId();
//            System.out.println("========getCommunityId======"+communityId);
            Integer ownerId = parking.getOwnerId();
            //通过feign调用community controller方法查询社区名称
//            String communityName = communityClient.findNameById(communityId);
            String ownerName = ownerClient.findNameById(ownerId);

            BeanUtils.copyProperties(parking, parkingDto);
//            parkingDto.setCommunityName(communityName);
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
        System.out.println(update);


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

    //获取所有小区的名称
    @GetMapping("/getParkingList")
    public R<List<Parking>> getParkingList(){

        List<Parking> list = service.list();

        return R.success(list);
    }


    //获取所有小区的名称
    @PostMapping("/selectById")
    public R<Parking> selectById(@RequestBody Parking parking){
        Integer id = parking.getId();
        Parking byId = service.getById(id);



        return R.success(byId);
    }

}

