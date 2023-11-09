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

    @Column(name = "size")
    private Double size;

    @Column(name = "file_extention")
    private String fileExtention;

    @Column(name = "path")
    private String path;

}
