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

    public static final String GET_ALL_TAGS = "";
    public static final String DELETE_TAG_BY_ID = "{tagId}";
    public static final String GET_TAG_BY_ID = "{tagId}";
    public static final String UPDATE_TAG = "{tagId}";
    public static final String CREATE_TAG = "";
    @Autowired
    private TagService tagService;

    @PostMapping(CREATE_TAG)
    public ResponseEntity<?> createTag(@RequestBody TagRequestDto tagRequestDto){
        return new ResponseEntity(new ApiResponse("Tag created successfully",tagService.save(tagRequestDto)), HttpStatus.OK);

    }

    @PutMapping(UPDATE_TAG)
    public ResponseEntity<TagResponseDto> updateTag(@PathVariable String tagId, @RequestBody TagRequestDto tagRequestDto){
        return new ResponseEntity(new ApiResponse(tagService.update(Long.valueOf(tagId),tagRequestDto)), HttpStatus.OK);
    }

    @GetMapping(GET_TAG_BY_ID)
    public ResponseEntity<?> getTagById(@PathVariable String tagId){
        return ResponseEntity.ok(new ApiResponse(tagService.getById(Long.valueOf(tagId))));
    }

    @GetMapping(GET_ALL_TAGS)
    public ResponseEntity<?> getAllTags(){
        return ResponseEntity.ok(new ApiResponse(tagService.getAll()));
    }


    @DeleteMapping(DELETE_TAG_BY_ID)
    public ResponseEntity<?> deleteTagById(@PathVariable String tagId){
        return ResponseEntity.ok(new ApiResponse(tagService.delete(Long.valueOf(tagId))));
    }
}
