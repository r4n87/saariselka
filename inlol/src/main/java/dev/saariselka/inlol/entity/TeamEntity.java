package dev.saariselka.inlol.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="RIOT_MATCH_TEAM")
public class TeamEntity {
    @EmbeddedId
    private TeamId teamId;

    private boolean win;
    private Timestamp rrt;
}
