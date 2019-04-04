package org.yinan.mysql.entity;

/**
 * @author yinan
 * @date created in 下午2:00 19-4-4
 */
public class Amount {

    private int id;

    private String serialNo;

    private int money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Amount{");
        sb.append("id=").append(id);
        sb.append(", serialNo='").append(serialNo).append('\'');
        sb.append(", money=").append(money);
        sb.append('}');
        return sb.toString();
    }
}
