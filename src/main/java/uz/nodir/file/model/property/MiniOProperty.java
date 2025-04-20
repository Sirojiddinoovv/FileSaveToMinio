package uz.nodir.file.model.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Nodir
 * on Date 27 май, 2024
 * Java Spring Boot by Davr Coders
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "external.minio")
public class MiniOProperty {
    private String url;
    private String accessKey;
    private String secretKey;
    private String bucket;
}
