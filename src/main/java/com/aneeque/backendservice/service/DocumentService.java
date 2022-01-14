package com.aneeque.backendservice.service;

import com.aneeque.backendservice.dto.response.DocumentResponseDto;
import com.aneeque.backendservice.exception.ApiRequestException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Bashir Onuche Okala III
 *
 */


@Slf4j
@Data
@Service
public class DocumentService {

    @Value("${com.aneeque.file.upload.format}")
    public List<String> fileUploadFormat;

    @Value("${spring.http.multipart.max-file-size}")
    private String maxFileSize;

    @Autowired
    private ServletContext servletContext;

    @Value("${com.aneeque.file.directory}")
    private String fileDirectory;



    public static String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
    }


    public String validateFile(MultipartFile file) {
        String extension = DocumentService.getFileExtension(file.getOriginalFilename());
        if (!fileUploadFormat.contains(extension)) {
            return "Invalid File Type: " + extension;
        }
        return null;
    }


    public static String generateUploadToFilename(String originalFilename) {
        return generateUploadToFilename(originalFilename, false);
    }



    public static String generateUploadToFilename(String originalFilename, boolean noExtension) {
        Date date = new Date();
        String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
        String extension = "";
        if (noExtension == false) {
            extension = DocumentService.getFileExtension(originalFilename);
        }
        String name = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        if (name.length() > 80) {
            name = name.substring(0, 80);
        }
        return noExtension ? name + "_" + timeStamp : name + "_" + timeStamp + "." + extension;
    }

    public DocumentResponseDto uploadFile(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String newFile;
        String FileExt = FilenameUtils.getExtension(fileName);

        newFile= generateUniqueFileName() + "." + FileExt;

        fileName = newFile;

        Path storageDirectory = Paths.get(fileDirectory);

        if(!Files.exists(storageDirectory)){
            try {
                Files.createDirectories(storageDirectory);
            }catch (Exception e){
                log.error(e.getMessage());
                e.printStackTrace();
                throw new ApiRequestException("there was a problem uploading file", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        Path destination = Paths.get(storageDirectory.toString() + File.separator + fileName);

        try {
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(fileDirectory)
                .path(fileName)
                .toUriString();
        log.info("file name "+ fileName);
        return new DocumentResponseDto(fileName);
    }

    public  byte[] getImageWithMediaType(String imageName) {
        Path destination =   Paths.get(fileDirectory+ File.separator + imageName);// retrieve the image by its name

        try {
            return IOUtils.toByteArray(destination.toUri());
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof FileNotFoundException)
            throw new ApiRequestException("file not found", HttpStatus.NOT_FOUND );
        }
        return null;
    }

    /**
     * @param outputFile
     * @param response
     * @param isDownload
     */
    public void previewDocument(String outputFile, HttpServletResponse response, boolean isDownload) {
        try {
            File file = new File(outputFile);
            String filename = file.getName();
            String mineType = this.servletContext.getMimeType(filename);

            response.setContentType(mineType);
            if (isDownload) {
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename);
                response.setContentLength((int) file.length());
            } else {
                response.setHeader(HttpHeaders.EXPIRES, "0");
                response.setHeader(HttpHeaders.CACHE_CONTROL, "must-revalidate, post-check=0, pre-check=0");
                response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + filename);
                response.setHeader(HttpHeaders.ACCEPT_RANGES, "bytes");
            }

            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ServletOutputStream sos = response.getOutputStream();
            byte[] buffer = new byte[2048];
            while (true) {
                int bytesRead = bis.read(buffer, 0, buffer.length);
                if (bytesRead < 0) {
                    break;
                }
                sos.write(buffer, 0, bytesRead);
                sos.flush();
            }
            sos.flush();
            bis.close();

        } catch (IOException ex) {
            Logger.getLogger(DocumentService.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param outputFile
     * @param response
     */
    public void previewDocument(String outputFile, HttpServletResponse response) {
        this.previewDocument(outputFile, response, false);
    }

    public String generateUniqueFileName() {
        String filename = "";
        long millis = System.currentTimeMillis();
        String datetime = new Date().toString();
        datetime = datetime.replace(" ", "");
        datetime = datetime.replace(":", "");
        String rndchars = this.generateString();
        filename = rndchars + "_" + datetime + "_" + millis;
        return filename;
    }

    public String generateString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return  generatedString;
    }
}
