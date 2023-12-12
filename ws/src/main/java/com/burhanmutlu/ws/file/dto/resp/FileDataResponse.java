package com.burhanmutlu.ws.file.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDataResponse {
    
    private String fileName;

    @Lob
    private byte[] data;

}
