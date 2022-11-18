package com.example.shoppingcartserver.password.token;

import com.example.shoppingcartserver.appuser.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author vivek
 */

@Data
@NoArgsConstructor
@Entity
public class ConfirmationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createTime;

    @Column(nullable = false)
    private LocalDateTime expireTime;

    private LocalDateTime confirmTime;

    @OneToOne
    @JoinColumn(nullable = false, unique = true)
    private AppUser appUser;


    public ConfirmationToken(String token, LocalDateTime createTime, LocalDateTime expireTime, AppUser appUser) {
        this.token = token;
        this.createTime = createTime;
        this.expireTime = expireTime;
        this.appUser = appUser;
    }
}
