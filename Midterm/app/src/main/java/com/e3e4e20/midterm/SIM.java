package com.e3e4e20.midterm;

/*
 * Filename: com.e3e4e20.midterm
 * Description:
 * Version: 1.0
 * Created: 2019/11/8 15:59 星期五
 * Revision: none
 * Compiler:
 * Author: DreamSnow·Draco
 * Company:
 * */
public class SIM {
    // 电话卡卡号
    private String SIMNumber = "18779916319";
    // 电话卡密码
    private String SIMPassword = "123456";
    // 电话卡余额
    private double SIMMoney = 13.14;

    public String getSIMNumber() {
        return SIMNumber;
    }

    public void setSIMNumber(String SIMNumber) {
        this.SIMNumber = SIMNumber;
    }

    public String getSIMPassword() {
        return SIMPassword;
    }

    public void setSIMPassword(String SIMPassword) {
        this.SIMPassword = SIMPassword;
    }

    public double getSIMMoney() {
        return SIMMoney;
    }

    public void setSIMMoney(double SIMMoney) {
        this.SIMMoney = SIMMoney;
    }
}
