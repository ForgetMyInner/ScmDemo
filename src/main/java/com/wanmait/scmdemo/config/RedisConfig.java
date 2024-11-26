package com.wanmait.scmdemo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Configuration
//@EnableCaching //启用注解缓存 @Cachable注解添加缓存 @CacheEvict删除缓存
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        System.out.println("------------------------------------------");
        System.out.println(redisConnectionFactory.getClass().getName());
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        //取消时间转换格式，默认是时间戳，同时需要设置要表现的时间格式,java.util.Date
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));


        //默认序列化没有实现，反序列化有实现
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DATE_TIME_FORMATTER));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMATTER));

        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        javaTimeModule.addSerializer(LocalDate.class,new LocalDateSerializer(DATE_FORMATTER));
        javaTimeModule.addDeserializer(LocalDate.class,new LocalDateDeserializer(DATE_FORMATTER));

        objectMapper.registerModule(javaTimeModule);
        //设置时区
        objectMapper.setTimeZone(TimeZone.getDefault());
        //设置格式化输出，在Redis Desktop Manager中查看是格式化好的，否则是连在一起的字符串
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        //设置属性输出格式，inputTime变为input_time
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        //objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CASE); inputTime变为inputtime



        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

        //序列化时将对象全类名一起保存下来
        //objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL); 过期方法，用下面的代替
        objectMapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.WRAPPER_ARRAY);
        // java bean中对象有值为null ，就不会将这个字段或属性序列化为字符串。
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        jsonRedisSerializer.setObjectMapper(objectMapper);

        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jsonRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jsonRedisSerializer);
        return redisTemplate;
    }

    @Bean
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        //spring cache注解序列化配置
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getKeySerializer())) //key序列化方式
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer())) //value序列化方式
                .disableCachingNullValues(); //不缓存null值,被缓存的对象如果为null值会报错，如果允许缓存null值，org.springframework.cache.support.NullValue
        //.entryTtl(Duration.ofSeconds(600)); //默认缓存过期时间

        // 设置一个初始化的缓存名称set集合
        Set<String> cacheNames = new HashSet<>();
        //cacheNames.add("commonCache");
        //cacheNames.add("menuCache");

        // 对每个缓存名称应用不同的配置，自定义过期时间
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();

        //configMap.put("commonCache", redisCacheConfiguration.entryTtl(Duration.ofSeconds(1200)));
        //configMap.put("menuCache", redisCacheConfiguration.entryTtl(Duration.ofSeconds(1800)));

        RedisCacheManager redisCacheManager = RedisCacheManager.builder(redisTemplate.getConnectionFactory())
                .cacheDefaults(redisCacheConfiguration)
                .transactionAware()
                .initialCacheNames(cacheNames) // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
                .withInitialCacheConfigurations(configMap)
                .build();
        return redisCacheManager;
    }

    //在没有指定缓存Key的情况下，key生成策略，@Cacheable注解不指定key
    //全限定类名#方法名#参数1值#参数2值。。。
    //org.springframework.cache.interceptor.KeyGenerator
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append("#" + method.getName());
                for (Object obj : params) {
                    sb.append("#"+obj.getClass().getSimpleName()).append("#").append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

}
