package uz.nodir.file.service.business.impl;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.nodir.file.exception.InvalidDataException;
import uz.nodir.file.model.dto.core.response.ResultData;
import uz.nodir.file.model.dto.file.request.FileCreateRequestDTO;
import uz.nodir.file.model.dto.file.response.FileBaseResponseDTO;
import uz.nodir.file.model.dto.file.response.FileDownloadResponseDTO;
import uz.nodir.file.model.entity.FileSaver;
import uz.nodir.file.model.property.FileProperty;
import uz.nodir.file.service.business.FileService;
import uz.nodir.file.service.core.FileSaverDAO;
import uz.nodir.file.service.business.FileSenderService;

import java.util.List;
import java.util.UUID;

/**
 * Created by Nodir
 * on Date 27 май, 2024
 * Java Spring Boot by Davr Coders
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileSenderService fileSenderService;
    private final FileSaverDAO fileSaverDAO;
    private final FileProperty fileProperty;

    @Override
    public ResultData<FileBaseResponseDTO> save(@NotNull MultipartFile file,
                                                @NotNull FileCreateRequestDTO requestDTO) {
        log.info("Received file : {} to save loan: {}", file.getOriginalFilename(), requestDTO.getLoanExtId());

        validateRequest(file, requestDTO);

        var fileName = fileNameBuilder(file.getOriginalFilename(), requestDTO.getLoanExtId());

        fileSenderService.save(
                file,
                fileName
        );

        var preparedFile = new FileSaver(
                requestDTO.getLoanExtId(),
                fileName,
                file.getContentType(),
                requestDTO.getDescription()
        );

        var savedFile = fileSaverDAO.save(preparedFile);


        return ResultData.ok(
                new FileBaseResponseDTO(
                        savedFile
                )
        );
    }

    private void validateRequest(MultipartFile file, FileCreateRequestDTO requestDTO) {
        checkUploadFilesSize(file);

        checkUploadFilesCount(requestDTO);
    }

    private void checkUploadFilesSize(MultipartFile file) {
        if (file.getSize() / 1_000_000D > fileProperty.getMaxFileSizeInMB())
            throw new InvalidDataException("File: %s size: %f more than waiting: %d"
                    .formatted(file.getOriginalFilename(), file.getSize() / 1_000_000D, fileProperty.getMaxFileSizeInMB()));
    }

    private void checkUploadFilesCount(FileCreateRequestDTO requestDTO) {
        var filesList = fileSaverDAO.findFilesByLoanExtId(requestDTO.getLoanExtId());

        if (filesList.size() == fileProperty.getMaxCountForOneLoan() || filesList.size() > fileProperty.getMaxCountForOneLoan())
            throw new InvalidDataException("You cannot upload more than 10 files. Loan: %s".formatted(requestDTO.getLoanExtId()));
    }


    private static String fileNameBuilder(String fileOriginalName, String loanExtId) {
        var preparedFileName = loanExtId + UUID.randomUUID() + fileOriginalName;
        log.info("Prepared filename for bucket: {}", preparedFileName);

        return preparedFileName;
    }

    @Override
    public ResultData<List<FileBaseResponseDTO>> findFileListByLoan(@NotNull String loanExtId) {
        var files = fileSaverDAO.findFilesByLoanExtId(loanExtId);

        return ResultData.ok(
                files
                        .stream()
                        .map(FileBaseResponseDTO::new)
                        .toList()
        );
    }

    @Override
    public ResultData<FileDownloadResponseDTO> getFileById(@NotNull Long id) {
        var fileSaver = fileSaverDAO.findById(id);
        log.info("Download file : {}", fileSaver);

        InputStreamResource file = fileSenderService.getByName(fileSaver.getName());
        return ResultData.ok(
                new FileDownloadResponseDTO(
                        file,
                        fileSaver.getContentType(),
                        fileSaver.getName()
                )
        );
    }

    @Override
    public ResultData<FileBaseResponseDTO> deleteFileById(@NotNull Long id) {
        log.info("Received command to delete file by id: {}", id);
        var file = fileSaverDAO.findById(id);

        fileSenderService.deleteByName(file.getName());

        var response = ResultData.ok(
                new FileBaseResponseDTO(file)
        );

        log.info("Finished command to delete file by id with response: {}", response);
        return response;
    }

}
