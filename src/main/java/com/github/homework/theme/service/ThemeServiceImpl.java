package com.github.homework.theme.service;

import com.github.homework.theme.domain.Theme;
import com.github.homework.theme.parser.ThemeParser;
import com.github.homework.theme.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    @Override
    @Transactional
    public Theme getOrSaveTheme(String themeName) {
        return this.themeRepository
            .findByName(themeName)
            .orElseGet(() -> this.themeRepository.save(new Theme(themeName)));
    }

    @Transactional
    public List<Theme> saveThemes(String themeName) {
        List<String> themes = ThemeParser.parse(themeName);
        return themes.stream()
                .map(this::getOrSaveTheme)
                .collect(Collectors.toList());
    }
}
