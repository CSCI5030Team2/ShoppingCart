package com.example.shoppingcartserver.password.token;

import com.example.shoppingcartserver.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author vivek
 */

@Repository
public interface ForgotTokenRepository extends JpaRepository<ForgotToken, Long> {

    Optional<ForgotToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ForgotToken f " +
            "SET f.sentTime = ?2 " +
            "WHERE f.token = ?1")
    void updateConfirmedAt(String token,
                           LocalDateTime confirmTime);
}
