package com.example.relaystore.api;

import com.example.relaystore.test_model.ProductAvlShop;
import com.example.relaystore.test_model.SearchOrder;
import com.example.relaystore.test_model.UserDetails;
import com.example.relaystore.test_model.UserList;
import com.example.relaystore.test_model.UserOrderDetails;
import com.example.relaystore.test_model.UserOrderListDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    String BASE_URL = "http://dhakadsoft.com/farmkey_api/";

    /*@GET("relay_store_get_register")
    Call<List<Users>> getUsers();*/

    /*
     *     Register New User
     */
 /*   @FormUrlEncoded
    @POST("relay_store_register")
    Call<DefaultResponse> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("cpass") String cpass
    );
*/
    /*
     *     Login User
     */

    @FormUrlEncoded
    @POST("relay_store_login")
    Call<DefaultResponse> userLogin(
            @Field("email") String email,
            @Field("password") String pass
    );


    /*
     *     Update user
     *
     */

//    @FormUrlEncoded
//    @POST("relat_store_user_update")
//    Call<DefaultResponse> updateUser(
//            @Field("f_name") String f_name,
//            @Field("street_no") String street_no,
//            @Field("email") String email,
//            @Field("m_number") String m_number
//    );

    @FormUrlEncoded
    @POST("relay_store_get_register")
    Call<UserDetails> getUserDetails(
            @Field("email") String email,
            @Field("pass") String pass
    );

    /*
     * Products Available Shops
     * */

/*    @GET("product_avl_shop")
    Call<List<ProductAvlShop>> getProducts();*/

/*

    */
/*
     * List Of Users that order something
     * *//*



    @GET("relay_order_list")
    Call<List<UserList>> getUsers();
*/



    /*
     * List Of Users that order something
     * */


    @GET("order_search")
    Call<List<SearchOrder>> getSelected();



    /*
     * single order user details
     * */

    @FormUrlEncoded
    @POST("relay_single_order")
    Call<List<UserOrderDetails>> getUserOrderDetails(
            @Field("NIC_Handle") String nic
    );

    /*
     * user order details
     * */

    // TODO WORKING CODE FOR USER ORDER LIST

    /*@FormUrlEncoded
    @POST("relay_order_details")
    Call<List<ProductAvlShop>> getOrderDetails(
            @Field("NIC_Handle") String nic
    );
*/

    // TODO TESTING ONE

    // TODO  FOR USER ORDER LIST


    @FormUrlEncoded
    @POST("relay_get_qty")
    Call<UserOrderListDetails> getOrderDetails(
            @Field("NIC_Handle") String nic
    );




    /*
     * update Available/ Selected product
     * */
    /*@FormUrlEncoded
    @POST("update_avl_product")
    Call<DefaultResponse> updateProduct(
            @Field("NumId") String numId,
            @Field("NIC_Handle") String nic,
            @Field("D_Creation") String date,
            @Field("Reference") String reference,
            @Field("Libelle_TAG") String libele,
            @Field("Quantite") String quantity,
            @Field("Prix_Unitaire") String prix,
            @Field("Prix_Total") String prix_total,
            @Field("Remise") String remise,
            @Field("Total") String total

    );
    */

    @FormUrlEncoded
    @POST("relay_update_qty")
    Call<DefaultResponse> updateProduct(
            @Field("NumId") String numId,
            @Field("Quantite") String quantity,
            @Field("Prix_Unitaire") String prix

    );




    @FormUrlEncoded
    @POST("relay_store_forgot")
    Call<DefaultResponse> forgotPassword(
            @Field("email") String email
    );



    /*
     *     Verification User / Register User
     */

    @FormUrlEncoded
    @POST("relayverify_user")
    Call<DefaultResponse> verifyUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String pass,
            @Field("cpass") String cpass
    );
}
