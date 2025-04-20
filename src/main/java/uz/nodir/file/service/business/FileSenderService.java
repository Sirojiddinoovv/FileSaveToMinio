package uz.nodir.file.service.business;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Nodir
 * on Date 27 май, 2024
 * Java Spring Boot by Davr Coders
 */
public interface FileSenderService {

    void save(MultipartFile multipartFile, String fileName);

    InputStreamResource getByName(String name);

    void deleteByName(String name);
}
