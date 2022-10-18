package dev.saariselka.lcsgg.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Metadata extends BaseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "metadata_id")
    private Long id;

    private String dataVersion;
    private String matchId;

    // 연관관계 매핑
    @OneToMany(mappedBy = "metadata")
    private List<Participant> participantList = new ArrayList<>();

    // 연관관계 설정
    public void setParticipantList(List<Participant> participantList) { this.participantList = participantList; }
}