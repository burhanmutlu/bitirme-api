package com.burhanmutlu.ws.file;

import com.burhanmutlu.ws.shared.BaseEntity;
import com.burhanmutlu.ws.user.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_opened")
    private Date lastOpened;

    @Column(name = "type")
    private String type;

    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

}
