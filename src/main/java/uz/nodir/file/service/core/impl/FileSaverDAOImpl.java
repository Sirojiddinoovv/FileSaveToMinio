package uz.nodir.file.service.core.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.nodir.file.exception.BadRequestException;
import uz.nodir.file.model.entity.FileSaver;
import uz.nodir.file.repository.FileSaverRepository;
import uz.nodir.file.service.core.FileSaverDAO;

import java.util.List;

/**
 * Created by Nodir
 * on Date 27 май, 2024
 * Java Spring Boot by Davr Coders
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class FileSaverDAOImpl implements FileSaverDAO {
    private final FileSaverRepository repository;

    @Override
    public FileSaver save(FileSaver fileSaver) {
        log.info("Save file: {}", fileSaver);
        return repository.save(fileSaver);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FileSaver> findFilesByLoanExtId(String loanExtId) {
        log.info("Find files by loanExtId: {}", loanExtId);
        return repository.findByLoanExtId(loanExtId);
    }

    @Override
    @Transactional(readOnly = true)
    public FileSaver findById(Long id) {
        log.info("Find file by id: {}", id);
        return repository.findById(id)
                .orElseThrow(()-> new BadRequestException("File with id: %d not found in database".formatted(id)));
    }

}
