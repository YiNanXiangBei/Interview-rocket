package org.yinan.rabbit.model;

import java.io.Serializable;

/**
 * @author yinan
 * @date 19-3-28
 */
public class Amount implements Serializable {

    private static final long serialVersionUID = 5468335797443850679L;

    private String serialNo;

    private String money;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Amount{");
        sb.append("serialNo='").append(serialNo).append('\'');
        sb.append(", money='").append(money).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
