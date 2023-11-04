package com.burhanmutlu.ws.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "files")
public class File extends BaseEntity {

    @Column(name = "size")
    private Double size;

    @Column(name = "file_extention")
    private String fileExtention;

    @Column(name = "path")
    private String path;

}
