package cat.controller;


import cat.entity.Complaint;
import cat.entity.QueryPageBean;
import cat.service.ComplaintService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import result.R;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * <p>
 * 投诉表 前端控制器
 * </p>
 *
 * @author zxb
 * @since 2022-09-07
 */
@RestController
@RequestMapping("/property/complaint")
public class ComplaintController {

    @Autowired
    ComplaintService service;

    //分页获取小区信息
    @PostMapping("/getAllComplaints")
    public R<Page<Complaint>> getAllCommunities(@RequestBody QueryPageBean queryPageBean){
        System.out.println(queryPageBean);
        //page对象需要接收当前页和每页条数
        Page<Complaint> pageInfo = new Page<>(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //可能会有额外的查询条件
        LambdaQueryWrapper<Complaint> queryWrapper = new LambdaQueryWrapper<>();
        //通过 投诉名称 来查询
        queryWrapper.like(queryPageBean.getQueryString()!=null, Complaint::getDescriptionName,queryPageBean.getQueryString());
        Page<Complaint> complaintList = service.page(pageInfo, queryWrapper);
//        System.out.println(carList.toString());
        return R.success(complaintList);
    }
    //新增
    @PostMapping("/add")
    public R<String> add(@RequestBody Complaint complaint){
        boolean save = service.save(complaint);
        if (save){
            return R.success("添加投诉成功");
        }
        return R.error("添加投诉失败");
    }
    //修改
    @PutMapping("/update")
    public R<String> update(@RequestBody Complaint complaint){
//        e.setUpdateUser(1L);
//        e.setUpdateTime(LocalDateTime.now());
        System.out.println("=============修改Complaint============="+complaint);
        boolean update = service.updateById(complaint);


        if (update){
            return R.success("修改投诉信息成功");

        }
        return R.error("修改投诉信息失败");
    }

    //修改
    @DeleteMapping("/delete")
    public R<String> delete(@RequestBody Integer id){
//        e.setUpdateUser(1L);
//        e.setUpdateTime(LocalDateTime.now());
        boolean delete = service.removeById(id);


        if (delete){
            return R.success("删除投诉信息成功");

        }
        return R.error("删除投诉信息失败");
    }



}

