package com.wax.blogsystem.config.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wax.blogsystem.mapper.PermMapper;
import com.wax.blogsystem.mapper.RoleMapper;
import com.wax.blogsystem.domain.Perm;
import com.wax.blogsystem.domain.Role;
import com.wax.blogsystem.domain.User;
import com.wax.blogsystem.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private PermMapper permMapper;

    @Autowired
    private RoleMapper roleMapper;


    //权限信息，包括角色以及权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //如果身份认证的时候没有传入User对象，这里只能取到userName
        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
        User user  = (User)principals.getPrimaryPrincipal();

        authorizationInfo.addRole(user.getRoleCode());

        Role role = roleMapper.selectByCode(user.getRoleCode());

        List<Perm> perms =  permMapper.selectByRoleId(role.getId());
        List<String> permCodes = new ArrayList<String>();
        for(Perm perm : perms){
            permCodes.add(perm.getCode());
        }
        authorizationInfo.addStringPermissions(permCodes);

        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken)token;
        //获取用户的输入的账号,密码.
        String userName = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        System.out.println(usernamePasswordToken.getUsername());
        //通过username从数据库中查找 User对象.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userName);
        User user = userService.selectOne(queryWrapper);
        System.out.println("----->>user="+user);
        if(user == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                user.getPassword(), //密码
                getName()  //realm name
        );
        return authenticationInfo;
    }

}