package com.aneeque.backendservice.util;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Okala Bashir .O.
 */

/**
 *
 * @param <E> the Entity Class to be used
 * @param <D> the Dto pojo
 */
public interface CrudService<E, D> {

    @Transactional
    D save(D dto);

    D getById(Long id);

    @Transactional
    D update(Long id, D dto);

    @Transactional
    void delete(Long id);

    List<D> getAll();
}
