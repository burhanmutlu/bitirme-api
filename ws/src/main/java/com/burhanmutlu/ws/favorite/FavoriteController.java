package com.burhanmutlu.ws.favorite;

import com.burhanmutlu.ws.favorite.dto.req.FavoriteRequest;
import com.burhanmutlu.ws.favorite.dto.resp.FavoriteResponse;
import com.burhanmutlu.ws.shared.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1")
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/favorites/files/user/{userId}")
    public ResponseEntity<?> addFileToFavorite(@PathVariable Long userId,
                                               @RequestBody FavoriteRequest favoriteRequest) {
        favoriteService.addFileToFavorite(userId, favoriteRequest);
        return ResponseEntity.ok(new GenericResponse(true, "file added to favorites"));
    }

    @PostMapping("/favorites/logins/user/{userId}")
    public ResponseEntity<?> addLoginsToFavorite(@PathVariable Long userId,
                                                 @RequestBody FavoriteRequest favoriteRequest) {
        favoriteService.addLoginsToFavorite(userId, favoriteRequest);
        return ResponseEntity.ok(new GenericResponse(true, "Logins added to favorites"));
    }

    @GetMapping("/favorites/files/user/{userId}")
    public ResponseEntity<List<FavoriteResponse>> getAllFavoriteFiles(@PathVariable Long userId) {
        List<FavoriteResponse> favoriteResponses = favoriteService.getAllFavoritesFilesByUser(userId);
        return ResponseEntity.ok(favoriteResponses);
    }






}
