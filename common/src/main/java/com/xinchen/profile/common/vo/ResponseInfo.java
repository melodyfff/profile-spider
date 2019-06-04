package com.xinchen.profile.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author xinchen
 * @version 1.0
 * @date 29/04/2019 10:57
 */
@Data
public class ResponseInfo {

    @JSONField(name = "data")
    private Data data;


    @lombok.Data
    class Data {
        @JSONField(name = "employee")
        private Employee employee;


        @lombok.Data
        class Employee {
            @JSONField(name = "academy")
            private String academy;
            @JSONField(name = "birthday")
            private String birthday;
            @JSONField(name = "birthland")
            private String birthland;
            @JSONField(name = "dept")
            private String dept;
            @JSONField(name = "education")
            private String education;
            @JSONField(name = "email")
            private String email;
            @JSONField(name = "employmentTime")
            private String employmentTime;
            @JSONField(name = "ip")
            private Ip ip;
            @JSONField(name = "name")
            private String name;
            @JSONField(name = "nation")
            private String nation;
            @JSONField(name = "nativeplace")
            private String nativeplace;
            @JSONField(name = "no")
            private String no;
            @JSONField(name = "orgs")
            private List<Org> orgs;
            @JSONField(name = "pgraduatetime")
            private String pgraduatetime;
            @JSONField(name = "pgraduatetime")
            private String pl;
            @JSONField(name = "pleaveTime")
            private String pleaveTime;
            @JSONField(name = "plevelChangeTime")
            private String plevelChangeTime;
            @JSONField(name = "pmajor")
            private String pmajor;
            @JSONField(name = "politicsstatus")
            private String politicsstatus;
            @JSONField(name = "ppositiveStatus")
            private String ppositiveStatus;
            @JSONField(name = "ppositiveTime")
            private String ppositiveTime;
            @JSONField(name = "ps")
            private String ps;
            @JSONField(name = "pteamtime")
            private String pteamtime;
            @JSONField(name = "pworkDivide")
            private String pworkDivide;
            @JSONField(name = "pworktime")
            private String pworktime;
            @JSONField(name = "sex")
            private String sex;
            @JSONField(name = "telephone")
            private String telephone;
            @JSONField(name = "profile")
            private Profile profile;


            class Ip {
                private String ipAddress;

                public String getIpAddress() {
                    return ipAddress;
                }

                public void setIpAddress(String ipAddress) {
                    this.ipAddress = ipAddress;
                }

                @Override
                public String toString() {
                    return "Ip{" +
                            "ipAddress='" + ipAddress + '\'' +
                            '}';
                }
            }

            class Profile{
                private String mobile;
                private String workArea;

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getWorkArea() {
                    return workArea;
                }

                public void setWorkArea(String workArea) {
                    this.workArea = workArea;
                }

                @Override
                public String toString() {
                    return "Profile{" +
                            "mobile='" + mobile + '\'' +
                            ", workArea='" + workArea + '\'' +
                            '}';
                }
            }
            class Org{
                private String orgName;
                private String orgNo;
                private String orgTypeName;
                private String orgTypeNo;
                private String parentOrgNo;

                public String getOrgName() {
                    return orgName;
                }

                public void setOrgName(String orgName) {
                    this.orgName = orgName;
                }

                public String getOrgNo() {
                    return orgNo;
                }

                public void setOrgNo(String orgNo) {
                    this.orgNo = orgNo;
                }

                public String getOrgTypeName() {
                    return orgTypeName;
                }

                public void setOrgTypeName(String orgTypeName) {
                    this.orgTypeName = orgTypeName;
                }

                public String getOrgTypeNo() {
                    return orgTypeNo;
                }

                public void setOrgTypeNo(String orgTypeNo) {
                    this.orgTypeNo = orgTypeNo;
                }

                public String getParentOrgNo() {
                    return parentOrgNo;
                }

                public void setParentOrgNo(String parentOrgNo) {
                    this.parentOrgNo = parentOrgNo;
                }

                @Override
                public String toString() {
                    return "Org{" +
                            "orgName='" + orgName + '\'' +
                            ", orgNo='" + orgNo + '\'' +
                            ", orgTypeName='" + orgTypeName + '\'' +
                            ", orgTypeNo='" + orgTypeNo + '\'' +
                            ", parentOrgNo='" + parentOrgNo + '\'' +
                            '}';
                }
            }
        }
    }


}


