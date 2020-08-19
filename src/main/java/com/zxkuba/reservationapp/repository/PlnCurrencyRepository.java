package com.zxkuba.reservationapp.repository;

import com.zxkuba.reservationapp.entity.currency.FixerCurrency;
import com.zxkuba.reservationapp.entity.currency.FixerCurrencyRate;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PlnCurrencyRepository extends CrudRepository<FixerCurrency, Long> {

    @Override
    FixerCurrency save(FixerCurrency plnCurrency);

    void deleteAll();

}
