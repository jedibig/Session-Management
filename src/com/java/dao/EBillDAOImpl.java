package com.java.dao;

import com.java.core.Bill;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EBillDAOImpl implements EBillDAO{
    static Logger logger = Logger.getLogger(EBillDAOImpl.class);
    /**
     * @param bill
     * @return int 0 if successful, 1 if no account found, -1 if database exception
     */
    @Override
    public int inputBill(Bill bill) {
        logger.info("Creating account for user.");
        String create = "insert into BILLDETAILS(bill_num, consumer_num,cur_reading,unitConsumed,netAmount,bill_date) values(seq_bill_num.nextval,?,?,?,?,?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement ps1 = connection.prepareStatement(create);) {

            ps1.setLong(1,bill.getCustomerNum());
            ps1.setDouble(2,bill.getCurrReading());
            ps1.setDouble(3,bill.getUnitConsumed());
            ps1.setDouble(4,bill.getNetAmount());
            ps1.setDate(5,bill.getBillDate());

            logger.info("Executing query.");
            if ( ps1.executeUpdate() <= 0 ){
                return -1;
            }
            connection.commit();
        } catch (SQLException e) {
            if (e.getErrorCode() == 2291)
                return 1;
            e.printStackTrace();
                return -1;
        }
        return 0;
    }
}
