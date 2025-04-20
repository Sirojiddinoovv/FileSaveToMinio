package uz.nodir.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.nodir.file.model.entity.FileSaver;

import java.util.List;

/**
 * Created by Nodir
 * on Date 27 май, 2024
 * Java Spring Boot by Davr Coders
 */
@Repository
public interface FileSaverRepository extends JpaRepository<FileSaver, Long> {
    List<FileSaver> findByLoanExtId(String loanExtId);
}
