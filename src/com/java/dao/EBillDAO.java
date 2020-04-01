package com.java.dao;

import com.java.core.Bill;

public interface EBillDAO {
    /**
     *
     * @param bill
     * @return int 0 if successful, 1 if no account found, -1 if database exception
     */
    int inputBill(Bill bill) ;
}
