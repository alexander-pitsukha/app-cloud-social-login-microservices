package com.alexander.services.identity.repo;

import com.alexander.services.identity.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    @Query("select v from VerificationToken v where v.token = :token")
    VerificationToken findByToken(@Param("token") String token);

}
