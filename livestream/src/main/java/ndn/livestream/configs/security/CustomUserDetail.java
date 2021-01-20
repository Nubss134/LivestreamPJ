package ndn.livestream.configs.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetail implements UserDetails {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String avatar;
    private String token;
    private String role;
    private String nickName;


    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetail(Long id, String username, String password, String avatar, String role, String nickName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.role = role;
        this.nickName = nickName;

        List<GrantedAuthority> listAuthority = new ArrayList<>();
        listAuthority.add(new SimpleGrantedAuthority(role));
        this.authorities = listAuthority;

    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return this.nickName;
    }
    public void setRole(String role) {
        this.role = role;
    }

    private String getRole() {
        System.err.println(role);
        return role;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getAvatar() {
        return avatar;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
}
