package com.java.service;

import com.java.core.Bill;
import com.java.core.User;
import com.java.exception.DatabaseException;
import com.java.exception.NoAccountFoundException;

public interface EBillService {
    Bill calculateBill(Bill user) throws DatabaseException, NoAccountFoundException;
}
