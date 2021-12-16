package com.baiyan.ddd.infrastructure.event;

import com.baiyan.ddd.domain.share.event.BaseDomainEvent;
import com.baiyan.ddd.domain.share.event.DomainEventRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author baiyan
 */
@Repository
public class DomainEventRepositoryImpl implements DomainEventRepository {

    @Override
    public BaseDomainEvent load(Long id) {
        return null;
    }

    @Override
    public List<BaseDomainEvent> loadByDomainId(String domainId) {
        return null;
    }

    @Override
    public void save(BaseDomainEvent baseDomainEvent) {

    }

    @Override
    public void update(BaseDomainEvent baseDomainEvent) {

    }
}
