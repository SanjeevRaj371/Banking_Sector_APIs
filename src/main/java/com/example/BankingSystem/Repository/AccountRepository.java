package com.example.BankingSystem.Repository;


import com.example.BankingSystem.Model.AccountModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel,Integer>{

    @Query("SELECT a from AccountModel a where a.balance>= :amount ")
    List<AccountModel> getaccount_minamount(BigDecimal amount);

    @Query("SELECT a from AccountModel a where a.accountName = :name")
    AccountModel getaccountbyname(String name);

    @Modifying
    @Transactional
    @Query("UPDATE AccountModel a SET a.status = 'enable' WHERE a.id =:id")
    void enable(int id);


    @Modifying
    @Transactional
    @Query("UPDATE AccountModel a SET a.status = 'disable' WHERE a.id =:id")
    void disableaccount(int id);


    @Query("Select a from AccountModel a where a.transactionCount = 0")
    List<AccountModel> getNoTransactionAccount();
}
