package com.group.newpage.hostel.helper;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {

    public static void loadFile(final String uploadDir, final String fileName, final MultipartFile image) throws IOException {

        Path path = Paths.get(uploadDir);

        if (!Files.exists(path)) {

            Files.createDirectories(path);
        }
        try (InputStream inputStream = image.getInputStream()) {

            Path fullPath = path.resolve(fileName);
            Files.copy(inputStream, fullPath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ioException) {

            ioException.printStackTrace();
        }
    }

    public static void saveDefaultImageUser(final String uploadDir, final String fileName, final File defaultImage) throws IOException {

        if(defaultImage==null || !defaultImage.exists()){

            return;
        }
        Path path=Paths.get(uploadDir);

        if(!Files.exists(path)){

            Files.createDirectories(path);
        }
        Path fullPath=path.resolve(fileName);

        Files.createFile(fullPath);
        File image=new File(uploadDir+"/"+fileName);

        FileUtils.copyFile(defaultImage, image);

        return;
    }
}
