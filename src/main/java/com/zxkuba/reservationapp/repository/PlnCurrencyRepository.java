package com.zxkuba.reservationapp.repository;

import com.zxkuba.reservationapp.entity.currency.FixerCurrency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PlnCurrencyRepository extends CrudRepository<FixerCurrency, Long> {

    @Override
    FixerCurrency save(FixerCurrency plnCurrency);
}
