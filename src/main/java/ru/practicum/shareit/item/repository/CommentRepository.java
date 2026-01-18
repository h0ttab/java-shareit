package ru.practicum.shareit.item.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.shareit.item.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByItem_Id(Long itemId, Sort sort);

    List<Comment> findAllByItem_IdIn(Collection<Long> itemIds, Sort sort);
}