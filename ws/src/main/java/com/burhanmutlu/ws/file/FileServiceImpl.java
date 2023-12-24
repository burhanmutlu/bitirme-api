package com.burhanmutlu.ws.file;

import com.burhanmutlu.ws.file.dto.resp.FileDataResponse;
import com.burhanmutlu.ws.file.dto.resp.FileResponse;
import com.burhanmutlu.ws.file.exception.FileNotFoundException;
import com.burhanmutlu.ws.user.User;
import com.burhanmutlu.ws.user.UserService;
import com.burhanmutlu.ws.util.SizeConverter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private static final Logger log = LogManager.getLogger(FileServiceImpl.class);

    private final FileRepository fileRepository;

    private final UserService userService;
    
    private final FileMapper fileMapper;

    @Override
    public FileResponse addFileByUserId(Long userId, MultipartFile file) throws IOException {
        User user = userService.getUserById(userId);
        log.info("file is uploading...");
        return fileMapper.toFileResponse(
                fileRepository.save( File.builder()
                    .userId(user)
                    .fileName(file.getOriginalFilename())
                    .type(file.getContentType())
                    .data(file.getBytes())
                    .build() ));
    }

    @Override
    public FileDataResponse getFileById(String id) {
        File file = fileRepository.findById(id).orElseThrow(
                () -> { throw new FileNotFoundException("file not found"); });
        file.setLastOpened(new Date());
        fileRepository.save(file);

        log.info("getting file by id: " + id);

        return FileDataResponse.builder()
                .fileName(file.getFileName())
                .data(file.getData())
                .build();
    }

    @Override
    @Transactional
    public List<FileResponse> getAllFilesByUserId(Long userId) {
        User user = userService.getUserById(userId);
        List<FileResponse> fileResponseList = new ArrayList<>();

        fileRepository.findAllByUserId(userId).forEach(file -> {
            fileResponseList.add(fileMapper.toFileResponse(file)); });

        log.info("getting all files..");

        return fileResponseList;
    }

    @Override
    public void deleteFileById(String id) {
        File file = fileRepository.findById(id).orElseThrow(
                () -> { throw new FileNotFoundException("file not found"); });

        log.info("deleted file id: " + id);

        fileRepository.deleteById(id);
    }

    @Override
    public void updateFileNameById(String id, String fileName) {
        File file = fileRepository.findById(id).orElseThrow(
                () -> {  throw new FileNotFoundException("file not found"); });

        file.setFileName(fileName);

        fileRepository.save(file);

        log.info("updated file name: " + fileName);
    }

    @Override
    @Transactional
    public List<FileResponse> getRecommended10FilesByUserId(Long userId) {
        User user = userService.getUserById(userId);
        List<FileResponse> fileResponseList = new ArrayList<>();
        fileRepository.findByUserIdOrderByLastOpenedDesc(userId).stream().limit(10).forEach(file -> {
            fileResponseList.add(fileMapper.toFileResponse(file)); });

        log.info("getting recommended 10 files");

        return fileResponseList;
    }

    @Override
    @Transactional
    public List<FileResponse> getAllImagesByUserId(Long userId) {
        User user = userService.getUserById(userId);
        List<FileResponse> fileResponseList = new ArrayList<>();

        fileRepository.findAllByUserId(userId).forEach(file -> {
            if(file.getType().startsWith("image")) {
                fileResponseList.add(fileMapper.toFileResponse(file));
            }
        });

        log.info("getting all images..");

        return fileResponseList;
    }
}
