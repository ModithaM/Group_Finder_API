package com.moditha.group_finder.repository;

import com.moditha.group_finder.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JpaRepository provides data sorting and Pagination. CrudRepository doesn't
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
