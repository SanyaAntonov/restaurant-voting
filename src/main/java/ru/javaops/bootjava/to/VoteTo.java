package ru.javaops.bootjava.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.javaops.bootjava.model.Vote;
@Getter
@Setter
@AllArgsConstructor
@ToString
public class VoteTo {
    private Vote vote;
    private boolean created;
}
