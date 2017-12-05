package zealjiang.json;

import java.util.List;


public class CarPhoto {


    private List<DataBean> Data;

    public List<DataBean> getData() {
        return Data;
    }

    public void setData(List<DataBean> Data) {
        this.Data = Data;
    }

    public static class DataBean {
        private String Path;
        private String PicPath;

        public String getPath() {
            return Path;
        }

        public void setPath(String Path) {
            this.Path = Path;
        }

        public String getPicPath() {
            return PicPath;
        }

        public void setPicPath(String PicPath) {
            this.PicPath = PicPath;
        }
    }
}
