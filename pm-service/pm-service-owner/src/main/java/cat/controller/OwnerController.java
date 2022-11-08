package cat.controller;


import cat.client.CommunityClient;
import cat.dto.OwnerDto;
import cat.dto.OwnerRoleDto;
import cat.entity.Owner;
import cat.entity.OwnerRole;
import cat.vo.QueryPageBean;
import cat.service.OwnerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import result.R;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Resource
    StringRedisTemplate stringRedisTemplate;

    //分页获取小区信息
    @PostMapping("/getAllOwners")
    public R<Page<OwnerDto>> getAllCommunities(@RequestBody QueryPageBean queryPageBean){
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
            //通过feign调用community controller方法查询社区名称
            String communityName = communityClient.findNameById(communityId);
            BeanUtils.copyProperties(owner, owner1);
            owner1.setCommunityName(communityName);
            return owner1;
        }).collect(Collectors.toList());

        ownerPage1.setRecords(list);
        return R.success(ownerPage1);
    }
    //新增
    @PostMapping("/add")
    public R<String> add(@RequestBody Owner owner){
        boolean check = checkIdCard(owner.getIdCard());
        if (check==false){
            return R.error("重复的身份证号！");
        }else{
            boolean save = service.save(owner);
            if (save){
                return R.success("添加业主成功");
            }
            return R.error("添加业主失败");
        }

    }
    public boolean checkIdCard(String idCard){
        LambdaQueryWrapper<Owner> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(idCard!=null, Owner::getIdCard,idCard);
        Owner one = service.getOne(queryWrapper);
        if(one!=null){
            return true;
        }
        return false;
    }
    //修改
    @PutMapping("/update")
    public R<String> update(@RequestBody Owner owner){

        System.out.println("=============修改ownerInfo============="+owner);
        boolean update = service.updateById(owner);


        if (update){
            return R.success("修改业主信息成功");

        }
        return R.error("修改业主信息失败");
    }

    //修改
    @DeleteMapping("/delete")
    public R<String> delete(@RequestBody Integer id){

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

    //根据ownerId查询业主信息
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

    @PostMapping("/login")
    public R<Owner> login(@RequestBody Owner owner){
        log.info("业主登陆信息：{}",owner);
        String password = owner.getPassword();

        //2、根据用户名查询数据库对应信息
        //a、用LambdaQueryWrapper对象
        LambdaQueryWrapper<Owner> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(owner.getUserName()!=null,Owner::getUserName,owner.getUserName());
        Owner owner1 = service.getOne(queryWrapper);

        //3、先判断该用户是否存在
        if (owner1 == null){
            return R.error("用户不存在");
        }
        //4、验证密码
        if (! password.equals(owner1.getPassword())){
            return R.error("密码不正确");
        }

        String token;
        //用户首次登录，无token，创建token
        if (owner1.getToken()==null){
            owner1.setToken(UUID.randomUUID().toString());
            service.updateById(owner1);
            token= owner1.getToken();
            log.info("用户首次登录，token:{}", token );
        }

        return  R.success(owner1);

    }


    @PostMapping("/register")
    public R<String> register(@RequestBody Owner owner)  {
        log.info("业主注册信息：{}",owner);

        LambdaQueryWrapper<Owner> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(owner.getUserName()!=null,Owner::getUserName,owner.getUserName());
        Owner owner1 = service.getOne(queryWrapper);
        //3、先判断该用户是否存在
        if (owner1 != null){
            return R.error("用户名已存在！");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse("1978-01-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        owner.setBirthday(date);
        owner.setStatus(1);
        service.save(owner);
        return R.success("注册成功！");

    }


    /**
     * 请求授权页面
     */
    @PostMapping(value = "/gitee/authLogin")
    public R<Owner> authLogin(@RequestBody Owner owner) {
        Owner owner1 = new Owner();
        owner1.setUserName(owner.getName());
        System.out.println("======authlogin中的owner1=="+owner1);
        LambdaQueryWrapper<Owner> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(owner1.getUserName()!= null, Owner::getUserName, owner1.getUserName());
        System.out.println(queryWrapper);
        Owner owner2 = service.getOne(queryWrapper);

        if (owner2==null){
            log.info("社交用户无对应信息，注册！");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = dateFormat.parse("1978-01-01");
            } catch (ParseException e) {
                e.printStackTrace();
            }

            owner1.setBirthday(date);
            owner1.setToken(UUID.randomUUID().toString());
            owner1.setPassword("123");

            service.save(owner1);
            return R.success(owner1);
        }
        log.info("社交用户有对应信息！");
        return R.success(owner2);
    }

    @PostMapping("/gitee/getOwnerByName")
    public R<Owner> getOwnerByName(@RequestBody Owner owner){
        System.out.println("getownerbyname"+owner);
        LambdaQueryWrapper<Owner> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(owner.getUserName()!= null, Owner::getUserName, owner.getUserName());
        Owner owner1 = service.getOne(queryWrapper);
        return R.success(owner1);
    }

    @GetMapping("/gitee/getSocialUser")
    public R<String> getSocialUser(){
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        String social_user_name = ops.get("social_user_name");
        System.out.println("获取redis中的user"+social_user_name);

        if (social_user_name==null){
            R.error("无第三方登录");
        }
        return R.success(social_user_name);
    }

    @GetMapping("/gitee/deleteSocialUser")
    public R<String> deleteSocialUser(){
        System.out.println("==================删除socialuser========================");
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        Boolean social_user_name = ops.getOperations().delete("social_user_name");
        Boolean social_user = ops.getOperations().delete("social_user");
        String isDeleted = ops.get("social_user_name");
        System.out.println("删除redis中的user"+social_user_name+social_user);


        if (social_user_name){
            return R.success("删除成功");
        }
        return R.error("不存在该社交用户");

    }


}

