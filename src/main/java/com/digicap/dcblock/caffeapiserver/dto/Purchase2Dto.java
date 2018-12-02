package com.digicap.dcblock.caffeapiserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class Purchase2Dto {

    @NonNull
    private int category;

    @NonNull
    private int code;

    @NonNull
    private int price;

    @NonNull
    private int dc_price;

    @NonNull
    private String type;

    @NonNull
    private String size;

    @NonNull
    private int count;

    @NonNull
    private String menu_name_kr;

    @JsonIgnore
    private String name;

    @NonNull
    private long user_record_index;

    @JsonIgnore
    private int receipt_id;

    @NonNull
    private int receipt_status;
}
