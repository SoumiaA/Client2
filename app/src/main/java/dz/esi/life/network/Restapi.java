package dz.esi.life.network;


import java.util.List;

import dz.esi.life.Model.Contenu;
import dz.esi.life.Model.User;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

/**
 * Created by Meftah Lakhdar on 13/3/15.
 */

public interface Restapi {

    @GET("/contenus")
    List<Contenu> Contenu();


    @POST("/Contenus")
    Contenu ajouter(@Body Contenu vendeur);

    @POST("/auth/signin")
    User singIn(@Body User user);

    @POST("/auth/signup")
    User singUp(@Body User utilisateur);

    @GET("/auth/signout")
    void signOut();

    @Multipart
    @POST("/Contenus")
    User upload(@Part("photo") TypedFile file);


}
