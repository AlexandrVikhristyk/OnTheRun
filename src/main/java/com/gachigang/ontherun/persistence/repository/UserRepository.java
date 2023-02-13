package com.gachigang.ontherun.persistence.repository;

import com.gachigang.ontherun.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
