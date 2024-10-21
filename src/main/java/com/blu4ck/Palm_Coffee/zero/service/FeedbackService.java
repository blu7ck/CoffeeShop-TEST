package com.blu4ck.Palm_Coffee.zero.service;

import com.blu4ck.Palm_Coffee.zero.model.Feedback;
import com.blu4ck.Palm_Coffee.zero.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    // Diğer metodlar
}
