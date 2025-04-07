package com.example.BankingSystem.Repository;

import com.example.BankingSystem.Model.AccountModel;
import com.example.BankingSystem.Model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel,Integer> {



    @Query("Select t from TransactionModel t where t.accountModel.id=:id")
    List<TransactionModel> getHistory(int id);

    @Query("Select t from TransactionModel t where t.accountModel.id=:id and(t.transactionType=:tType)")
    List<TransactionModel> getHistorybytype(int id, String tType);

    @Query("Select t from TransactionModel t where t.accountModel.id=:id and t.transactionTime BETWEEN :sDate AND :eDate")
    List<TransactionModel> getHistorybyDate(int id, LocalDateTime sDate, LocalDateTime eDate);

    @Query("Select t from TransactionModel t where t.transactionTime BETWEEN :sDate AND :eDate")
    List<TransactionModel> getAllTransactionByDate(LocalDateTime sDate, LocalDateTime eDate);

    @Query("Select SUM(t.transactionAmount) from TransactionModel t where t.accountModel.id=:id")
    BigDecimal gettotalTransaction(int id);

    @Query("SELECT t FROM TransactionModel t Where t.accountModel.id=:id ORDER BY t.id DESC")
    List<TransactionModel> getTransactionReverse(int id);


}
