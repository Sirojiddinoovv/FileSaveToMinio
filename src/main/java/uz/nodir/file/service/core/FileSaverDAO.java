package uz.nodir.file.service.core;

import uz.nodir.file.model.entity.FileSaver;

import java.util.List;

/**
 * Created by Nodir
 * on Date 27 май, 2024
 * Java Spring Boot by Davr Coders
 */
public interface FileSaverDAO {

    FileSaver save(FileSaver fileSaver);

    List<FileSaver> findFilesByLoanExtId(String loanExtId);

    FileSaver findById(Long id);
}
