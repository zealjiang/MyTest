package zealjiang.json;

import java.util.List;


public class SubmitFinalJson {



    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        private Customer customer;
        private CarInfo carinfo;
        private CarPrice carPrice;
        private List<String> carCI;

        private List<SubmitParamWrapper.PhotoItem> carPic;
        private List<String> carCheck;


        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public CarInfo getCarinfo() {
            return carinfo;
        }

        public void setCarinfo(CarInfo carinfo) {
            this.carinfo = carinfo;
        }

        public CarPrice getCarPrice() {
            return carPrice;
        }

        public void setCarPrice(CarPrice carPrice) {
            this.carPrice = carPrice;
        }

        public List<String> getCarCI() {
            return carCI;
        }

        public void setCarCI(List<String> carCI) {
            this.carCI = carCI;
        }

        public List<SubmitParamWrapper.PhotoItem> getCarPic() {
            return carPic;
        }

        public void setCarPic(List<SubmitParamWrapper.PhotoItem> carPic) {
            this.carPic = carPic;
        }

        public List<String> getCarCheck() {
            return carCheck;
        }

        public void setCarCheck(List<String> carCheck) {
            this.carCheck = carCheck;
        }

        public static class Customer {
            private int SellerId;
            private String CustomerName;


            public Customer() {
            }

            public Customer(int sellerId, String customerName, String mobile, String replaceStyle, String gender, String customerWantPrice, String sellcarLevel, String customerSource, String address, String presellTime, String customerType, String contact, String createUserName, String saleID, String saleName) {
                SellerId = sellerId;
                CustomerName = customerName;
            }

            public int getSellerId() {
                return SellerId;
            }

            public void setSellerId(int SellerId) {
                this.SellerId = SellerId;
            }

            public String getCustomerName() {
                return CustomerName;
            }

            public void setCustomerName(String CustomerName) {
                this.CustomerName = CustomerName;
            }

          
        }

        public static class CarInfo {
            private int ID;
            private String VIN;


            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

  
            public String getVIN() {
                return VIN;
            }

            public void setVIN(String VIN) {
                this.VIN = VIN;
            }

  
        }

        public static class CarPrice {
            private String  CreateUserId;


            public CarPrice(String createUserId, String createUserName, String pingguPriceMin, String pingguPriceMax, String pingguUserId, String pingguUserName, String storeId, String storeName, String remark, String updateUserName) {
                CreateUserId = createUserId;

            }

  
            public String getCreateUserId() {
                return CreateUserId;
            }

            public void setCreateUserId(String CreateUserId) {
                this.CreateUserId = CreateUserId;
            }

        }
    }

}
