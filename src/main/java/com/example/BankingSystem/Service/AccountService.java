package com.example.BankingSystem.Service;

import com.example.BankingSystem.Model.AccountModel;
import com.example.BankingSystem.Model.TransactionModel;
import com.example.BankingSystem.Repository.AccountRepository;
import com.example.BankingSystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;




    public AccountModel createAccount(AccountModel accountModel) {
        return accountRepository.save(accountModel);
    }
    public String deposit(int id,BigDecimal amount,String des){
        AccountModel data=accountRepository.findById(id).orElse(null);
        if(data==null){
            return "Account Doesn't exist";
        }
        if(data.getStatus().equals("enable")){
            data.setBalance(data.getBalance().add(amount));
            data.setTransactionCount(data.getTransactionCount()+1);
            accountRepository.save(data);

            TransactionModel transactionModel= new TransactionModel();
            transactionModel.setTransactionAmount(amount);
            transactionModel.setAccountModel(data);
            transactionModel.setTransactionTime(LocalDateTime.now());
            transactionModel.setTransactionType("Deposit");
            transactionModel.setBalance(data.getBalance());
            transactionModel.setDescribtion(des);
            transactionRepository.save(transactionModel);


            return "AMOUNT ADDED SUCCESSFULLY IN THE ACCOUNT ID "+data.getId()+"with updated balance "+data.getBalance();
        }else return "Account status is disable to transaction after enable";

    }
    public String checkbalance(BigDecimal balance){
        return "Your Account Balance is:"+balance;
    }
    public String withdraw(int id,BigDecimal amount,String pin,String des){
        AccountModel data=accountRepository.findById(id).orElse(null);
        if(data==null){
            return "Account Doesn't exist";
        }
        if(data.getStatus().equals("enable")){
            if(!data.getPin().matches(pin)){
                return "Invalid Pin";
            }
            if(data.getBalance().compareTo(amount)<0){
                return "Your Account Balance is Low ";
            }
            data.setBalance(data.getBalance().subtract(amount));
            data.setTransactionCount(data.getTransactionCount()+1);
            accountRepository.save(data);

            TransactionModel transactionModel= new TransactionModel();
            transactionModel.setTransactionAmount(amount);
            transactionModel.setAccountModel(data);
            transactionModel.setTransactionTime(LocalDateTime.now());
            transactionModel.setTransactionType("Withdraw");
            transactionModel.setBalance(data.getBalance());
            transactionModel.setDescribtion(des);
            transactionRepository.save(transactionModel);


            return "The Withdrawal done Successfully";
        }else return "Account status is disable to transaction after enable";

    }
    public List<AccountModel> getaccountdetails_minamount(BigDecimal amount){
        return accountRepository.getaccount_minamount(amount);
    }
    public AccountModel getaccountbyname(String name){
        return accountRepository.getaccountbyname(name);
    }
    public String validatePin(int id, String pin){
        AccountModel data=accountRepository.findById(id).orElse(null);
        if(data==null){
            return "Account Doesn't exist";
        }
        if(!data.getPin().matches(pin)){
            return "Invalid Pin";
        }else
            return "Pin is Successful";
    }

    public List<TransactionModel> getTransactionofId(int id, String tType) {
        AccountModel data=accountRepository.findById(id).orElse(null);
        if (data == null) {
            System.out.println("Account does not exist");
            return null;
        }
        return transactionRepository.getHistorybytype(id,tType);
    }

    public String enableAccount(int id) {
        AccountModel data=accountRepository.findById(id).orElse(null);
        if(data==null){
            return "Account does not exist";
        }
        accountRepository.enable(id);
        return "Account enabled";
    }

    public String disableAccount(int id) {
        AccountModel data=accountRepository.findById(id).orElse(null);
        if(data==null){
            return "Account does not exist";
        }
        accountRepository.disableaccount(id);
        return "Account disabled";
    }

    public List<TransactionModel> getTransactionByDate(int id, LocalDateTime sDate, LocalDateTime eDate) {
        AccountModel data=accountRepository.findById(id).orElse(null);
        if (data == null) {
            System.out.println("Account does not exist");
            return null;
        }
        return transactionRepository.getHistorybyDate(id,sDate,eDate);
    }

    public List<TransactionModel> getTransactionByDate(LocalDateTime sDate, LocalDateTime eDate) {
        return transactionRepository.getAllTransactionByDate(sDate,eDate);
    }

    public String gettotalTofacc(int id) {
        AccountModel data=accountRepository.findById(id).orElse(null);
        if(data==null){
            return "Account does not exist";
        }
        BigDecimal sum=transactionRepository.gettotalTransaction(id);
        return "THE TOTAL TRANSACTION IS :"+sum;
    }

    public List<TransactionModel> getTransactionReverse(int id) {
        AccountModel data=accountRepository.findById(id).orElse(null);
        if(data==null){
            return null;
        }
        return transactionRepository.getTransactionReverse(id);

    }

    public String transferAmount(int fromId, String pin, BigDecimal amount, int toId) {
        AccountModel data1=accountRepository.findById(fromId).orElse(null);
        AccountModel data2=accountRepository.findById(toId).orElse(null);
        if(data1==null||data2==null){
            return "Account id is invalid";
        }
        String withdraw="amount sent to account id : "+data2.getId();
        String deposit="amount received from account id : "+data1.getId();
        withdraw(fromId,amount,pin,withdraw);
        deposit(toId,amount,deposit);

        return "Transaction done successfully";
    }

    public List<AccountModel> getNoTransactionAccount() {
        return accountRepository.getNoTransactionAccount();
    }
}
