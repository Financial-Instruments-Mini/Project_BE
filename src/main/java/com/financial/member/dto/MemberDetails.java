package com.financial.member.dto;

import com.financial.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Setter
@Getter
@AllArgsConstructor
public class MemberDetails implements UserDetails {

    @ApiModelProperty(name = "id", dataType = "Long", example = "1")
    private Long id;
    @NotBlank
    @ApiModelProperty(name = "email", dataType = "String", example = "aaa@naver.com")
    private String email;

    public static MemberDetails of(Member member){
        return new MemberDetails(member.getId(), member.getEmail());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
