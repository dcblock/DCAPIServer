package com.digicap.dcblock.caffeapiserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedList;

@Getter
@AllArgsConstructor
public class PurchasePageVo {

    private int total_pages;

    private LinkedList<PurchaseVo> list;
}
