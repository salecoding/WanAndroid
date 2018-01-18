package com.lw.wanandroid.bean;

import java.util.List;

/**
 * Created by lw on 2018/1/18.
 */

public class Article {
    /**
     * errorCode : 0
     * errorMsg : null
     * data : {"datas":[{"id":1578,"title":"这些 Drawable 的小技巧，你都了解吗？","chapterId":168,"chapterName":"Drawable","envelopePic":null,"link":"https://juejin.im/post/5a28b2d0f265da431c703153","author":" 承香墨影","origin":null,"publishTime":1512660849000,"zan":null,"desc":null,"visible":1,"niceDate":"2017-12-07","courseId":13,"collect":false},{"id":1544,"title":"Android drawable微技巧，你所不知道的drawable的那些细节","chapterId":168,"chapterName":"Drawable","envelopePic":null,"link":"http://blog.csdn.net/guolin_blog/article/details/50727753","author":"郭霖","origin":null,"publishTime":1511934940000,"zan":null,"desc":null,"visible":1,"niceDate":"2017-11-29","courseId":13,"collect":false},{"id":1476,"title":"美工死不瞑目系列之SVG推锅技巧！","chapterId":168,"chapterName":"Drawable","envelopePic":null,"link":"http://www.jianshu.com/p/ad9b7382aecb","author":"吴愣","origin":null,"publishTime":1509450326000,"zan":0,"desc":null,"visible":1,"niceDate":"2017-10-31","courseId":13,"collect":false},{"id":1289,"title":"Android资源详解","chapterId":168,"chapterName":"Drawable","envelopePic":null,"link":"http://www.jianshu.com/p/8d14d2c25138","author":"发烧的冬瓜","origin":null,"publishTime":1506957747000,"zan":0,"desc":null,"visible":1,"niceDate":"2017-10-02","courseId":13,"collect":false},{"id":1184,"title":"Android中的13种Drawable小结 Part 2","chapterId":168,"chapterName":"Drawable","envelopePic":null,"link":"http://blog.csdn.net/coder_pig/article/details/49008397","author":"coder-pig","origin":null,"publishTime":1506652976000,"zan":0,"desc":null,"visible":1,"niceDate":"2017-09-29","courseId":13,"collect":false},{"id":1183,"title":"Android中的13种Drawable小结 Part 1","chapterId":168,"chapterName":"Drawable","envelopePic":null,"link":"http://blog.csdn.net/coder_pig/article/details/49006217","author":"coder-pig","origin":null,"publishTime":1506652873000,"zan":0,"desc":null,"visible":1,"niceDate":"2017-09-29","courseId":13,"collect":false},{"id":1182,"title":"Android Drawable 那些不为人知的高效用法","chapterId":168,"chapterName":"Drawable","envelopePic":null,"link":"http://blog.csdn.net/lmj623565791/article/details/43752383","author":"鸿洋","origin":null,"publishTime":1506652498000,"zan":0,"desc":null,"visible":1,"niceDate":"2017-09-29","courseId":13,"collect":false},{"id":983,"title":"Android样式的开发:shape篇","chapterId":168,"chapterName":"Drawable","envelopePic":"","link":"http://keeganlee.me/post/android/20150830","author":"Keegan小钢","origin":"http://keeganlee.me/","publishTime":1472901417000,"zan":0,"desc":"一个应用，应该保持一套统一的样式，包括Button、EditText、ProgressBar、Toast、Checkbox等各种控件的样式，还包括控件间隔、文字大小和颜色、阴影等等。web的样式用css来定义，而android的样式主要则是通过shape、selector、layer-list、level-list、style、theme等组合实现。我将用一系列文章，循序渐进地讲解样式的每个方面该如何实现。第一个要讲的就是shape，最基础的形状定义工具。","visible":1,"niceDate":"2016-09-03","courseId":13,"collect":false},{"id":739,"title":"用RotateDrawable实现网易云音乐唱片机效果","chapterId":168,"chapterName":"Drawable","envelopePic":null,"link":"http://www.jianshu.com/p/0e0de2cdd2bb","author":"码农小阿飞","origin":"简书","publishTime":1466484108000,"zan":0,"desc":null,"visible":1,"niceDate":"2016-06-21","courseId":13,"collect":false}],"offset":0,"size":20,"total":9,"pageCount":1,"curPage":1,"over":true}
     */

    private int errorCode;
    private Object errorMsg;
    private DataBean data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(Object errorMsg) {
        this.errorMsg = errorMsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * datas : [{"id":1578,"title":"这些 Drawable 的小技巧，你都了解吗？","chapterId":168,"chapterName":"Drawable","envelopePic":null,"link":"https://juejin.im/post/5a28b2d0f265da431c703153","author":" 承香墨影","origin":null,"publishTime":1512660849000,"zan":null,"desc":null,"visible":1,"niceDate":"2017-12-07","courseId":13,"collect":false},{"id":1544,"title":"Android drawable微技巧，你所不知道的drawable的那些细节","chapterId":168,"chapterName":"Drawable","envelopePic":null,"link":"http://blog.csdn.net/guolin_blog/article/details/50727753","author":"郭霖","origin":null,"publishTime":1511934940000,"zan":null,"desc":null,"visible":1,"niceDate":"2017-11-29","courseId":13,"collect":false},{"id":1476,"title":"美工死不瞑目系列之SVG推锅技巧！","chapterId":168,"chapterName":"Drawable","envelopePic":null,"link":"http://www.jianshu.com/p/ad9b7382aecb","author":"吴愣","origin":null,"publishTime":1509450326000,"zan":0,"desc":null,"visible":1,"niceDate":"2017-10-31","courseId":13,"collect":false},{"id":1289,"title":"Android资源详解","chapterId":168,"chapterName":"Drawable","envelopePic":null,"link":"http://www.jianshu.com/p/8d14d2c25138","author":"发烧的冬瓜","origin":null,"publishTime":1506957747000,"zan":0,"desc":null,"visible":1,"niceDate":"2017-10-02","courseId":13,"collect":false},{"id":1184,"title":"Android中的13种Drawable小结 Part 2","chapterId":168,"chapterName":"Drawable","envelopePic":null,"link":"http://blog.csdn.net/coder_pig/article/details/49008397","author":"coder-pig","origin":null,"publishTime":1506652976000,"zan":0,"desc":null,"visible":1,"niceDate":"2017-09-29","courseId":13,"collect":false},{"id":1183,"title":"Android中的13种Drawable小结 Part 1","chapterId":168,"chapterName":"Drawable","envelopePic":null,"link":"http://blog.csdn.net/coder_pig/article/details/49006217","author":"coder-pig","origin":null,"publishTime":1506652873000,"zan":0,"desc":null,"visible":1,"niceDate":"2017-09-29","courseId":13,"collect":false},{"id":1182,"title":"Android Drawable 那些不为人知的高效用法","chapterId":168,"chapterName":"Drawable","envelopePic":null,"link":"http://blog.csdn.net/lmj623565791/article/details/43752383","author":"鸿洋","origin":null,"publishTime":1506652498000,"zan":0,"desc":null,"visible":1,"niceDate":"2017-09-29","courseId":13,"collect":false},{"id":983,"title":"Android样式的开发:shape篇","chapterId":168,"chapterName":"Drawable","envelopePic":"","link":"http://keeganlee.me/post/android/20150830","author":"Keegan小钢","origin":"http://keeganlee.me/","publishTime":1472901417000,"zan":0,"desc":"一个应用，应该保持一套统一的样式，包括Button、EditText、ProgressBar、Toast、Checkbox等各种控件的样式，还包括控件间隔、文字大小和颜色、阴影等等。web的样式用css来定义，而android的样式主要则是通过shape、selector、layer-list、level-list、style、theme等组合实现。我将用一系列文章，循序渐进地讲解样式的每个方面该如何实现。第一个要讲的就是shape，最基础的形状定义工具。","visible":1,"niceDate":"2016-09-03","courseId":13,"collect":false},{"id":739,"title":"用RotateDrawable实现网易云音乐唱片机效果","chapterId":168,"chapterName":"Drawable","envelopePic":null,"link":"http://www.jianshu.com/p/0e0de2cdd2bb","author":"码农小阿飞","origin":"简书","publishTime":1466484108000,"zan":0,"desc":null,"visible":1,"niceDate":"2016-06-21","courseId":13,"collect":false}]
         * offset : 0
         * size : 20
         * total : 9
         * pageCount : 1
         * curPage : 1
         * over : true
         */

        private int offset;
        private int size;
        private int total;
        private int pageCount;
        private int curPage;
        private boolean over;
        private List<DatasBean> datas;

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * id : 1578
             * title : 这些 Drawable 的小技巧，你都了解吗？
             * chapterId : 168
             * chapterName : Drawable
             * envelopePic : null
             * link : https://juejin.im/post/5a28b2d0f265da431c703153
             * author :  承香墨影
             * origin : null
             * publishTime : 1512660849000
             * zan : null
             * desc : null
             * visible : 1
             * niceDate : 2017-12-07
             * courseId : 13
             * collect : false
             */

            private int id;
            private String title;
            private int chapterId;
            private String chapterName;
            private Object envelopePic;
            private String link;
            private String author;
            private Object origin;
            private long publishTime;
            private Object zan;
            private Object desc;
            private int visible;
            private String niceDate;
            private int courseId;
            private boolean collect;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getChapterId() {
                return chapterId;
            }

            public void setChapterId(int chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public Object getEnvelopePic() {
                return envelopePic;
            }

            public void setEnvelopePic(Object envelopePic) {
                this.envelopePic = envelopePic;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public Object getOrigin() {
                return origin;
            }

            public void setOrigin(Object origin) {
                this.origin = origin;
            }

            public long getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(long publishTime) {
                this.publishTime = publishTime;
            }

            public Object getZan() {
                return zan;
            }

            public void setZan(Object zan) {
                this.zan = zan;
            }

            public Object getDesc() {
                return desc;
            }

            public void setDesc(Object desc) {
                this.desc = desc;
            }

            public int getVisible() {
                return visible;
            }

            public void setVisible(int visible) {
                this.visible = visible;
            }

            public String getNiceDate() {
                return niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public int getCourseId() {
                return courseId;
            }

            public void setCourseId(int courseId) {
                this.courseId = courseId;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }
        }
    }
}
