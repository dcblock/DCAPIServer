package com.digicap.dcblock.caffeapiserver.service;

import com.digicap.dcblock.caffeapiserver.dao.UserDao;
import com.digicap.dcblock.caffeapiserver.dto.ReceiptIdDto;
import com.digicap.dcblock.caffeapiserver.exception.NotFindException;
import com.digicap.dcblock.caffeapiserver.store.PurchaseMapper;
import com.digicap.dcblock.caffeapiserver.store.UserMapper;
import com.digicap.dcblock.caffeapiserver.util.TimeFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PurchaseService {

    private UserMapper userMapper;

    private PurchaseMapper purchaseMapper;

    @Autowired
    public PurchaseService(UserMapper userMapper, PurchaseMapper purchaseMapper) {
        this.userMapper = userMapper;

        this.purchaseMapper = purchaseMapper;
    }

    public ReceiptIdDto getReceiptId(String rfid) {
        UserDao userDao = null;

        try {
            userDao = userMapper.existUserByRfid(rfid);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

        if (userDao == null) {
            throw new NotFindException("not find user using rfid");
        }

        ReceiptIdDto receiptIdDto = new ReceiptIdDto();

        int receptId = 0;

        try {
            receptId = purchaseMapper.getReceptId();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

        receiptIdDto.setReceipt_id(insertZeroString(receptId));
        receiptIdDto.setName(userDao.getName());
        receiptIdDto.setDate(new TimeFormat().getCurrent());

        return receiptIdDto;
    }

    private String insertZeroString(int number) {
        int length = String.valueOf(number).length();

        String value = String.valueOf(number);

        if (length == 3) {
            return "0" + value;
        } else if (length == 2) {
            return "00" + value;
        } else if (length == 1) {
            return "000" + value;
        } else {
            return value;
        }
    }
}