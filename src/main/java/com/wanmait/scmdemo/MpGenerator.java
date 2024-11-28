package com.wanmait.scmdemo;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MpGenerator {
    public static void main(String[] args) throws InterruptedException {
        String url = "jdbc:mysql://localhost:3306/scm_db?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
        String projectPath = System.getProperty("user.dir");
        String outputDir = projectPath + "/src/main/java";
        String mapperxmlPath = projectPath+"/src/main/resources/mapper/";
        FastAutoGenerator.create(url, "root", "wanmait")
                .globalConfig(builder -> {
                    builder.author("wanmait") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            //.fileOverride() // 覆盖已生成文件
                            .dateType(DateType.ONLY_DATE)//生成的日期类型为java.util.Date，如果不指定默认为java.time.LocalDateTime
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.wanmait.scmdemo") // 设置父包名
                            .mapper("mapper")//mapper接口所在的包，默认mapper
                            .service("service")//service接口所在的包，默认service
                            .serviceImpl("service.impl")//service实现类所在的包，默认service.impl
                            .controller("controller")//controller接口所在的包，默认controller
                            .entity("entity")//实体类所在的包，默认entity
                            //.moduleName("userinfo") // 设置父包模块名 在父包下加userinfo子包，然后mapper service controller等都生成在userinfo下面
                            .pathInfo(Collections.singletonMap(OutputFile.xml, mapperxmlPath)); // 设置mapperXml生成路径
                })

                .strategyConfig(builder -> {
                    builder.entityBuilder()
                    .disableSerialVersionUID()
                    .enableChainModel()
                    .enableLombok()//生成的实体类加了@Setter @Getter注解，没有加@ToString注解
                    //.logicDeletePropertyName("enable")//enable列上直接加上@TableLogic注解
                    .serviceBuilder()
                    .formatServiceFileName("%sService")
                    .formatServiceImplFileName("%sServiceImpl")
                    .mapperBuilder()
                    .enableBaseResultMap()
                    .enableBaseColumnList()
                    .build();
                    //不设置下面两项则生成全部的表
                    //builder.addInclude("log"); // 设置需要生成的表名
                    //builder.addExclude("province");//除了admin之外其它的都生成
                    //builder.addInclude("question","userinfo","question_type");
                    //builder.addInclude("login_log");
                    //builder.addInclude("province");
                    //builder.addInclude("question","question_type");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}