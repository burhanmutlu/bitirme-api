package com.burhanmutlu.ws.entity;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "files")
public class File extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "file_size")
    private Double fileSize;

    @Column(name = "file_extention")
    private String fileExtention;

    @Column(name = "file_url_path")
    private String fileUrlPath;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

}
