package me.bugsrain.test1.api;

import java.util.List;

import chengxinet.chengxilibs.entity.Entity;
import me.bugsrain.test1.entity.Doctor;
import me.bugsrain.test1.entity.User;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/3/23.
 */
public interface ApiService {
    @GET("User/login")
    Observable<Entity<User>> login(@Query("phone") String username, @Query("password") String password);

    @GET("user/doctorList")
    Observable<Entity<List<Doctor>>> getDoctor(@Query("page") int page);



//    @GET("User/login")
//    Observable<Entity> login2(@Query("phone") String username, @Query("password") String password);

//    @POST("users")
//    Observable<CreatedResult> createUser(@Body _User user);
//
//
//    @GET("users")
//    Observable<Data<_User>> getAllUser(@Query("skip") int skip, @Query("limit") int limit);
//
//    @GET("classes/Image")
//    Observable<Data<Image>> getAllImages(@Query("where") String where, @Query("order") String order, @Query("skip") int skip, @Query("limit") int limit);
//
//
//    @GET("classes/Comment")
//    Observable<Data<CommentInfo>> getCommentList(@Query("include") String include, @Query("where") String where, @Query("skip") int skip, @Query("limit") int limit);
//
//
//    @POST("classes/Comment")
//    Observable<CreatedResult> createComment(@Body Comment mComment);
//
//
//    @Headers("Content-Type: image/png")
//    @POST("files/{name}")
//    Observable<CreatedResult> upFile(@Path("name") String name, @Body RequestBody body);
//
//
//    @PUT("users/{uid}")
//    Observable<CreatedResult> upUser(@Header("X-LC-Session") String session, @Path("uid") String uid, @Body UserModel.Face face);
}
