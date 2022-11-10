package com.example.shoppingcartserver.login.token;


import com.example.shoppingcartserver.appuser.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author aiden
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class LoginToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn( nullable = false, unique = true)
    private AppUser appUser;

    /**
     * token used to verity user identity
     */
    @Column(nullable = false,unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createTime;

    @Column(nullable = false)
    private LocalDateTime expireTime;

}
