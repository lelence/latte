package com.maogogo.latte.portal.admin.service;

import com.maogogo.latte.portal.admin.model.SysUser;
import com.maogogo.latte.portal.admin.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUser = sysUserMapper.findUserByName(username);

        if (sysUser != null) {

            System.out.println("===============================");
            System.out.println(sysUser.getId());
            System.out.println(sysUser.getUsername());
            System.out.println(sysUser.getPassword());
            System.out.println("===============================");
            // List<Role> roles = roleMapper.getRolesByUserId( user.getId() );
            // user.setAuthorities( roles );
        } else {
            System.out.println("user is null");
        }

        String password = passwordEncoder.encode("123456");

        User user = new User(username, password,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        return user;
    }

}
