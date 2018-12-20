package com.digicap.dcblock.caffeapiserver.service;

import com.digicap.dcblock.caffeapiserver.dto.*;
import com.digicap.dcblock.caffeapiserver.exception.ExpiredTimeException;
import com.digicap.dcblock.caffeapiserver.exception.InvalidParameterException;
import com.digicap.dcblock.caffeapiserver.exception.NotFindException;
import com.digicap.dcblock.caffeapiserver.exception.UnknownException;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.mybatis.spring.MyBatisSystemException;

public interface PurchaseService {

    ReceiptIdDto getReceiptId(String rfid);

    PurchasedDto requestPurchases(int receiptId, List<LinkedHashMap<String, Object>> purchases);

    List<PurchaseDto> cancelPurchases(int receiptId);

    List<PurchaseDto> cancelApprovalPurchases(int receiptId, Timestamp purchaseDate);

    LinkedList<PurchaseVo> getPurchases(PurchaseDto purchaseDto, Timestamp from, Timestamp to);

    PurchaseBalanceDto getBalanceByRfid(String rfid, Timestamp from, Timestamp to);

    LinkedHashMap<String, LinkedHashMap<String, LinkedList<PurchaseSearchDto>>> getPurchasesBySearch(
            Timestamp after, Timestamp before, int filter, int userRecordIndex);
}
