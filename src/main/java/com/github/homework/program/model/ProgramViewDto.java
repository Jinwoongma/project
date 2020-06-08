package com.github.homework.program.model;

import com.github.homework.program.domain.Program;
import com.github.homework.theme.domain.Theme;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class ProgramViewDto {

    private Long id;
    private String name;
    private String introduction;
    private String introductionDetail;
    private String region;
    private String themes;

    @QueryProjection
    public ProgramViewDto(Long id, String name, String introduction, String introductionDetail, String region,
          List<Theme> themes) {
        this.id = id;
        this.name = name;
        this.introduction = introduction;
        this.introductionDetail = introductionDetail;
        this.region = region;
        this.themes = themes.stream().map(Theme::getName).collect(Collectors.joining(" "));
    }
}
