package com.burhanmutlu.ws.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "files")
public class File extends BaseEntity {

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    private String id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "type")
    private String type;

    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User userId;

}
