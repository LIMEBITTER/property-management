package cat.controller;


import cat.entity.EstateManager;
import cat.entity.Owner;
import cat.service.EstateManagerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import result.R;


import javax.servlet.http.HttpServletRequest;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 物业管理人员表 前端控制器
 * </p>
 *
 * @author zxb
 * @since 2022-09-06
 */
@Slf4j
@RestController
@RequestMapping("/community/estateManager")
public class EstateManagerController {

    @Autowired
    EstateManagerService service;

    @PostMapping("/login")
    public R<EstateManager> login(@RequestBody EstateManager estateManager){
        log.info("登陆信息：{}",estateManager);
        String password = estateManager.getPassword();
        //2、根据用户名查询数据库对应信息
        //a、用LambdaQueryWrapper对象
        LambdaQueryWrapper<EstateManager> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(estateManager.getUserName()!=null,EstateManager::getUserName,estateManager.getUserName());
        EstateManager esm = service.getOne(queryWrapper);
        //3、先判断该用户是否存在
        if (esm == null){
            return R.error("用户不存在");
        }
        //4、验证密码
        if (! password.equals(esm.getPassword())){
            return R.error("密码不正确");
        }
        //已有token,不重新设token
//        if(esm.getToken()==null){
            esm.setToken(UUID.randomUUID().toString());
            service.updateById(esm);
//        }


        return  R.success(esm);

    }
    @PostMapping("/register")
    public R<String> register(@RequestBody EstateManager estateManager)  {
        log.info("管理员注册信息：{}",estateManager);

        LambdaQueryWrapper<EstateManager> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(estateManager.getUserName()!=null,EstateManager::getUserName,estateManager.getUserName());
        EstateManager estateManager1 = service.getOne(queryWrapper);
        //3、先判断该用户是否存在
        if (estateManager1 != null){
            return R.error("用户名已存在！");
        }

        service.save(estateManager);
        return R.success("注册成功！");

    }


    /**
     * 退出登陆
     *
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //1、从session域将employee对应的值清空
        request.getSession().removeAttribute("role");
        return R.success("退出登陆成功");
    }





}

