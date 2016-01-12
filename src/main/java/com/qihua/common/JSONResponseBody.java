package com.qihua.common;

/**
 * Class description goes here.
 *
 * @author aopfilter@163.com
 * @since 2013-10-11
 * @version 1.0
 * @see
 */
public class JSONResponseBody {

    private boolean result = true;
    private Object data;

    public JSONResponseBody() {
    }

    public JSONResponseBody(boolean result) {
        this.result = result;
    }

    public JSONResponseBody(Object data) {
        result = true;
        this.data = data;
    }

    public JSONResponseBody(boolean result, Object data) {
        this.result = result;
        this.data = data;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
