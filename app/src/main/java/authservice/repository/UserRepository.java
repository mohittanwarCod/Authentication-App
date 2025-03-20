package authservice.repository;


import authservice.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long>
{
    public UserInfo findByUsername(String username); // findByUsername from UserInfo table
}