package project.security_study.config.auth;


import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import project.security_study.model.StudyData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Data
public class StudyDetails implements UserDetails, OAuth2User {

    //데이터를 주입 받아야함
    private StudyData studyData;

    public StudyDetails(StudyData studyData) {
        this.studyData = studyData;
    }


    //OAuth2User
    @Override
    public Map<String, Object> getAttributes() {
        return Map.of();
    }

    @Override
    public String getName() {
        return "";
    }


    //사용자 role을 가져옴
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return studyData.getRole();
            }
        });
        return roles;
    }

    @Override
    public String getPassword() {
        return studyData.getPassword();
    }

    @Override
    public String getUsername() {
        return studyData.getUsername();
    }

    //계정이 안만료됨?
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정 안잠김?
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호가 일정시간이 안지남?
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화 됨?
    @Override
    public boolean isEnabled() {
        return true;
    }

}
