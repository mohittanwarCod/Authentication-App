package authservice.repository;

import authservice.entities.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Integer> { // RefreshToken we are taking from database jiski id hogi integer
    // to not write SQL queries we provide create with jpa
    // Spring with jpa automatically write sql queries for us
    Optional<RefreshToken> findByToken(String token);



}
