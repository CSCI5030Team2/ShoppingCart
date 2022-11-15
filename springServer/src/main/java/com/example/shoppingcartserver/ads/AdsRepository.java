package com.example.shoppingcartserver.ads;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdsRepository extends JpaRepository<Ads, Long> {

}
