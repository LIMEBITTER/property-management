package cat.controller;

import cat.FaceManage;
import cat.constant.FaceConstant;
import cat.constant.ImageTypeEnum;
import cat.dto.FaceResult;
import cat.dto.FaceUserDTO;
import cat.dto.ImageU;


import cat.dto.Face;
import cat.entity.OwnerRole;
import cat.service.OwnerService;
import cat.utils.Base64DecodeMultipartFile;
import cat.utils.BizException;
import cat.utils.FaceUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import result.R;

import java.io.IOException;

@RestController
@RequestMapping("/owner/ownerFace")
public class OwnerFaceController {

    @Autowired
    OwnerService service;

    /**
     * 人脸开门
     */
    @PostMapping("/faceOpenDoor")
    public R<String> faceOpenDoor(@RequestBody Face face) throws Exception {
//        OwnerFace ownerFace = new OwnerFace();
        String file = face.getFile();
        String groupId = face.getGroupId();
        /**
         * base64转为multipartFile
         */
        MultipartFile multipartFile = Base64DecodeMultipartFile.base64Convert(file);//很长
        if (multipartFile.isEmpty()) {
            throw new BizException("上传文件不能为空");
        }
        String groupIds = "owner";
        String data = FaceUtil.encodeBase64(multipartFile.getBytes());
        ImageU imageU = ImageU.builder().data(data).imageTypeEnum(ImageTypeEnum.BASE64).build();
        //在百度人脸库中查询groupid对应的image并对比
        FaceResult result = FaceManage.faceSearch(groupIds, imageU);

        //返回user对象，是否存在该人脸
        String users = result.getData().getString(FaceConstant.USER_LIST);
        System.out.println("存在次user"+users);
        if (StringUtils.isEmpty(users)){
            return R.error("用户不存在");
        }

        JSONArray array = JSONObject.parseArray(users);
        JSONObject object = JSONObject.parseObject(array.get(0).toString());
        Integer score = object.getInteger(FaceConstant.SCORE);
        //返回权重分数
        if (score == null){
            return R.error("识别失败");
        }
        //如果大于比对分数，则人脸识别成功，处理接下来的对应逻辑
        if (score >= FaceConstant.MATCH_SCORE){
            System.out.println(result.getData().toString());
            Long user_id = object.getLong("user_id");
            System.out.println("此时比对成功的userid"+user_id);
            return R.success(result.getData().toString());

        }
        return R.error("用户不存在");
    }

    //人脸注册
    @PostMapping("/faceRegister")
    public R<String> faceRegister(@RequestBody Face face) throws IOException {
        MultipartFile multipartFile = Base64DecodeMultipartFile.base64Convert(face.getFile());//很长
        if (multipartFile.isEmpty()) {
            R.error("上传文件不能为空");
        }
        String data = FaceUtil.encodeBase64(multipartFile.getBytes());
        ImageU imageU = ImageU.builder().data(data).imageTypeEnum(ImageTypeEnum.BASE64).build();

        //人脸注册
        FaceUserDTO<Object> userDTO = new FaceUserDTO<>();
        userDTO.setGroupId(face.getGroupId());
        userDTO.setUserId(face.getId().toString());
        userDTO.setUser(face.getUserName());
        FaceManage.faceRegister(userDTO, imageU);
        return R.success("注册成功");



    }
}
