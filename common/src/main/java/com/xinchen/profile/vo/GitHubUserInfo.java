package com.xinchen.profile.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author Xin Chen (xinchenmelody@gmail.com)
 * @version 1.0
 * @date Created In 2019/4/29 23:21
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubUserInfo {
    private String name;
    private String blog;
}
