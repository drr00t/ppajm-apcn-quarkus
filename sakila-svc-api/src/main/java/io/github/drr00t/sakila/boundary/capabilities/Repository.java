package io.github.drr00t.sakila.boundary.capabilities;

import io.github.drr00t.sakila.boundary.Result;

public interface Repository<TDomain, TId> {
    Result<TDomain> findById(TId identity);

    Result<TId> save(TDomain entity);

    Result<TId> delete(TId identity);

    Result<TId> update(TDomain entity);

}
