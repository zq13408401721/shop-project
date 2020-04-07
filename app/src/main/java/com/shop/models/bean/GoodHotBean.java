package com.shop.models.bean;

import java.util.List;

public class GoodHotBean {

    /**
     * errno : 0
     * errmsg :
     * data : {"count":1,"totalPages":1,"pageSize":10,"currentPage":1,"data":[{"id":1152101,"name":"魔兽世界 部落 奥格瑞玛 拉杆箱 可登机","list_pic_url":"http://yanxuan.nosdn.127.net/c1c62211a17b71a634fa0c705d11fb42.png","retail_price":888}],"filterCategory":[{"id":0,"name":"全部","checked":false},{"id":1005000,"name":"居家","checked":false},{"id":1008000,"name":"配件","checked":true},{"id":1005002,"name":"饮食","checked":false},{"id":1019000,"name":"志趣","checked":false}],"goodsList":[{"id":1152101,"name":"魔兽世界 部落 奥格瑞玛 拉杆箱 可登机","list_pic_url":"http://yanxuan.nosdn.127.net/c1c62211a17b71a634fa0c705d11fb42.png","retail_price":888}]}
     */

    private int errno;
    private String errmsg;
    private DataBeanX data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * count : 1
         * totalPages : 1
         * pageSize : 10
         * currentPage : 1
         * data : [{"id":1152101,"name":"魔兽世界 部落 奥格瑞玛 拉杆箱 可登机","list_pic_url":"http://yanxuan.nosdn.127.net/c1c62211a17b71a634fa0c705d11fb42.png","retail_price":888}]
         * filterCategory : [{"id":0,"name":"全部","checked":false},{"id":1005000,"name":"居家","checked":false},{"id":1008000,"name":"配件","checked":true},{"id":1005002,"name":"饮食","checked":false},{"id":1019000,"name":"志趣","checked":false}]
         * goodsList : [{"id":1152101,"name":"魔兽世界 部落 奥格瑞玛 拉杆箱 可登机","list_pic_url":"http://yanxuan.nosdn.127.net/c1c62211a17b71a634fa0c705d11fb42.png","retail_price":888}]
         */

        private int count;
        private int totalPages;
        private int pageSize;
        private int currentPage;
        private List<DataBean> data;
        private List<FilterCategoryBean> filterCategory;
        private List<GoodsListBean> goodsList;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public List<FilterCategoryBean> getFilterCategory() {
            return filterCategory;
        }

        public void setFilterCategory(List<FilterCategoryBean> filterCategory) {
            this.filterCategory = filterCategory;
        }

        public List<GoodsListBean> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<GoodsListBean> goodsList) {
            this.goodsList = goodsList;
        }

        public static class DataBean {
            /**
             * id : 1152101
             * name : 魔兽世界 部落 奥格瑞玛 拉杆箱 可登机
             * list_pic_url : http://yanxuan.nosdn.127.net/c1c62211a17b71a634fa0c705d11fb42.png
             * retail_price : 888
             */

            private int id;
            private String name;
            private String list_pic_url;
            private int retail_price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getList_pic_url() {
                return list_pic_url;
            }

            public void setList_pic_url(String list_pic_url) {
                this.list_pic_url = list_pic_url;
            }

            public int getRetail_price() {
                return retail_price;
            }

            public void setRetail_price(int retail_price) {
                this.retail_price = retail_price;
            }
        }

        public static class FilterCategoryBean {
            /**
             * id : 0
             * name : 全部
             * checked : false
             */

            private int id;
            private String name;
            private boolean checked;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isChecked() {
                return checked;
            }

            public void setChecked(boolean checked) {
                this.checked = checked;
            }
        }

        public static class GoodsListBean {
            /**
             * id : 1152101
             * name : 魔兽世界 部落 奥格瑞玛 拉杆箱 可登机
             * list_pic_url : http://yanxuan.nosdn.127.net/c1c62211a17b71a634fa0c705d11fb42.png
             * retail_price : 888
             */

            private int id;
            private String name;
            private String list_pic_url;
            private int retail_price;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getList_pic_url() {
                return list_pic_url;
            }

            public void setList_pic_url(String list_pic_url) {
                this.list_pic_url = list_pic_url;
            }

            public int getRetail_price() {
                return retail_price;
            }

            public void setRetail_price(int retail_price) {
                this.retail_price = retail_price;
            }
        }
    }
}
