package com.java.service;

import com.java.core.Bill;
import com.java.dao.EBillDAO;
import com.java.dao.EBillDAOImpl;
import com.java.exception.DatabaseException;
import com.java.exception.NoAccountFoundException;
import org.apache.log4j.Logger;

import java.sql.Date;
import java.time.LocalDate;

public class EBillServiceImpl implements EBillService {
    static Logger logger = Logger.getLogger(EBillService.class);

    static EBillDAO eBillDAO;
    static final double FIXED_CHARGE = 100;

    static {
        eBillDAO = new EBillDAOImpl();
    }
    @Override
    public Bill calculateBill(Bill bill) throws NoAccountFoundException, DatabaseException {
        double unitConsumed = bill.getCurrReading() - bill.getPrevReading();
        double netAmount = unitConsumed * 1.15 + FIXED_CHARGE;

        bill.setUnitConsumed(unitConsumed);
        bill.setNetAmount(netAmount);
        bill.setBillDate(Date.valueOf(LocalDate.now()));

        int retVal = eBillDAO.inputBill(bill);
        switch (retVal) {
            case 0:
                logger.info("Success in database insertion.");
                break;
            case 1:
                logger.info("No Account found.");
                throw new NoAccountFoundException();
            case -1:
                logger.error("Database Error");
                throw new DatabaseException();
        }
        return bill;
    }
}
