package com.burhanmutlu.ws.file;

import com.burhanmutlu.ws.file.dto.resp.FileResponse;
import com.burhanmutlu.ws.util.SizeConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class FileMapper {

    public FileResponse toFileResponse(File file) {
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/files/")
                .path(file.getId().toString())
                .toUriString();

        SizeConverter sizeConverter = new SizeConverter();
        sizeConverter.autoConvert(file.getData().length);

        return FileResponse.builder()
                .id(file.getId())
                .name(file.getFileName())
                .url(fileDownloadUri)
                .size(sizeConverter.getSizeString())
                .type(file.getType())
                .build();
    }

}
