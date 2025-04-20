package uz.nodir.file.model.dto.file.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Nodir
 * on Date 27 май, 2024
 * Java Spring Boot by Davr Coders
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileCreateRequestDTO {

    @JsonProperty("loanExtId")
    @Schema(
            name = "loanExtId",
            example = "01121158076",
            requiredMode = Schema.RequiredMode.REQUIRED,
            pattern = "\\d{9,}",
            description = "loan external id"
    )
    private String loanExtId;
    @NotBlank
    @JsonProperty("description")
    @Schema(
            name = "description",
            example = "Passport rasmi",
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "description of file"
    )
    private String description;
}
