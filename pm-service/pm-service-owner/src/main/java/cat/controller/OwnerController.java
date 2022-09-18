package cat.controller;


import cat.client.CommunityClient;
import cat.dto.OwnerDto;
import cat.entity.Community;
import cat.entity.EstateManager;
import cat.entity.Owner;
import cat.entity.QueryPageBean;
import cat.service.OwnerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import result.R;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <p>
 * 业主表 前端控制器
 * </p>
 *
 * @author zxb
 * @since 2022-09-07
 */
@Slf4j
@RestController
@RequestMapping("/owner/info")
public class OwnerController {

    @Autowired
    OwnerService service;

    @Autowired
    private CommunityClient communityClient;





    //分页获取小区信息
    @PostMapping("/getAllOwners")
    public R<Page<OwnerDto>> getAllCommunities(@RequestBody QueryPageBean queryPageBean){
//        System.out.println("==========执行client=========="+communityClient.findById(1));
        System.out.println(queryPageBean);
        //page对象需要接收当前页和每页条数
        Page<Owner> pageInfo = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //可能会有额外的查询条件
        LambdaQueryWrapper<Owner> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(queryPageBean.getQueryString()!=null, Owner::getName,queryPageBean.getQueryString());
        Page<Owner> ownerPage = service.page(pageInfo, queryWrapper);

        //跨表查询！！！！
        Page<OwnerDto> ownerPage1 = new Page<>();
        BeanUtils.copyProperties(ownerPage, ownerPage1,"records");

        List<Owner> records = ownerPage.getRecords();

        List<OwnerDto> list = records.stream().map(owner -> {
            OwnerDto owner1 = new OwnerDto();
            //从owner表获取社区id
            Integer communityId = owner.getCommunityId();
            System.out.println("========社区id======"+communityId);
            //通过feign调用community controller方法查询社区名称
            String communityName = communityClient.findNameById(communityId);

            BeanUtils.copyProperties(owner, owner1);
            owner1.setCommunityName(communityName);
            return owner1;
        }).collect(Collectors.toList());

        ownerPage1.setRecords(list);

//        System.out.println();


        return R.success(ownerPage1);
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

    //feign  parking业务调用owner方法
    @PostMapping("{ownerId}")
    public String findNameById(@PathVariable Integer ownerId){
        Owner byId = service.getById(ownerId);
        System.out.println("======查询到的owner信息===="+byId);
        if (byId==null){
            return "-1";
        }
        String name = byId.getName();
        return name;

    }

    //获取所有小区的名称
    @GetMapping("/getOwnerList")
    public R<List<Owner>> getOwnerList(){

        List<Owner> list = service.list();

        return R.success(list);
    }





    //根据ownerid查询业主信息
    @PostMapping("/selectById")
    public R<Owner> selectById(@RequestBody Owner owner){
        Integer id = owner.getId();
        System.out.println(id);
        Owner owner1 = service.getById(id);
        System.out.println(owner1);
        if (owner1==null){
            return R.error("用户不存在");
        }
//        System.out.println(R.success(owner1));
        return R.success(owner1);
    }

}

