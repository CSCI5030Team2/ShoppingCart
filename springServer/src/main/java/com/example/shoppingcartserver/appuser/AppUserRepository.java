package com.example.shoppingcartserver.appuser;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author aiden
 */

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    /**
     * find all AppUser with the given email address
     * @param email string
     * @return list of AppUser with that email or none
     */
    Optional<AppUser> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("delete from AppUser i where i.email = ?1")
    void deleteByEmail(String email);
}
