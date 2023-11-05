package com.burhanmutlu.ws.entity;

import jakarta.persistence.*;
<<<<<<< HEAD
import lombok.*;
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
>>>>>>> 1d9b436d83a84bcb57f070a08b6ee70a5fb85d6c

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "files")
public class File extends BaseEntity {

    @Column(name = "size")
    private Double size;

    @Column(name = "file_extention")
    private String fileExtention;

    @Column(name = "path")
    private String path;

}
