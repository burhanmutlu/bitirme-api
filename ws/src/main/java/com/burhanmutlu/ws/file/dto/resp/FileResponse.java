package com.burhanmutlu.ws.file.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileResponse {
    private String id;
    private String name;
    private String url;
    private String type;
    private String size;
}
