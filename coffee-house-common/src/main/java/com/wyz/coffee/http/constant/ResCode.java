package com.wyz.coffee.http.constant;


import com.wyz.coffee.http.bean.dto.Response;

public enum ResCode {

    R100("100", "成功"),
    R101("101", "SUCCESS"),
    R102("102", "FIAL"),

    /**
     * 64000-64099为系统级错误码
     */
    R64000("64000", "系统错误，请稍候再试"),
    R64001("64001", "数据库错误"),
    R64002("64002", "Redis错误"),
    R64003("64003", "文件读写错误"),
    R64004("64004", "配置文件出错"),
    R64005("64005", "HTTP请求方式错误"),
    R64006("64006", "序列化错误"),
    R64007("64007", "json格式化错误"),
    R64008("64008", "xml格式化错误"),
    R64009("64009", "缺少参数或格式错误,请检查"),
    R64010("64010", "签名校验失败"),
    R64011("64011", "http请求失败"),
    R64012("64012", "第三方接口调用失败"),
    R64013("64013", "区域分析错误"),


    R600("600", "账号或密码错误"),
    R601("601", "密码错误"),
    R602("602", "非法修改他人信息"),
    R603("603", "账号已存在"),
    R604("604", "未登录"),
    R605("605", "不可删除该角色"),
    R606("606", "权限不足"),
    R607("607", "参数为空")


    ;

    private String code;
    private String msg;

    private ResCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public <T> Response<T> build() {
        return new Response<>(this.code, this.msg);
    }

    public String code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }
}
