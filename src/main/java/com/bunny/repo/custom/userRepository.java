package com.bunny.repo.custom;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bunny.model.User;

@Repository
@Transactional
public interface userRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

	public Optional<User> findByEmail(String email);

}
