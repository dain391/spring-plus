package org.example.expert.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.stereotype.Repository;

import static org.example.expert.domain.todo.entity.QTodo.todo;
import static org.example.expert.domain.user.entity.QUser.user;

@Repository
public class TodoCustomRepositoryImpl implements TodoCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public TodoCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Optional<Todo> findByIdWithUser(Long todoId) {
        Todo result = jpaQueryFactory
            .selectFrom(todo)
            .join(todo.user, user).fetchJoin()
            .where(todo.id.eq(todoId))
            .fetchOne();
        return Optional.ofNullable(result);
    }
}
