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
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationToken c " +
            "SET c.confirmTime = ?2 " +
            "WHERE c.token = ?1")
    void updateConfirmedAt(String token,
                           LocalDateTime confirmTime);

    @Transactional
    @Query("select i from ConfirmationToken i where i.appUser = ?1")
    Optional<ConfirmationToken> findByUser(AppUser appUser);

    @Transactional
    @Modifying
    @Query("delete from ConfirmationToken i where i.appUser = ?1")
    void deleteByAppUser(AppUser appUser);
}
