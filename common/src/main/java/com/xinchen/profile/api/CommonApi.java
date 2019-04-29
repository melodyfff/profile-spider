package com.xinchen.profile.api;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/29 23:30
 */
public enum CommonApi {
    /**
     * GITHUB_USER_API
     */
    GITHUB_USER_API("https://api.github.com/users/");

    private String url;

    CommonApi(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
