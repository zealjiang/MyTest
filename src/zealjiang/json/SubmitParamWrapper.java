package zealjiang.json;


import java.util.ArrayList;
import java.util.List;


public class SubmitParamWrapper {
    private CustomerInfo customerInfo;
    private CarInfo carInfo;
    private CarCondition carCondition;
    private CarPhoto carPhoto;
    private PriceEvaluation priceEvaluation;
    private List<PhotoItem> photoItems;

    public SubmitParamWrapper(){

    }


    public void clearAllData(){
        customerInfo = null;
        carInfo = null;
        carCondition = null;
        carPhoto = null;
        priceEvaluation = null;
        photoItems = null;
    }


    public List<PhotoItem> getPhotoItems() {
        return photoItems;
    }

    public void setPhotoItems(List<PhotoItem> photoItems) {
        this.photoItems = photoItems;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public CarInfo getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(CarInfo carInfo) {
        this.carInfo = carInfo;
    }

    public CarCondition getCarCondition() {
        return carCondition;
    }

    public void setCarCondition(CarCondition carCondition) {
        this.carCondition = carCondition;
    }

    public CarPhoto getCarPhoto() {
        return carPhoto;
    }

    public void setCarPhoto(CarPhoto carPhoto) {
        this.carPhoto = carPhoto;
    }

    public PriceEvaluation getPriceEvaluation() {
        return priceEvaluation;
    }

    public void setPriceEvaluation(PriceEvaluation priceEvaluation) {
        this.priceEvaluation = priceEvaluation;
    }


    public static class PriceEvaluation{
        private String startPrice;
        private int sellerId;



        public int getSellerId() {
            return sellerId;
        }

        public void setSellerId(int sellerId) {
            sellerId = sellerId;
        }

        public boolean isChecked(){
            return true;
        }

        public PriceEvaluation() {

        }

        public String getStartPrice() {
            return startPrice;
        }

        public void setStartPrice(String startPrice) {
            this.startPrice = startPrice;
        }

  
    }


    public static class PhotoItem{
        private int DictID;
        private String PicPath;
 

        public int getDictID() {
            return DictID;
        }

        public void setDictID(int dictID) {
            DictID = dictID;
        }

        public String getPicPath() {
            return PicPath;
        }

        public void setPicPath(String picPath) {
            PicPath = picPath;
        }

    }


    public static class CarCondition{

        public boolean isChecked(){
            return false;
        }

        public List<String> data;

        public List<String> getData() {
            return data;
        }

        public void setData(List<String> data) {
            this.data = data;
        }
    }


    public static class CarInfo{
        private String vin;
        private CarBaseInfo carBaseInfo;
        private AllocInfo allocInfo;
        private ArrayList<String> allocInfoList;
        private ProcedureInfo procedureInfo;

        public boolean isChecked(){
            return true;
        }

        public String getVin() {
            return vin;
        }

        public void setVin(String vin) {
            this.vin = vin;
        }

        public CarBaseInfo getCarBaseInfo() {
            return carBaseInfo;
        }

        public void setCarBaseInfo(CarBaseInfo carBaseInfo) {
            this.carBaseInfo = carBaseInfo;
        }

        public AllocInfo getAllocInfo() {
            return allocInfo;
        }

        public void setAllocInfo(AllocInfo allocInfo) {
            this.allocInfo = allocInfo;
        }

        public ProcedureInfo getProcedureInfo() {
            return procedureInfo;
        }

        public void setProcedureInfo(ProcedureInfo procedureInfo) {
            this.procedureInfo = procedureInfo;
        }

        public ArrayList<String> getAllocInfoList() {
            return allocInfoList;
        }

        public void setAllocInfoList(ArrayList<String> allocInfoList) {
            this.allocInfoList = allocInfoList;
        }
    }


    public static class CarBaseInfo{
        private String brand;


        public CarBaseInfo() {
        }


		public String getBrand() {
			return brand;
		}


		public void setBrand(String brand) {
			this.brand = brand;
		}

    
    }

    /***
     * 2.2车辆配置信息
     */
    public static class AllocInfo{
        private String abs;

        public AllocInfo() {
        }


        public String getAbs() {
            return abs;
        }

        public void setAbs(String abs) {
            this.abs = abs;
        }


    }

    /***
     * 2.3车辆手续信息
     */
    public static class ProcedureInfo{
        private String yearlyInspectDate;

        public ProcedureInfo() {
        }



        public String getYearlyInspectDate() {
            return yearlyInspectDate;
        }

        public void setYearlyInspectDate(String yearlyInspectDate) {
            this.yearlyInspectDate = yearlyInspectDate;
        }

    
    }

    /***
     * 1.客户资料
     */
    public static class CustomerInfo{
        private int type;
        private String name;


        public CustomerInfo() {

        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        
    }
}
