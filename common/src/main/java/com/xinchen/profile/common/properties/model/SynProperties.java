package com.xinchen.profile.common.properties.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author xinchen
 * @version 1.0
 * @date 04/06/2019 13:41
 */
@Getter
@Setter
public class SynProperties implements Serializable {

    private static final long serialVersionUID = 1581224643537249608L;

    private Info info = new Info();

    @Getter
    @Setter
    public class Info{
        private String url;
        private String param;
    }
}
