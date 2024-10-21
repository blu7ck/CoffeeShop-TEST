package com.blu4ck.Palm_Coffee.zero.service;

import com.blu4ck.Palm_Coffee.zero.model.Vote;
import com.blu4ck.Palm_Coffee.zero.repository.VoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote save(Vote vote) {
        return voteRepository.save(vote);
    }

    // Diğer metodlar
}
