package com.blu4ck.Palm_Coffee.zero.repository;

import com.blu4ck.Palm_Coffee.zero.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    // İlgili yöntemleri ekleyebilirsiniz.
}
