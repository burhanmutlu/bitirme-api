package com.burhanmutlu.ws.service.impl;

import com.burhanmutlu.ws.repository.FileRepository;
import com.burhanmutlu.ws.service.FileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger log = LogManager.getLogger(FileServiceImpl.class);

    @Autowired
    private FileRepository fileRepository;



}
