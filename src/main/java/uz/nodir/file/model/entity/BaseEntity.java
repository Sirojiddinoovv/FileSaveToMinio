package uz.nodir.file.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Nodir
 * on Date 26 дек., 2023
 * Java Spring Boot by Davr Coders
 */
@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1234567L;

    @Column(name = "initTime", updatable = false)
    private LocalDateTime initTime = LocalDateTime.now();
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDate createdDate = LocalDate.now();
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;
    @LastModifiedDate
    @Column(name = "update_time")
    private LocalDateTime updateTime = LocalDateTime.now();
    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy;

}
