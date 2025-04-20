package uz.nodir.file.config;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uz.nodir.file.model.property.MiniOProperty;

/**
 * Created by Nodir
 * on Date 01 май, 2024
 * Java Spring Boot by Davr Coders
 */
@Configuration
@RequiredArgsConstructor
public class MinioConfig {
    private final MiniOProperty property;

    @Bean
    public MinioClient bucketConfig() {
        return
                MinioClient.builder()
                        .endpoint(property.getUrl())
                        .credentials(property.getAccessKey(), property.getSecretKey())
                        .build();
    }
}
