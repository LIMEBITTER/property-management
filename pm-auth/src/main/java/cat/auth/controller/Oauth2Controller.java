package cat.auth.controller;

import cat.auth.client.OwnerClient;

import cat.dto.OwnerRoleDto;
import cat.vo.SocialUser;
import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.HttpCookie;

/**
 * 处理社交登录请求
 *
 */
@Slf4j
@Controller
@RequestMapping("/auth")
public class Oauth2Controller {
    @Autowired
    private OwnerClient ownerClient;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/oauth2/gitee/success")
    public String weibo(@RequestParam("code") String code) throws Exception {
        String url = "https://gitee.com/oauth/token?grant_type=authorization_code&code="+code+"&client_id=068b619f6c8711924ed0494bbf936cd5c8ed8b87999b2bffa614d9ec15298f68&redirect_uri=http://localhost:9444/auth/oauth2/gitee/success&client_secret=366bb54937ec6d729d2dc2048dc2c71a2182818c3d60b15f4893f32733a4811b";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        HttpResponse response = httpClient.execute(httpPost);
//        //2、处理
        if (response.getStatusLine().getStatusCode()==200){
            //获取到了accessToken
            String json = EntityUtils.toString(response.getEntity());

            SocialUser socialUser = JSON.parseObject(json, SocialUser.class);
            String access_token = socialUser.getAccess_token();
            //通过获取到的access_token查询user信息
            String url2 = "https://gitee.com/api/v5/user?access_token="+access_token;
            System.out.println("====access_token"+access_token+url2);

            HttpGet httpPost2 = new HttpGet(url2);
            HttpResponse response2 = httpClient.execute(httpPost2);
            //获取到了userinfo
            String json2 = EntityUtils.toString(response2.getEntity());
            OwnerRoleDto ownerRoleDto = JSON.parseObject(json2, OwnerRoleDto.class);
            String name = ownerRoleDto.getName();


            System.out.println("====用户信息==="+ownerRoleDto);
            //进行登录或者注册
            ownerClient.authLogin(ownerRoleDto);
            //将数据信息存入redis中
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            ops.set("social_user", ownerRoleDto.toString());
            ops.set("social_user_name", name);

            return "redirect:http://localhost:8080/#/pages/my/my";
        }
            return "redirect:http://localhost:8080/#/pages/owner-login/owner-login";



    }
    @GetMapping("/aaa")
    public String aaa(HttpSession session){
        Object myapp = session.getAttribute("myapp");

        System.out.println("====存储sessio3434n==="+myapp);
        return "3434";
    }


}
