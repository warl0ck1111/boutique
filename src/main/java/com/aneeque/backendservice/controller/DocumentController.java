package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.documents.DocumentService;
import com.aneeque.backendservice.dto.response.DocumentResponseDto;
import com.aneeque.backendservice.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.io.IOException;

/**
 * @author Bashir .O. Okala
 */


@RestController
@RequestMapping("/api/v1/files")
public class DocumentController extends ResponseEntityExceptionHandler {

    @Autowired
    private DocumentService documentService;


    @Value("{spring.http.multipart.max-file-size}")
    private String maxfileSize;

    @GetMapping(value = "{fileName:.+}", produces = MediaType.ALL_VALUE)
    public ResponseEntity<byte[]> getFile(@PathVariable(name = "fileName") String fileName) throws IOException {
        byte[] media = this.documentService.getImageWithMediaType(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        return new ResponseEntity<>(media, headers, HttpStatus.OK);
    }



    @Transactional
    @PostMapping(path = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(
            @RequestPart("fileUpload") MultipartFile file
    ) {
        logger.info(file);
        logger.info("this is the file size" + file.getSize());
        DocumentResponseDto responseDto = documentService.uploadFile(file);
        return ResponseEntity.ok(new ApiResponse("file uploaded successfully", responseDto));
    }
}
