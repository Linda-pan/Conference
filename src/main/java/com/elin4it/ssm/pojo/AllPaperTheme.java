package com.elin4it.ssm.pojo;

public class AllPaperTheme {
    private Integer themeId;

    private String theme;

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme == null ? null : theme.trim();
    }

    @Override
    public String toString() {
        return "AllPaperTheme{" +
                "themeId=" + themeId +
                ", theme='" + theme + '\'' +
                '}';
    }
}