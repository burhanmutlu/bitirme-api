package com.burhanmutlu.ws.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Component
public class FileRead {

    @Autowired
    private ResourceLoader resourceLoader;

    public String readFile(String templateName) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:templates/"+templateName);
        try (InputStreamReader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            StringBuilder content = new StringBuilder();
            char[] buffer = new char[1024];
            int bytesRead;
            while ((bytesRead = reader.read(buffer, 0, buffer.length)) != -1) {
                content.append(buffer, 0, bytesRead);
            }
            return content.toString();
        }
    }

}
