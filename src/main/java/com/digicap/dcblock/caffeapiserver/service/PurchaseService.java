package com.digicap.dcblock.caffeapiserver.service;

import com.digicap.dcblock.caffeapiserver.dto.*;
import com.digicap.dcblock.caffeapiserver.type.PurchaseType;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public interface PurchaseService {

    ReceiptIdDto getReceiptId(String rfid);

    PurchasedDto requestPurchases(int receiptId, PurchaseType type, List<LinkedHashMap<String, Object>> purchases);

    LinkedList<PurchaseVo> cancelPurchases(int receiptId, String rfid);

    List<PurchaseVo> cancelApprovalPurchases(int receiptId, Timestamp purchaseDate);

    LinkedList<PurchaseVo> getPurchases(PurchaseDto purchaseDto, Timestamp before, Timestamp after);

    PurchaseBalanceDto getBalanceByRfid(String rfid, Timestamp before, Timestamp after);

//    LinkedList<PurchaseSearchDto> getPurchasesBySearch(PurchaseWhere w);

    PurchaseSearchPageDto getPurchasesBySearch(PurchaseWhere w);
}
