package com.example.shoppingcartserver.login;

import com.example.shoppingcartserver.login.token.LoginToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author aiden
 */
@Repository
public interface LoginRepository extends JpaRepository<LoginToken,Long> {


    /**
     * @param token  Login token String
     * @return Login token object
     */
    Optional<LoginToken> findByToken(String token);

    /**
     * Update expire time of login token to new time
     * @param token login token
     * @param newExpireTime LocalDateTime ideally 1 hour later than now
     */
    @Transactional
    @Modifying
    @Query("UPDATE LoginToken t SET t.expireTime = ?2 WHERE t.token = ?1")
    void updateExpireTime(String token, LocalDateTime newExpireTime);


    Optional<LoginToken> findByApp_user_id(Long appUserId);
}
