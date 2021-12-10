package com.aneeque.backendservice.category.department.attribute;

import java.util.List;

/**
 * @author Okala III
 */

public interface AttributeService {
    AttributeDto save(AttributeDto attributeDto);

    AttributeDto update(Long id, AttributeDto attributeDto);

    List<AttributeDto> getAllAttributes();

    List<AttributeDto> getAllAttributesByDepartmentId(Long id);


}
