package com.lw.wanandroid.bean;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.lw.wanandroid.utils.GsonUtils;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by lw on 2018/1/22.
 */
@Route(path = "/service/json")
public class KnowledgeSystem implements SerializationService {

    private int id;
    private String name;
    private int courseId;
    private int parentChapterId;
    private int order;
    private int visible;
    private List<ChildrenBean> children;

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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    @Override
    public <T> T json2Object(String input, Class<T> clazz) {
        return GsonUtils.convertObj(input, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return GsonUtils.toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return GsonUtils.convertObj(input, clazz);
    }

    @Override
    public void init(Context context) {

    }

    public static class ChildrenBean implements SerializationService {
        /**
         * id : 60
         * name : Android Studio相关
         * courseId : 13
         * parentChapterId : 150
         * order : 1000
         * visible : 1
         * children : []
         */

        private int id;
        private String name;
        private int courseId;
        private int parentChapterId;
        private int order;
        private int visible;
        private List<ChildrenBean> children;

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

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public int getParentChapterId() {
            return parentChapterId;
        }

        public void setParentChapterId(int parentChapterId) {
            this.parentChapterId = parentChapterId;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        @Override
        public <T> T json2Object(String input, Class<T> clazz) {
            return GsonUtils.convertObj(input, clazz);
        }

        @Override
        public String object2Json(Object instance) {
            return GsonUtils.toJson(instance);
        }

        @Override
        public <T> T parseObject(String input, Type clazz) {
            return GsonUtils.convertObj(input, clazz);
        }

        @Override
        public void init(Context context) {

        }

        public ChildrenBean(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }


}
