package com.example.week7.Repo;

import com.example.week7.dto.UserDTO;
import com.example.week7.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByEmailAndPassword(String email,String password);
    @Query(value = "select * from users u where u.email=?1",
    nativeQuery = true)
    Optional<User> findUserByEmail(String email);
}
