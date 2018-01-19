package com.lw.wanandroid.net;

import com.lw.wanandroid.bean.Article;
import com.lw.wanandroid.bean.Banner;
import com.lw.wanandroid.bean.DataResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by lw on 2018/1/19.
 */

public interface HomeApiService {

    /**
     * 首页数据
     * http://www.wanandroid.com/article/list/0/json
     *
     * @param page page
     */
    @GET("/article/list/{page}/json")
    Observable<DataResponse<Article>> getHomeArticles(@Path("page") int page);

    /**
     * 首页Banner
     * @return BannerResponse
     */
    @GET("/banner/json")
    Observable<DataResponse<List<Banner>>> getHomeBanners();
}
