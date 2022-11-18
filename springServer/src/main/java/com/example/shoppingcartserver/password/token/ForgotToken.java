package com.example.shoppingcartserver.password.token;

import com.example.shoppingcartserver.appuser.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author vivek
 */

@Data
@NoArgsConstructor
@Entity
public class ForgotToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createTime;

    @Column(nullable = false)
    private LocalDateTime expireTime;

    private LocalDateTime sentTime;

    @OneToOne
    @JoinColumn(nullable = false, unique = true)
    private AppUser appUser;


    public ForgotToken(String token, LocalDateTime sentTime, LocalDateTime expireTime, AppUser appUser) {
        this.token = token;
        this.sentTime = sentTime;
        this.expireTime = expireTime;
        this.appUser = appUser;
    }
}
