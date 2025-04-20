package uz.nodir.file.model.dto.file.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.InputStreamResource;

/**
 * Created by Nodir
 * on Date 27 май, 2024
 * Java Spring Boot by Davr Coders
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDownloadResponseDTO {
    private InputStreamResource inputStreamResource;
    private String contentType;
    private String fileName;
}
