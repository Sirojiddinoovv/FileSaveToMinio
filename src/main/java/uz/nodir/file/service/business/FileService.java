package uz.nodir.file.service.business;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;
import uz.nodir.file.model.dto.core.response.ResultData;
import uz.nodir.file.model.dto.file.request.FileCreateRequestDTO;
import uz.nodir.file.model.dto.file.response.FileBaseResponseDTO;
import uz.nodir.file.model.dto.file.response.FileDownloadResponseDTO;

import java.util.List;

/**
 * Created by Nodir
 * on Date 27 май, 2024
 * Java Spring Boot by Davr Coders
 */
public interface FileService {

    ResultData<FileBaseResponseDTO> save(@NotNull MultipartFile file,
                                         @NotNull FileCreateRequestDTO requestDTO);

    ResultData<List<FileBaseResponseDTO>> findFileListByLoan(@NotNull String loanExtId);

    ResultData<FileDownloadResponseDTO> getFileById(@NotNull Long id);

    ResultData<FileBaseResponseDTO> deleteFileById(@NotNull Long id);
}
