package com.metacritic.repositories;

import com.metacritic.domain.Comment;
import com.metacritic.domain.Reaction;
import com.metacritic.domain.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReactionRepository extends CrudRepository<Reaction,Long> {
    List<Reaction> findReactionByReview(Review review);
    List<Reaction> findReactionByComment(Comment comment);

}
