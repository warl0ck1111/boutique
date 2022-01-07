package com.aneeque.backendservice.service;

import com.aneeque.backendservice.data.entity.Tag;
import com.aneeque.backendservice.data.repository.TagRepository;
import com.aneeque.backendservice.dto.request.TagRequestDto;
import com.aneeque.backendservice.dto.response.TagResponseDto;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Okala Bashir .O.
 */

@Service
@Getter
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    @Transactional
    public TagResponseDto save(TagRequestDto tagRequestDto) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagRequestDto, tag);
        Tag savedTag = tagRepository.save(tag);
        TagResponseDto tagResponseDto = new TagResponseDto();
        BeanUtils.copyProperties(savedTag, tagResponseDto);
        return tagResponseDto;
    }

    public TagResponseDto getById(Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new NoSuchElementException("no tag found"));
        TagResponseDto tagResponseDto = new TagResponseDto();
        BeanUtils.copyProperties(tag, tagResponseDto);
        return tagResponseDto;
    }

    @Transactional
    public String update(Long tagId, TagRequestDto dto) {
        tagRepository.updateTag(tagId, dto.getSlug(), dto.getName(), dto.getDescription());
        return "updated successfully";
    }

    public String delete(Long id) {
        tagRepository.deleteById(id);
        return "deleted successfully";
    }

    public List<TagResponseDto> getAll() {
        List<TagResponseDto> tagResponseDtoList = new ArrayList<>();
        List<Tag> tagList = tagRepository.findAll();
        tagList.forEach(tag ->{
            TagResponseDto tagResponseDto = new TagResponseDto();
            BeanUtils.copyProperties(tag, tagResponseDto);
            tagResponseDtoList.add(tagResponseDto);
        });
        return tagResponseDtoList;
    }
}
