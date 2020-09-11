package com.kyora.studio.vote.repository;


import com.kyora.studio.vote.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

    default Authority findOne(String id) {
        return findById(id).get();
    }

    default void delete(String id) {
        deleteById(id);
    }
}
