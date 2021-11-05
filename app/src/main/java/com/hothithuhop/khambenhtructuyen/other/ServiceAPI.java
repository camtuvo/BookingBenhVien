package com.hothithuhop.khambenhtructuyen.other;

import com.hothithuhop.khambenhtructuyen.model.Bacsi;
import com.hothithuhop.khambenhtructuyen.model.BillResponse;
import com.hothithuhop.khambenhtructuyen.model.Chuyenkhoa;
import com.hothithuhop.khambenhtructuyen.model.Dangky;
import com.hothithuhop.khambenhtructuyen.model.DatKham;
import com.hothithuhop.khambenhtructuyen.model.Hoadon;
import com.hothithuhop.khambenhtructuyen.model.InfUser;
import com.hothithuhop.khambenhtructuyen.model.Info;
import com.hothithuhop.khambenhtructuyen.model.MessageLogin;
import com.hothithuhop.khambenhtructuyen.model.Notification;
import com.hothithuhop.khambenhtructuyen.model.RegisterResponse;
import com.hothithuhop.khambenhtructuyen.model.UpdateUser;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceAPI {
//    ngrok http -host-header=localhost https://localhost:
    String BASE_Service = "http://datlichkham.somee.com/api/";
//    String BASE_Service = "https://9bad-113-172-37-42.ngrok.io/api/";

    @GET("get-hoadon")
    Observable<Notification> GetHoadon(@Query("id") int id);

    @GET("get-bacsi")
    Observable<ArrayList<Bacsi>> GetDoctor(@Query("id") int id);

    @GET("get-chuyenkhoa")
    Observable<ArrayList<Chuyenkhoa>> GetChuyenkhoa();

    @POST("login")
    Observable<MessageLogin> Login(@Body Info info);

    @POST("register")
    Observable<RegisterResponse> Register(@Body Dangky dangky);

    @POST("create-bill")
    Observable<Notification> BookAPI(@Body DatKham datKham);

    @GET("get-inf")
    Observable<InfUser> GetInf(@Query("id") int id);

    @POST("update-user")
    Observable<MessageLogin> UpdateUser(@Query("id") int id, @Body UpdateUser updateUser);

    @POST("bill")
    Observable<ArrayList<Hoadon>> Bill(@Query("id") int id);

    @POST("history-bill")
    Observable<ArrayList<BillResponse>> HistoryBill(@Query("id") int id);
}