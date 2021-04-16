package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Query("select v from Vote v WHERE v.user.id=:userId and v.id=:voteId")
    Optional<Vote> getVoteById(@Param("userId") int userId, @Param("voteId") int voteId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.date=:date")
    Optional<Vote> getUserVoteByDate(@Param("userId") int userId, @Param("date") LocalDate date);

    @Query("SELECT v from Vote v WHERE v.user.id=:userId ORDER BY v.date DESC")
    Optional<List<Vote>> getVotingHistory(@Param("userId") int userId);
}
