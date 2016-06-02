package com.snf.console;

/**
 * Created by ngadre on 6/2/16.
 */

/** convenient "-flag opt" combination */
public class Option {
    String flag, opt;
    public Option(String flag, String opt) { this.flag = flag ; this.opt = opt; }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }
}