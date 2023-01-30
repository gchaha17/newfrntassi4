package com.example.assignnment4.resource;

import com.example.assignnment4.model.Album;
import com.example.assignnment4.model.FirebaseUser;
import com.example.assignnment4.service.AlbumService;
import com.example.assignnment4.service.FirebaseService;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://52.12.77.85","http://localhost:4200"})

@RequestMapping("/api")
public class AlbumResource {

    @Autowired
    private AlbumService als;
    @Autowired
    private FirebaseService firebaseservice;
    @PostMapping("/album")
    public Album saveAlbum(@RequestBody @Valid Album al,@RequestHeader(name="idToken")String idToken) throws IOException, FirebaseAuthException {
        FirebaseUser firebaseuser=firebaseservice.authenticate(idToken);
        if(firebaseuser!=null){

            return als.saveAlbum(al);
        }

else {
    return  null;
        }
    }

    @GetMapping("/album")
    public List<Album> getAlbum(@RequestHeader(name="idToken")String idToken)
    {



            return als.getAlbum();

    }
    @PutMapping("/album")
    public Album updateAlbum(@RequestBody Album al)
    {

        return als.updateAlbum(al);
    }
    @DeleteMapping("/album")
    public void deleteAlbum(@RequestParam(name="albumId")String albumId)
    {
        als.deleteAlbum(albumId);

    }
}