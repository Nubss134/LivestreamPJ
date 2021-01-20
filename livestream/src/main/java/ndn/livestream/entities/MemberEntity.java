package ndn.livestream.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Date;

@Getter
@Setter
@Entity(name="member")
public class MemberEntity extends BaseEntity{
    private String username;
    @JsonIgnore
    private String password;
    private String nickName;
    private RoleEnum role;
    private String avatar;
    private Date birthday;
    private Boolean gender;
    private String phone;
    private String email;
    private Boolean isBan;
    @Transient
    private Long followNumber;
    @Transient
    private Long followingNumber;

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
