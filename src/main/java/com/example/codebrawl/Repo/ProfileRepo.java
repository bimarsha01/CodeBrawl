package com.example.codebrawl.Repo;

import com.example.codebrawl.Entity.Model.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepo extends JpaRepository<ProfileEntity, Long> {
}
