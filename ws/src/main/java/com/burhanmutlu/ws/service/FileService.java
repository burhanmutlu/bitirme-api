package com.burhanmutlu.ws.service;

import com.burhanmutlu.ws.dto.resp.FileDataResponse;
import com.burhanmutlu.ws.dto.resp.FileResponse;
import com.burhanmutlu.ws.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface FileService {
    FileResponse addFileByUserId(Long userId, MultipartFile file) throws IOException;

    FileDataResponse getFileById(String id);

    List<FileResponse> getAllFilesByUserId(Long userId);

    void deleteFileById(String id);

    void updateFileNameById(String id, String fileName);

}
