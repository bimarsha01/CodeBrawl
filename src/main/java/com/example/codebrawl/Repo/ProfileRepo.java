package com.example.codebrawl.Repo;

import com.example.codebrawl.Entity.Model.ProfileEntity;
import com.example.codebrawl.Entity.Model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepo extends JpaRepository<ProfileEntity, Long> {
    boolean existsByUser(UserEntity user);

    UserEntity user(UserEntity user);

    UserEntity getUserByUsername();
}
