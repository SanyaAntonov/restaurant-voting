package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    Vote getVoteByUserAndId(User user, Integer id);

    Vote getVoteByUserAndDate(User user, LocalDate date);

    List<Vote> getVoteByUserOrderByDateDesc(User user);
}
