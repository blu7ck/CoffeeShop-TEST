package com.blu4ck.Palm_Coffee.zero.repository;

import com.blu4ck.Palm_Coffee.zero.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // İlgili yöntemleri ekleyebilirsiniz.
}
