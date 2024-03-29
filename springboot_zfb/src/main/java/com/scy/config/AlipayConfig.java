package com.scy.config;

/**
 * Created by Administrator on 2020/7/31.
 */
public class AlipayConfig {

    //商户appid
    public String APPID="2021000116686515";

    //私钥pkcs8格式的
    public String RSA_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCyOb/31p7UD/RLsrO+GKrupUJ6q7w+5k1MIwOYjpmdJaL7Y8doft1xgeIaJJcs+yiCmO5jiWFXzWFYK7lrGrT2aBOJaQb3jw4oTqHkNypYvyNjXOESwFAoKPDj7N8ss7ZK032A0wMauFgXtyDSjn0taIq6NQnMeHpGKhOVfyb/3G14iwp6uoSLbTvufaHyfJGun3E702jGbXJAGuqEEaYBGVHBn22mHOvtlVfwa244+fRgSQaiGOUxe5mg9T1HU0uoV9iLTcW4VtjVwCP2HkKE9TGjs73G4C3JSXFwRsYcUoQWj1xuhtENJ6vRNMt3RVb+NG9bW3ZY3lY4uRQ4zC6lAgMBAAECggEBAJZmZ5p1MhlQ0V6kEkykMnMrYLt2e2g1HxvWHG28QOO9ZBly7U9AaA0mhzB6mlyqIlh7SAkVYOEgfzpRw6ofooCh+YKm7+svfMoE+VdGOmUj/3AVbBZP1hTd39LoDLmDep0QdkOlTmRZFnYE4xzsg7QzGYbjxT4YmU7mqRoqcDXPXAFXHK26ve8iU1fGbIW8YRYLJg8LQ8foNgorQIMUmu7bvijc2tquGTe25zAxRz+LsfPJXDRPYnjohtVoVFIVnna3Ya4jJwJKNp+Voo1T1I3pXSYQQBapcjlBfW2N9IZt5RhIO9tufJdYrV3tsZirxziKQxOW1ObhjBoK5aq7IiECgYEA5DQYzcJ+BinAI/mv0+NVQAI7HhyxEGY9zpU2X092VfoE2odrIIZmxjCpyKLJfBlW7RGWMaHGArcsN7KV9uS+7G8pqpoH0NQt4hV0hr7M9aikRn/Cogs0R0idTB/BD1+N4xu5tJ6/ScptMM2QpcH2BfdL/FPNMOdx326tQ5GAktkCgYEAx+86Y8PVlgfy5RM/xpZi/G9xQK8U2lSkRyq9r5KygzFvh49TG5VKMXfpjSHCCScuohXmmyauN9GqjwfUih4U7V2G7CUteUsn9uVFBs9oI8C0iUniBHpfw+J30Vnk3oexAlx+O+G1x/keaicucklj9jiJXV8nLLZZpwA6JsVVQq0CgYA0r96GKwBjteH5rF0vA46cL6eshR1QVvqwJn0eNj9xDD9woNFX+g/idDvO6d6w+zy5kVTm8p2/5MCdv2qmTRvThLrO+68QRNyvFRsJPSYfFaeP61FpjkKsrpQ6jh5JyVr981nF3Nz3UXgyS1O5IXYG3Ji7kjGOt/DvkCMKtZ5EyQKBgC/f8/5hrWpQmvQJiPL3ndpOoTLY+chSDkhQTr2VBhPuKYOvaToopGx2B0HZPIpOjJcZYW/zcjJBjXLUcCtmiqJQC/fN6zapLXesbLv1vD0bbt+VBn8GApp4RzKlMO2sVlF3bm7OXGX3HDZVDYUmV75kyVfHai0FuTAJGOOhywm9AoGAIrkN6Q5yU35IdYMLUd9oOu7R+H4RsKOmRBUV0cjQXE+0nHPPjUfHVGKedK3zpG4DZxmUXygtlZhNXU+eOzAh2XVrkccusWFw+SCkj7TK5qVKuNLIZ6rwoNstD6U9EL59yxcaI/GJiIzNMBiSKqUmzrehMtd0zUOisqHL19Z76VE=";

    //服务器异步通知页面路径需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public String notif_url= "http://www.baidu.com";

    public String return_url= "http://www.baidu.com";

    //请求网关地址
    public String URL = "https://openapi.alipaydev.com/gateway.do";

    //编码
    public String CHARSET = "UTF-8";

    //返回格式
    public String FORMAT= "json";

    //支付宝公钥
    public String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4jAvrKL+xJDO7p9PazXMo+H1fUXmjqHCTSeju+OJ2ow5GrbU7v3ZixF/FhD1B/bDt5Um0NCJbShBxbfrIKdsnP43g7wOYVhR8qcZ0iJEdWvZI6yO6+5mm9NsZc6WdNMuhYhm8qVyYfG/L6nb9fmZJk/nAbtD0bkz5YhW/6wk4taEJDtIFa0nz9qpN98exNzFfWmshaWvQoQAPnrfY0isg1Q7EVXFnMOQfOANIi38Axk/DV+MleW1gcTX/WmvfhChssgS+CyEEqyU9S0g9SG2Ii7yojObD9FmZcR7jYYE0g/KwSd/Xgr17h6BJtYZvVeRfexv4jKYKlBgizpBbsfsIQIDAQAB";

    // RSA2
    public String SIGNTYPE = "RSA2" ;

}
