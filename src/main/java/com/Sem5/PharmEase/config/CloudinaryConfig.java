package com.Sem5.PharmEase.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "deeuhdusi",
                "api_key", "533149835432667",
                "api_secret", "ZCMnsIn4RC8sB3MWwinxegqdI0Y"
        ));
    }
}
