package com.aneeque.backendservice.data.repository;

import com.aneeque.backendservice.data.entity.Cart;
import com.aneeque.backendservice.dto.response.CartResponseDto;
import com.aneeque.backendservice.dto.response.CartResponseInterfaceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Okala Bashir .O.
 */

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    @Query(value = "select p.description as productDescription, p.name as productName, p.price as productPrice, " +
            "c.product_id as productId, c.quantity, c.unique_id as uniqueId, c.creator_id as creatorId, pm.file_name as fileName "+
            "from cart c  left join product p on c.product_id = p.id left join product_media pm on (p.id = pm.product_id and pm.file_type = 'IMAGE') where c.unique_id = :uniqueId ", nativeQuery = true)
    List<CartResponseInterfaceDto> findByCreatorId(@Param("creatorId") Long creatorId);


    @Query(value = "select p.description as productDescription, p.name as productName, p.price as productPrice, " +
            "c.product_id as productId, c.quantity, c.unique_id as uniqueId, c.creator_id as creatorId, pm.file_name as fileName "+
            "from cart c  left join product p on c.product_id = p.id left join product_media pm on (p.id = pm.product_id and pm.file_type = 'IMAGE') where c.unique_id = :uniqueId ", nativeQuery = true)
    List<CartResponseInterfaceDto> findByUniqueId(@Param("uniqueId") String uniqueId);

    @Query(value = "select c.id as cartId, c.created_at as cartCreatedAt, c.quantity as cartQuantity, " +
            "c.unique_id as cartUniqueId, p.description as productDescription, p.name as productName, " +
            "p.price as productPrice, au.first_name as creatorFirstName,au.last_name as creatorLastName " +
            "from cart c left join product p on c.product_id = p.id left join app_user au on c.creator_id = au.id " +
            " where c.id = :cartId", nativeQuery = true)
    Optional<CartResponseInterfaceDto> findByCartId(@Param("cartId") Long cartId);


    @Modifying
    @Query(value = "UPDATE cart  SET quantity = :quantity WHERE id =:cartId", nativeQuery = true)
    void updateQuantity(@Param("cartId") Long cartId, @Param("quantity") Long quantity);


}
