package com.lightning.lgc.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResultBody {
    private int code;
    private Object obj;
    private String msg;
    private String type;//前端提示框样式

    public ResultBody(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultBody(int code, Object obj, String msg) {
        this.code = code;
        this.obj = obj;
        this.msg = msg;
    }

    public ResultBody(int code, String msg, String type) {
        this.code = code;
        this.msg = msg;
        this.type = type;
    }
}

