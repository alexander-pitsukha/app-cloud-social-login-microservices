package com.alexander.services.identity.repo;

import com.alexander.services.identity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email")
    User findByEmail(@Param("email") String email);

    @Query("select case when count(u) > 0 then true else false end from User u where u.email = :email")
    boolean existsByEmail(@Param("email") String email);

}
