package com.cheese.radio.ui.user.enroll;

public class CreateOrderWXEntity {
        /**
         * orderNo : 584490804785975296
         * prepareId : wx13131402164450aaade914c82492698689
         * paySign : C04AD766FE289D5D812F18766FFCA313
         * payOrderCode : 20180513-73480
         * partnerId : 208115441
         * nonceStr : 752e98d1c7784e00bf7ae8b154769982
         * timestamp : 1526188442
         */

        private String orderNo;
        private String prepareId;
        private String paySign;
        private String payOrderCode;
        private String partnerId;
        private String nonceStr;
        private String timestamp;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getPrepareId() {
            return prepareId;
        }

        public void setPrepareId(String prepareId) {
            this.prepareId = prepareId;
        }

        public String getPaySign() {
            return paySign;
        }

        public void setPaySign(String paySign) {
            this.paySign = paySign;
        }

        public String getPayOrderCode() {
            return payOrderCode;
        }

        public void setPayOrderCode(String payOrderCode) {
            this.payOrderCode = payOrderCode;
        }

        public String getPartnerId() {
            return partnerId;
        }

        public void setPartnerId(String partnerId) {
            this.partnerId = partnerId;
        }

        public String getNonceStr() {
            return nonceStr;
        }

        public void setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }

