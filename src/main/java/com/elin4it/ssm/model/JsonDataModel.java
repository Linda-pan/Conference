package com.elin4it.ssm.model;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;

import java.util.Date;

/**
 * Created by jpan on 2016/4/28.
 */
public class JsonDataModel {
    private String status = "true";
    private String message = "success";
    private String errorCode = "0000";
    private Object data;

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public String getStatus() {
        return status;
    }

    public String toJSONString() {
        JsonConfig jsonConfig = new JsonConfig();
        // 设置转换成json字符串时Integer 为null时返回null，而不是0

        jsonConfig.registerDefaultValueProcessor(Integer.class, new DefaultValueProcessor() {
            public Object getDefaultValue(Class type) {
                return null;
            }
        });
        // 设置转换成json字符串时Double 为null时返回null，而不是0
        jsonConfig.registerDefaultValueProcessor(Double.class, new DefaultValueProcessor() {
            public Object getDefaultValue(Class type) {
                return null;
            }
        });
        jsonConfig.registerDefaultValueProcessor(Date.class, new DefaultValueProcessor() {
            public Object getDefaultValue(Class type) {
                return null;
            }
        });
        // 设置转换成json字符串时String 为null时返回null，而不是""
        jsonConfig.registerDefaultValueProcessor(String.class, new DefaultValueProcessor() {
            public Object getDefaultValue(Class type) {
                return null;
            }
        });
        // 设置转换成json字符串时Boolean 为null时返回null，而不是false
        jsonConfig.registerDefaultValueProcessor(Boolean.class, new DefaultValueProcessor() {
            public Object getDefaultValue(Class type) {
                return null;
            }
        });
        // 设置转换成json字符串时Short 为null时返回null，而不是0
        jsonConfig.registerDefaultValueProcessor(Short.class, new DefaultValueProcessor() {
            public Object getDefaultValue(Class type) {
                return null;
            }
        });
        return JSONObject.fromObject(this, jsonConfig).toString();
    }
}
