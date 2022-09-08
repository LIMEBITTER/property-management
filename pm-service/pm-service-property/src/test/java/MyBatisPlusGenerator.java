
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;

public class MyBatisPlusGenerator {

    public static void main(String[] args) {
        // 全局配置 GlobalConfig 会用到projectPath
        // E:\MAVEN\springboot-application\guli-parent\service\service_edu
        String projectPath = System.getProperty("user.dir");

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/heima-estate", "root", "123")
                // 全局配置 GlobalConfig
                .globalConfig(builder -> {
                    builder.author("zxb")                               // 设置作者名
                            .fileOverride()                              // 开启覆盖已生成文件，默认值false
                            .enableSwagger()                             // 开启 swagger 模式，默认值false
                            .dateType(DateType.ONLY_DATE)
                            .outputDir( "E:\\毕设项目---智慧物业\\property-management\\pm-service\\pm-service-property\\src\\main\\java");  // 指定输出目录
                })
                // 包配置 PackageConfig
                .packageConfig(builder -> {
                    builder.parent("cat")        // 父包名，默认值：com.baomidou
                            // .moduleName("com/IT/education")   // 父包模块名，默认值:无
                            // 上面两行代码加起来: com.IT.eduservice.xxx(entity、service、controller等）
                            .entity("entity")           // Entity包名
                            .service("service")         // Service包名
                            .controller("controller")   // Controller包名
                            .serviceImpl("serviceImpl") // ServiceImpl包名
                            .mapper("mapper")           // Mapper包名，生成的xxxMapper接口所在的包名
                            // 路径配置信息，设置mapperXml配置文件的生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,"E:\\毕设项目---智慧物业\\property-management\\pm-service\\pm-service-community\\src\\main\\resources\\mapper"));
                })
                // 配置策略 StrategyConfig
                .strategyConfig(builder -> {
                    builder.addInclude("tb_activity","tb_complaint")                   // 增加表匹配，需要映射的数据库表的表名
                            .addTablePrefix("tb_")                       // 增加过滤表前缀，生成时将数据库表的前缀"p_"去掉
                            // 1.实体策略配置
                            .entityBuilder()
                            .naming(NamingStrategy.underline_to_camel)  // 数据库表映射到实体的命名策略，下划线转驼峰命名
                            .enableLombok()                             // 开启 lombok 模型
                            .logicDeleteColumnName("is_deleted")        // ⚡逻辑删除字段名(数据库)，与下一条结合使用
                            .logicDeletePropertyName("isDeleted")       // ⚡逻辑删除属性名(实体)，添加后会在对应属性上加上@TableLogic注解
                            //.enableTableFieldAnnotation()             // 开启生成实体时生成字段注解，意思就是会将数据库表中对应的字段也标注在上面
                            .idType(IdType.ASSIGN_ID)                   // 全局主键类型，配置后会在对应字段上生成 @TableId(value = "id", type = IdType.ASSIGN_ID)
                            // 2.service策略配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService")         // 格式化 service 接口文件名称，例如：User类——UserService
                            .formatServiceImplFileName("%sServiceImpl") // 格式化 service 实现类文件名称，例如：User类——UserServiceImpl
                            // 3.controller策略配置
                            .controllerBuilder()
                            .formatFileName("%sController")             // 格式化文件名称，例如：User类——UserController
                            .enableRestStyle()                          // 开启生成@RestController 控制器，添加后会在UserController类上添加@RestController注解
                            // 4.mapper策略配置
                            .mapperBuilder()
                            .superClass(BaseMapper.class)       // 设置父类，继承BaseMapper基本类后，所有基本的CRUD操作均已编写完成，不需要再像mybatis一样编写映射文件
                            .enableMapperAnnotation()           // 开启 @Mapper 注解
                            .formatMapperFileName("%sMapper")   // 格式化 mapper 文件名称
                            .formatXmlFileName("%sMapper");     // 格式化 xml 实现类文件名称
                })
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }

}
