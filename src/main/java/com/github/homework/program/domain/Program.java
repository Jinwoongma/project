package com.github.homework.program.domain;


import com.github.homework.theme.domain.Theme;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
    name = "program_seq_generator",
    sequenceName = "program_seq", allocationSize = 10)
@EqualsAndHashCode(of = "id")
@ToString
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = "program_seq_generator")
    private Long id;
    private String name;
    private String introduction;
    private String region;
    @Lob
    private String introductionDetail;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "program_theme",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "theme_id"))
    private List<Theme> themes;

    @Builder
    public Program(String name, String introduction, String introductionDetail, String region, List<Theme> themes) {
        this.name = name;
        this.introduction = introduction;
        this.introductionDetail = introductionDetail;
        this.region = region;
        this.themes = themes;
    }

    public void updateProgram(String name, String introduction, String introductionDetail, String region, List<Theme> themes) {
        this.name = name;
        this.introduction = introduction;
        this.introductionDetail = introductionDetail;
        this.region = region;
        this.themes = themes;
    }

}
