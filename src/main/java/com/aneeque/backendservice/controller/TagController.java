package com.aneeque.backendservice.controller;

import com.aneeque.backendservice.dto.response.ApiResponse;
import com.aneeque.backendservice.dto.request.TagRequestDto;
import com.aneeque.backendservice.dto.response.TagResponseDto;
import com.aneeque.backendservice.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Okala Bashir .O.
 */

@RestController
@RequestMapping("/api/v1/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping
    public ResponseEntity<?> createTag(@RequestBody TagRequestDto tagRequestDto){
        return new ResponseEntity(new ApiResponse("Tag created successfully",tagService.save(tagRequestDto)), HttpStatus.OK);

    }

    @PutMapping("{tagId}")
    public ResponseEntity<TagResponseDto> updateTag(@PathVariable String tagId, @RequestBody TagRequestDto tagRequestDto){
        return new ResponseEntity(new ApiResponse(tagService.update(Long.valueOf(tagId),tagRequestDto)), HttpStatus.OK);
    }

    @GetMapping("{tagId}")
    public ResponseEntity<?> getTagById(@PathVariable String tagId){
        return ResponseEntity.ok(new ApiResponse(tagService.getById(Long.valueOf(tagId))));
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllTags(){
        return ResponseEntity.ok(new ApiResponse(tagService.getAll()));
    }


    @DeleteMapping("{tagId}")
    public ResponseEntity<?> deleteTagById(@PathVariable String tagId){
        return ResponseEntity.ok(new ApiResponse(tagService.delete(Long.valueOf(tagId))));
    }
}
