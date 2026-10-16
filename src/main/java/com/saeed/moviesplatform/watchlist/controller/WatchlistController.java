package com.saeed.moviesplatform.watchlist.controller;


import com.saeed.moviesplatform.dto.WatchlistDetailsResponse;
import com.saeed.moviesplatform.dto.WatchlistResponse;
import com.saeed.moviesplatform.entity.PlatformUser;
import com.saeed.moviesplatform.security.CustomUserDetails;
import com.saeed.moviesplatform.watchlist.service.IWatchlistService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/watchlist")
@SecurityRequirement(name = "bearerAuth")
public class WatchlistController {

    private final IWatchlistService watchlistService;

    @PostMapping("/movies/{movieId}")
    public WatchlistResponse addToWatchList(Authentication authentication , @PathVariable Long movieId){

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

       return watchlistService.addToWatchlist(user.getUser().getId(), movieId);

    }

    @GetMapping()
    public WatchlistDetailsResponse displayWatchlist(Authentication authentication) {

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        return watchlistService.displayWatchlist(user.getUser().getId());

    }

    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<Void> removeFromWatchlist(Authentication authentication, @PathVariable Long movieId){

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        watchlistService.removeFromWatchlist(user.getUser().getId(), movieId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
