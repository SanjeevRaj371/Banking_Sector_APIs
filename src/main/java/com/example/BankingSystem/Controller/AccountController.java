package com.example.BankingSystem.Controller;

import com.example.BankingSystem.Model.AccountModel;
import com.example.BankingSystem.Model.TransactionModel;
import com.example.BankingSystem.Repository.AccountRepository;
import com.example.BankingSystem.Repository.TransactionRepository;
import com.example.BankingSystem.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/Account")
@RestController
public class AccountController {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountService accountService;
    @Autowired
    AccountRepository accountRepository;

    @PostMapping("/CreateAccount")
    public AccountModel createAccount(@RequestBody AccountModel accountModel){
        return accountService.createAccount(accountModel);
    }

    @PostMapping("/Deposit/{id}")
    public String deposit(@PathVariable int id, @RequestParam("amount")BigDecimal amount){
        String des ="Manual";
        return accountService.deposit(id,amount,des);

    }
    @GetMapping("/checkBalance/{id}")
    public String checkbalance(@PathVariable int id){
        AccountModel data=accountRepository.findById(id).orElse(null);
        if(data==null){
            return "Account Doesn't Exist";
        }
        BigDecimal balance=data.getBalance();
        return accountService.checkbalance(balance);

    }
    @PostMapping("/Withdraw/{id}")
    public String withdraw(@PathVariable int id, @RequestParam("amount")BigDecimal amount,@RequestParam("pin")String pin){
        String des ="Manual";
       return accountService.withdraw(id, amount, pin,des);
    }
    @GetMapping("/getTransactionHistory/{id}")
    public List<TransactionModel> getTransactionHistory(@PathVariable int id){
        return transactionRepository.getHistory(id);
    }
    @GetMapping("/getaccountdetail_by_min_amount")
    public List<AccountModel> getaccountdetailsbyamount(@RequestParam("amount")BigDecimal amount){
        return accountService.getaccountdetails_minamount(amount);
    }
    @GetMapping("/getaccountbyname")
    public AccountModel getaccountbyname(@RequestParam("name")String name){
        return accountService.getaccountbyname(name);
    }
    @GetMapping("/validatepin/{id}")
    public String validate(@PathVariable int id , @RequestParam("pin")String pin){
        return accountService.validatePin(id,pin);
    }
    @GetMapping("/getTransactionofId/{id}")
    public List<TransactionModel> getTransactionofId(@PathVariable int id,@RequestParam("TransactionType")String tType){
        return accountService.getTransactionofId(id,tType);
    }
    @PostMapping("/enableAccount/{id}")
    public String enableAccount(@PathVariable int id){
        return accountService.enableAccount(id);
    }
    @PostMapping("/disableAccount/{id}")
    public String disableAccount(@PathVariable int id){
        return accountService.disableAccount(id);
    }
    @GetMapping("/getTransactionByDate/{id}")
    public List<TransactionModel> getTransactionByDate(@PathVariable int id, @RequestParam("Startdate")LocalDateTime sDate,@RequestParam("Enddate")LocalDateTime eDate){
        return accountService.getTransactionByDate(id,sDate,eDate);
    }
    @GetMapping("/getTotalTransactionByDate")
    public List<TransactionModel> getTotalTransactionByDate( @RequestParam("Startdate")LocalDateTime sDate,@RequestParam("Enddate")LocalDateTime eDate){
        return accountService.getTransactionByDate(sDate,eDate);
    }
    @GetMapping("/totalTransactionofaccount/{id}")
    public String gettotalTransactionofaccount(@PathVariable int id){
        return accountService.gettotalTofacc(id);
    }
    @GetMapping("/getTransactionReverse/{id}")
    public List<TransactionModel> getTransactionReverse(@PathVariable int id){
        return accountService.getTransactionReverse(id);
    }
    @PostMapping("/transferAmount")
    public String transferAmount(@RequestParam ("From") int from_id,@RequestParam("pin") String pin,@RequestParam("Amount") BigDecimal amount,@RequestParam("To") int To_id){
        return accountService.transferAmount(from_id,pin,amount,To_id);
    }
    @GetMapping("/getNoTransactionAccount")
    public List<AccountModel> getNoTransactionAccount(){
        return accountService.getNoTransactionAccount();
    }
}

