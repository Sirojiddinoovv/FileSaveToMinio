package uz.nodir.file.model.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Nodir
 * on Date 31 май, 2024
 * Java Spring Boot by Davr Coders
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "internal.file")
public class FileProperty {
    private Integer maxCountForOneLoan;
    private Integer maxFileSizeInMB;
}
