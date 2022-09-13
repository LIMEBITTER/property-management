package cat.controller;


import cat.entity.EstateManager;
import cat.service.EstateManagerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import result.R;
import result.Result;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
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

//    @PostMapping("/login")
//    public R<EstateManager> login(HttpServletRequest request, @RequestBody EstateManager estateManager){
//        System.out.println(estateManager+"-----------");
//
//        //1.对页面传递的密码进行加密
//        String password = estateManager.getPassword();
//        String digestAsHexpass = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
//        //2.根据用户名查询数据库对应信息
//        //a.用lambdaquerywrapper对象进行查询，此时没有id了 ，所以不能用id查询
//        LambdaQueryWrapper<EstateManager> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(estateManager.getUserName()!=null, EstateManager::getUserName, estateManager.getUserName());
//        EstateManager esm = service.getOne(queryWrapper);
//        //3.先判断用户是否存在
//        if (esm == null){
//            return R.error("用户不存在");
//
//        }
//
//        //4.验证密码
//        if (!digestAsHexpass.equals(esm.getPassword())){
//            return R.error("密码不正确");
//        }
//
//        //判断账号状态  暂时无！！！
////        if (esm.getStatus()!=1){
////            return R.error("账号被禁用");
////        }
//
//        //5.将用户信息存储到session域里
//        request.getSession().setAttribute("estateManager", esm.getId());
//
//        //前端永远通过ajax访问！！，而不是访问session的数据
//        return R.success(esm);
//
//    }

//    @PostMapping("login")
//    public R<Map<String, Object>> login() {
//        //{"code":20000,"data":{"token":"admin-token"}}
//        Map<String,Object> map = new HashMap<>();
//        map.put("token","admin-token");
//        return R.success(map);
//    }


//    @GetMapping("info")
//    public R<Map<String, Object>> info() {
//        //{"code":20000,"data":
//        // {"roles":["admin"],
//        // "introduction":"I am a super administrator",
//        // "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
//        // "name":"Super Admin"}}
//        Map<String,Object> map = new HashMap<>();
//        map.put("roles","admin");
//        map.put("introduction","I am a super administrator");
//        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
//        map.put("name","Super Admin");
//        return R.success(map);
//    }


    @PostMapping("/login")
    public R<EstateManager> login(HttpServletRequest request, @RequestBody EstateManager estateManager){
        log.info("登陆信息：{}",estateManager);
        String password = estateManager.getPassword();
        //2、根据用户名查询数据库对应信息
        //a、用lambdaquerywrapper对象
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
        estateManager.setToken(UUID.randomUUID().toString());

        //5、将用户信息存储到session域里
        request.getSession().setAttribute("estateManager",estateManager.getId());

        return  R.success(esm);

    }



    /**
     * @description:退出登陆
     * @param:
     * @returm:
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //1、从session域将employee对应的值清空
        request.getSession().removeAttribute("estateManager");
        return R.success("退出登陆成功");
    }





}

