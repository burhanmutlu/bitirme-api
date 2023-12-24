package com.burhanmutlu.ws.file;

import com.burhanmutlu.ws.file.dto.resp.FileDataResponse;
import com.burhanmutlu.ws.file.dto.resp.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    FileResponse addFileByUserId(Long userId, MultipartFile file) throws IOException;

    FileDataResponse getFileById(String id);

    List<FileResponse> getAllFilesByUserId(Long userId);

    void deleteFileById(String id);

    void updateFileNameById(String id, String fileName);

    List<FileResponse> getRecommended10FilesByUserId(Long userId);

    List<FileResponse> getAllImagesByUserId(Long userId);

}
