package com.neoteric.bankloan.service;

import com.neoteric.bankloan.model.ApplicationForm;
import com.neoteric.bankloan.model.CustomerDetails;
import com.neoteric.bankloan.model.LoanDetails;

import java.util.UUID;

public class BankLoanService { public int age=18;
    public double noOfMonths=12;

    public void registrationService(ApplicationForm applicationForm) {

        if (applicationForm.customerDetails.age >= age &&
                applicationForm.customerDetails.income != applicationForm.customerDetails.expenditure) {
            applicationForm.applicationNumber = UUID.randomUUID().toString();
            System.out.println("you are eligible for loan " + applicationForm.applicationNumber);
        } else {
            System.out.println("you are not eligible for loan");
        }
    }
    public LoanDetails approveAmount(CustomerDetails customerDetails){
        double amount= customerDetails.income-customerDetails.expenditure;
        double approvedAmount= amount*10;
        customerDetails.loanAmount=approvedAmount;
        LoanDetails loanDetails= new LoanDetails();
        loanDetails.approvedAmount=approvedAmount;
        loanDetails.monthlyEmi=amount*0.6;
        loanDetails.rateOfInterest=9;
        return loanDetails;
    }
    public void emiApproval(CustomerDetails customerDetails){
        double amount= customerDetails.income-customerDetails.expenditure;
        if(customerDetails.selectedTenure>=noOfMonths){
            if(amount>amount*0.6){
                System.out.println("you are eligible for emi");
            }

        }

    }

}


