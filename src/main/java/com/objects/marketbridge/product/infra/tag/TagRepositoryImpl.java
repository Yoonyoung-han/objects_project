package com.objects.marketbridge.product.infra.tag;

import com.objects.marketbridge.product.domain.Tag;
import com.objects.marketbridge.product.service.port.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepository {
    private final TagJpaRepository tagJpaRepository;

    @Override
    public Tag findByName(String name) {
        return tagJpaRepository.findByName(name).orElseGet(() -> Tag.builder().name("EMPTY").build());
    }

    @Override
    public void save(Tag tag) {
        tagJpaRepository.save(tag);
    }

    @Override
    public Tag findById(Long id) {
        return tagJpaRepository.findById(id).orElseGet(() -> Tag.builder().name("EMPTY").build());
    }
}
