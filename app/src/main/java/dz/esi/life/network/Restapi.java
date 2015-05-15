package dz.esi.life.network;


import java.util.List;

import dz.esi.life.Model.Contenu;
import dz.esi.life.Model.PubImag;
import dz.esi.life.Model.User;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Query;
import retrofit.mime.TypedFile;

/**
 * Created by Meftah Lakhdar on 13/3/15.
 */

public interface Restapi {

    @GET("/contenus")
    List<Contenu> contenu();

    @POST("/Contenus")
    Contenu contenu(@Body Contenu contenu);

    @GET("/pub-imags")
    List<PubImag> images();

    @POST("/pub-imags")
    PubImag PubImag_publier(@Body PubImag pubImag);


    @POST("/auth/signup")
    User singUp(@Body User utilisateur);

    @GET("/auth/signout")
    void signOut();

    @Multipart
    @POST("/Contenus")
    User upload(@Part("photo") TypedFile file);


    @POST("/auth/signin")
    User singIn(@Body User user);

    @GET("/auth/google/callback")
    Response signInWithGoogle(@Query("code") String code);

    @GET("/users/me")
    User me();


}
