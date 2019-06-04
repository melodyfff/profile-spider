package com.xinchen.profile.common.properties;

import com.xinchen.profile.common.properties.model.SynProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;

/**
 * @author xinchen
 * @version 1.0
 * @date 04/06/2019 13:37
 */
@ConfigurationProperties("spider")
@Getter
@Setter
public class SpiderConfigurationProperties implements Serializable {
    private static final long serialVersionUID = -4017270137669779850L;

    @NestedConfigurationProperty
    private SynProperties syn = new SynProperties();
}
