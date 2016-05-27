package org.cucumberpeople.eclipse.colortheme.mapper;

import org.cucumberpeople.eclipse.colortheme.ColorThemeMapping;
import org.cucumberpeople.eclipse.colortheme.ColorThemeSemanticHighlightingMapping;
import org.cucumberpeople.eclipse.colortheme.ColorThemeSetting;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

/**
 * Maps color themes to preferences for Eclipse's XML, HTML and CSS editors.
 */
public class WebEditorMapper extends GenericMapper {
    @Override
    protected ColorThemeMapping createMapping(String pluginKey, String themeKey) {
        return new Mapping(pluginKey, themeKey);
    }

    @Override
    protected ColorThemeSemanticHighlightingMapping createSemanticHighlightingMapping(
            String pluginKey, String themeKey) {
        return new SemanticMapping(pluginKey, themeKey);
    }

    private class Mapping extends ColorThemeMapping {
        public Mapping(String pluginKey, String themeKey) {
            super(pluginKey, themeKey);
        }

        @Override
        public void putPreferences(IEclipsePreferences preferences,
                ColorThemeSetting setting) {
            String value = setting.getColor().asHex() + " | "
                    + defaultBackground.getHexColorOrNull() + " | "
                    + setting.isBoldEnabled() + " | "
                    + setting.isItalicEnabled() + " | "
                    + setting.isStrikethroughEnabled() + " | "
                    + setting.isUnderlineEnabled();
            preferences.put(pluginKey, value);
        }
    }

    private class SemanticMapping extends ColorThemeSemanticHighlightingMapping {
        public SemanticMapping(String pluginKey, String themeKey) {
            super(pluginKey, themeKey);
        }

        @Override
        public void putPreferences(IEclipsePreferences preferences, ColorThemeSetting setting) {
            super.putPreferences(preferences, setting);
            preferences.put(pluginKey + separator + "color", setting.getColor().asHex());
        }
    }
}
