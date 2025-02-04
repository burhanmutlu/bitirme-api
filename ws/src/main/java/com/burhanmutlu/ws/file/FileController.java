package com.burhanmutlu.ws.file;

import com.burhanmutlu.ws.file.dto.req.FileNameRequest;
import com.burhanmutlu.ws.file.dto.resp.FileDataResponse;
import com.burhanmutlu.ws.file.dto.resp.FileResponse;
import com.burhanmutlu.ws.shared.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1")
public class FileController {

    private final FileService fileService;
    private static final Logger log = LogManager.getLogger(FileController.class);

    @PostMapping("/files/user/{userId}")
    public ResponseEntity<?> uploadFile(@PathVariable("userId") Long userId,
                                        @RequestParam("file") MultipartFile file) throws IOException {
        FileResponse fileResponse =fileService.addFileByUserId(userId, file);
        return ResponseEntity.ok(fileResponse);
    }

    @GetMapping("/files/user/{userId}")
    public ResponseEntity<List<FileResponse>> getAllFilesByUserId(@PathVariable("userId") Long userId) {
        List<FileResponse> fileResponses = fileService.getAllFilesByUserId(userId);
        return ResponseEntity.ok(fileResponses);
    }

    @GetMapping("/images/user/{userId}")
    public ResponseEntity<List<FileResponse>> getAllImagesByUserId(@PathVariable("userId") Long userId) {
        List<FileResponse> fileResponses = fileService.getAllImagesByUserId(userId);
        return ResponseEntity.ok(fileResponses);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFileById(@PathVariable String id) {
        FileDataResponse fileDataResponse = fileService.getFileById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileDataResponse.getFileName() + "\"")
                .body(fileDataResponse.getData());
    }

    @DeleteMapping("/files/{id}")
    public ResponseEntity<GenericResponse> deleteFileById(@PathVariable String id) {
        fileService.deleteFileById(id);
        return ResponseEntity.ok(new GenericResponse(true, "file is deleted"));
    }

    @PatchMapping("/files/{id}")
    public ResponseEntity<GenericResponse> updateFileNameById(@PathVariable String id, @RequestBody FileNameRequest fileName) {
        fileService.updateFileNameById(id, fileName.getFileName());
        return ResponseEntity.ok(new GenericResponse(true, "file name is updated"));
    }

    @GetMapping("/files/user/{userId}/recommended")
    public ResponseEntity<List<FileResponse>> getRecommendedFilesByUserId(@PathVariable Long userId) {
        List<FileResponse> fileResponseList = fileService.getRecommended10FilesByUserId(userId);
        return ResponseEntity.ok(fileResponseList);
    }

}
