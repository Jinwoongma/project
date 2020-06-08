package com.github.homework.program.repository;


import static com.github.homework.program.domain.QProgram.program;
import static com.github.homework.theme.domain.QTheme.theme;
import static com.querydsl.jpa.JPAExpressions.select;
import static java.sql.JDBCType.NULL;

import com.github.homework.program.domain.Program;
import com.github.homework.program.model.ProgramViewDto;
import com.github.homework.program.model.QProgramViewDto;
import com.querydsl.jpa.JPQLQuery;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.repository.support.PageableExecutionUtils;

public class ProgramCustomRepositoryImpl extends QuerydslRepositorySupport implements ProgramCustomRepository {

    public ProgramCustomRepositoryImpl() {
        super(Program.class);
    }

    @Override
    public Page<ProgramViewDto> findBy(Pageable pageable) {
        JPQLQuery<ProgramViewDto> query = Objects.requireNonNull(getQuerydsl())
            .applyPagination(pageable, from(program).innerJoin(theme).on(program.id.eq(theme.id))
                .select(new QProgramViewDto(
                    program.id,
                    program.name,
                    program.introduction,
                    program.introductionDetail,
                    program.region,
                    program.themes)));


        return PageableExecutionUtils.getPage(query.fetch(), pageable, query::fetchCount);
    }
}