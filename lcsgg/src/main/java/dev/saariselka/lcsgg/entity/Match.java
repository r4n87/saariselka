package dev.saariselka.lcsgg.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "match_master")
public class Match extends BaseInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long id;

    // 연관관계 매핑
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "metadata_id")
    private Metadata metadata;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "info_id")
    private Info info;

    // 연관관계 설정
    public void setMetadata(Metadata metadata) { this.metadata = metadata; }
    public void setInfo(Info info) { this.info = info; }
}