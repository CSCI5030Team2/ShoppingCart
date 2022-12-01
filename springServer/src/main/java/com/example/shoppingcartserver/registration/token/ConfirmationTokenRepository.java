package com.example.shoppingcartserver.registration.token;

import com.example.shoppingcartserver.appuser.AppUser;
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
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    /**
     * find token
     * @param token string
     * @return ConfirmationToken object, or none
     */
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
    Optional<ConfirmationToken> findByAppUser(AppUser appUser);

    @Modifying
    @Transactional
    @Query("delete from ConfirmationToken i where i.appUser = ?1")
    void deleteByAppUser(AppUser appUser);
}