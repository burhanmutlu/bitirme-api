package com.burhanmutlu.ws.service.impl;

import com.burhanmutlu.ws.dto.resp.FileDataResponse;
import com.burhanmutlu.ws.dto.resp.FileResponse;
import com.burhanmutlu.ws.entity.File;
import com.burhanmutlu.ws.entity.User;
import com.burhanmutlu.ws.exception.FileNotFoundException;
import com.burhanmutlu.ws.repository.FileRepository;
import com.burhanmutlu.ws.service.FileService;
import com.burhanmutlu.ws.service.UserService;
import com.burhanmutlu.ws.util.SizeConverter;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private static final Logger log = LogManager.getLogger(FileServiceImpl.class);

    private final FileRepository fileRepository;

    private final UserService userService;

    @Override
    public FileResponse addFileByUserId(Long userId, MultipartFile file) throws IOException {
        User user = userService.getUserById(userId);
        String fileName = file.getOriginalFilename();

        File file1 = File.builder()
                .userId(user)
                .fileName(fileName)
                .type(file.getContentType())
                .data(file.getBytes())
                .build();
        fileRepository.save(file1);

        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/files/")
                .path(file1.getId().toString())
                .toUriString();

        SizeConverter sizeConverter = new SizeConverter();
        sizeConverter.autoConvert(file1.getData().length);

        FileResponse fileResponse = FileResponse.builder()
                .name(file1.getFileName())
                .size(sizeConverter.getSizeString())
                .url(fileDownloadUri)
                .type(file1.getType())
                .build();

        return fileResponse;
    }

    @Override
    public FileDataResponse getFileById(String id) {
        File file = fileRepository.findById(id).orElseThrow(() -> {
            throw new FileNotFoundException("file not found");
        });

        FileDataResponse fileDataResponse = FileDataResponse.builder()
                .fileName(file.getFileName())
                .data(file.getData())
                .build();

        return fileDataResponse;
    }

    @Override
    @Transactional
    public List<FileResponse> getAllFilesByUserId(Long userId) {
        User user = userService.getUserById(userId);
        List<FileResponse> fileResponseList = new ArrayList<>();

        fileRepository.findAllByUserId(userId).forEach(file -> {

            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/v1/files/")
                    .path(file.getId().toString())
                    .toUriString();

            SizeConverter sizeConverter = new SizeConverter();
            sizeConverter.autoConvert(file.getData().length);

            FileResponse fileResponse = FileResponse.builder()
                    .name(file.getFileName())
                    .url(fileDownloadUri)
                    .size(sizeConverter.getSizeString())
                    .type(file.getType())
                    .build();
            fileResponseList.add(fileResponse);
        });

        return fileResponseList;
    }

    @Override
    public void deleteFileById(String id) {
        File file = fileRepository.findById(id).orElseThrow(() -> {
            throw new FileNotFoundException("file not found");
        });

        fileRepository.deleteById(id);
    }

    @Override
    public void updateFileNameById(String id, String fileName) {
        File file = fileRepository.findById(id).orElseThrow(() -> {
            throw new FileNotFoundException("file not found");
        });

        file.setFileName(fileName);

        fileRepository.save(file);
    }
}
