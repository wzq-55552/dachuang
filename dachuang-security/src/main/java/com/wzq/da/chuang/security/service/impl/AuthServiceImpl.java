package com.wzq.da.chuang.security.service.impl;

import com.wzq.da.chuang.security.dto.AuthToken;
import com.wzq.da.chuang.security.dto.LoginDto;
import com.wzq.da.chuang.security.service.AuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    //@Resource
    //private LoadBalancerClient loadBalancerClient;

    @Resource
    private RestTemplate restTemplate;

    private static final String GRAND_TYPE1 = "password";//授权模式：密码模式
    private static final String GRAND_TYPE2 = "refresh_token";//授权模式 刷新令牌

    /**
     * 授权认证方法
     * @param loginDto
     * @param clientId
     * @param clientSecret
     * @return
     */
    @Override
    public AuthToken login(LoginDto loginDto, String clientId, String clientSecret) {
        //申请令牌
        return applyToken(GRAND_TYPE1,null, loginDto.getUserID(),loginDto.getUserPsw(),clientId, clientSecret);
    }

    //刷新令牌
    @Override
    public AuthToken refreshToken(String refresh_token,String clientId, String clientSecret) {
        return applyToken(GRAND_TYPE2,refresh_token,null,null,clientId, clientSecret);
    }


    /****
     * 认证方法，这里是密码模式和刷新令牌
     * @param type：类型
     * @param refresh_token：刷新token
     * @param username:用户登录名字
     * @param password：用户密码
     * @param clientId：配置文件中的客户端ID
     * @param clientSecret：配置文件中的秘钥
     * @return
     */
    private AuthToken applyToken(String type, String refresh_token, String username, String password, String clientId, String clientSecret) {
        //选中认证服务的地址
//        ServiceInstance serviceInstance = loadBalancerClient.choose("security-oauth2");
//        if (serviceInstance == null) {
//            throw new RuntimeException("找不到对应的服务");
//        }
        //获取令牌的url
        //String path = serviceInstance.getUri().toString() + "/oauth/token";
        String path = "http://localhost:9001/oauth/token";
        //定义body
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        //授权方式
        formData.add("grant_type", type);
        if (type.equals(GRAND_TYPE1)){
            //账号
            formData.add("username", username);
            //密码
            formData.add("password", password);
        }else {
            formData.add("refresh_token", refresh_token);
        }
        //定义头
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        header.add("Authorization", httpbasic(clientId, clientSecret));
        //指定 restTemplate当遇到400或401响应时候也不要抛出异常，也要正常返回值
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                //当响应的值为400或401时候也要正常响应，不要抛出异常
                if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });
        Map map = null;
        try {
            //http请求spring security的申请令牌接口
            ResponseEntity<Map> mapResponseEntity = restTemplate.exchange(path, HttpMethod.POST,new HttpEntity<MultiValueMap<String, String>>(formData, header), Map.class);
            //获取响应数据
            map = mapResponseEntity.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException(e);
        }
        if(map == null || map.get("access_token") == null || map.get("refresh_token") == null || map.get("jti") == null) {
            //jti是jwt令牌的唯一标识作为用户身份令牌
            throw new RuntimeException("获取令牌失败！");
        }

        //将响应数据封装成AuthToken对象
        AuthToken authToken = new AuthToken();
        //访问令牌(jwt)
        String accessToken = (String) map.get("access_token");
        //刷新令牌(jwt)
        String refreshToken = (String) map.get("refresh_token");
        //jti，作为用户的身份标识
        String jwtToken= (String) map.get("jti");
        authToken.setJti(jwtToken);
        authToken.setAccessToken(accessToken);
        authToken.setRefreshToken(refreshToken);
        return authToken;
    }


    /***
     * base64编码
     * @param clientId
     * @param clientSecret
     * @return
     */
    private String httpbasic(String clientId,String clientSecret){
        //将客户端id和客户端密码拼接，按“客户端id:客户端密码”
        String string = clientId+":"+clientSecret;
        //进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic " + new String(encode);
    }
}
