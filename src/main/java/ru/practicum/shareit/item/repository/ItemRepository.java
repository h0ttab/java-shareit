package ru.practicum.shareit.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.shareit.item.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByOwner_Id(Long ownerId);

    @Query("""
            select i from Item i
            where i.available = true
              and (
                   upper(i.name) like upper(concat('%', :text, '%'))
                or upper(i.description) like upper(concat('%', :text, '%'))
              )
            """)
    List<Item> search(@Param("text") String text);
}