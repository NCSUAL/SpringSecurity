package project.security_study.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "user")
public class StudyData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //기본 키 생성을 DB에 위임 (Mysql)
    private String username;
    private String password;
    private String email;
    private String role; //security 권한

    private String provider; //로그인 기관(google,naver,다음) 등등
    private String providerId; //해당 기관에서 받은 특유의 id값

    @CreationTimestamp
    private Timestamp createTime;
}
