package com.neoteric.bankloan;

import com.neoteric.bankloan.model.ApplicationForm;
import com.neoteric.bankloan.model.CustomerDetails;
import com.neoteric.bankloan.model.LoanDetails;
import com.neoteric.bankloan.service.BankLoanService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankLoanServiceTest {
    @Test
    void testRegister_EligibleForLoan() {
        ApplicationForm form = new ApplicationForm();
        CustomerDetails customer = new CustomerDetails();
        customer.age=25;
        customer.income=50000;
        customer.expenditure=20000;
        form.customerDetails=customer;
        BankLoanService bankLoanService= new BankLoanService();
        bankLoanService.registrationService(form);

        assertNotNull(form.applicationNumber);
    }

    @Test
    void testRegister_NotEligibleForLoan_Age() {
        ApplicationForm form = new ApplicationForm();
        CustomerDetails customer = new CustomerDetails();
        customer.age=15;
        customer.income=50000;
        customer.expenditure=2000;
        form.customerDetails=customer;
        BankLoanService bankLoanService= new BankLoanService();
        bankLoanService.registrationService(form);

        assertNull(form.applicationNumber);
    }

    @Test
    void testRegister_NotEligibleForLoan_IncomeExpenditureEqual() {
        ApplicationForm form = new ApplicationForm();
        CustomerDetails customer = new CustomerDetails();
        customer.age=15;
        customer.income=50000;
        customer.expenditure=50000;
        form.customerDetails=customer;
        BankLoanService bankLoanService= new BankLoanService();
        bankLoanService.registrationService(form);

        assertNull(form.applicationNumber);
    }

    @Test
    void testApproveAmount_ValidCustomer() {
        CustomerDetails customer = new CustomerDetails();
        customer.income=50000;
        customer.expenditure=20000;
        BankLoanService bankLoanService= new BankLoanService();
        LoanDetails loanDetails = bankLoanService.approveAmount(customer);

        assertNotNull(loanDetails);
        assertEquals(300000.0, loanDetails.approvedAmount);
        assertEquals(18000.0, loanDetails.monthlyEmi);
        assertEquals(9.0, loanDetails.rateOfInterest);
    }

    @Test
    void testApproveEmi_EligibleForEmi() {
        CustomerDetails customer = new CustomerDetails();
        BankLoanService bankLoanService= new BankLoanService();
        customer.income=(5000);
        customer.expenditure=(2000);
        customer.selectedTenure=(12);

        bankLoanService.emiApproval(customer);

    }

    @Test
    void testApproveEmi_NotEligibleForEmi_Tenure() {
        CustomerDetails customer = new CustomerDetails();
        BankLoanService bankLoanService= new BankLoanService();
        customer.income=(50000);
        customer.expenditure=(20000);
        customer.selectedTenure=(9);

        bankLoanService.emiApproval(customer);

    }


}


