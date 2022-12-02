package com.example.shoppingcartserver.registration.token;

import com.example.shoppingcartserver.appuser.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Data entity for ConfirmationToken entry
 * @author aiden
 */
@Getter
@Setter
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
    @JoinColumn( nullable = false, unique = true)
    private AppUser appUser;

    public ConfirmationToken(String token, LocalDateTime createTime, LocalDateTime expireTime, AppUser appUser) {
        this.token = token;
        this.createTime = createTime;
        this.expireTime = expireTime;
        this.appUser = appUser;
    }
}
