package com.github.homework.theme.service;

import com.github.homework.theme.domain.Theme;

import java.util.List;

public interface ThemeService {
    Theme getOrSaveTheme(String themeName);
    List<Theme> saveThemes(String themeName);
}
