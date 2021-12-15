package com.aneeque.backendservice.util;

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

    D save(D dto);

    D getById(Long id);

    D update(Long id, D dto);

    void delete(Long id);

    List<D> getAll();
}
