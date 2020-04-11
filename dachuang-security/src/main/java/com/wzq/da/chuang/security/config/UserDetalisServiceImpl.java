package com.wzq.da.chuang.security.config;

import com.wzq.da.chuang.model.pojos.user.PRange;
import com.wzq.da.chuang.model.pojos.user.Permission;
import com.wzq.da.chuang.model.pojos.user.UserInformation;
import com.wzq.da.chuang.security.service.PRangeService;
import com.wzq.da.chuang.security.service.PermissionService;
import com.wzq.da.chuang.security.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Title：Security提供的自定义用户接口
 * Description：实现security提供的认证授权接口，绑定数据库，自定义用户权限
 * @author WZQ
 * @version 1.0.0
 * @date 2020/2/16
 */
@Service
public class UserDetalisServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Resource
    private PRangeService pRangeService;

    @Resource
    private PermissionService permissionService;

    // 自定义
    // 查询用户的权限集合
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        UserInformation user = userService.getByUserId(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (user != null) {
            // 获取用户授权，根据FrId，即通过用户类型（比如管理员，普通用户之类），来得到角色权限关联表
            List<PRange> pRanges = pRangeService.selectByIdentityId(user.getIdentityId());

            // 拿到权限url、权限名
            List<Permission> permissions = permissionService.selectByList(pRanges);

            // 声明用户授权
            if (permissions!=null && permissions.size()!=0){
                permissions.forEach(permission -> {
                    if (permission != null && !StringUtils.isEmpty(permission.getPName())){
                        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getPName());
                        grantedAuthorities.add(grantedAuthority);
                    }
                });
            }

            // 由框架完成认证工作
            return new User(user.getUserId(), user.getPassword(), grantedAuthorities);
        }

        return null;
    }
}
