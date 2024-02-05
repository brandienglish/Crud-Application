package com.example.CrudApp.controller;

import com.example.CrudApp.model.Game;
import com.example.CrudApp.repo.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class GameController {

    // basic getter and setters to create, delete , add and update a game
    @Autowired
    private GameRepo gameRepo;


    //returning all games as a list
    @GetMapping("/getAllGames")
    public ResponseEntity<List<Game>> getAllGames() {
        try {
            List<Game> gameList = new ArrayList<>();
            gameRepo.findAll().forEach(gameList::add);

            if(gameList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return  new ResponseEntity<>(gameList,HttpStatus.OK);
        } catch (Exception ex) {
            //returning errors status
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }}

   //returning a game by its ID
    @GetMapping("/getGameById/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable Long id){
        Optional<Game> gameData= gameRepo.findById(id);

        // returning ok status if found
        if (gameData.isPresent()){
            return  new ResponseEntity<>(gameData.get(), HttpStatus.OK);
        }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addGame")
    public ResponseEntity<Game> addGame(@RequestBody Game game){
      Game gameObj  = gameRepo.save(game);

      return new ResponseEntity<>(gameObj, HttpStatus.OK);

    }
    @PostMapping("/updateGameById/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable Long id, @RequestBody Game newGameData){
    Optional<Game> oldGameData = gameRepo.findById(id );

    if (oldGameData.isPresent()){

        Game updatedGameData = oldGameData.get();
        updatedGameData.setTitle(newGameData.getTitle());
        updatedGameData.setStudio(newGameData.getStudio());

        Game  gameObj =gameRepo.save(updatedGameData);

        return  new ResponseEntity<>(gameObj, HttpStatus.OK);
    }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("deleteGamebyId/{id}")
    public ResponseEntity<Object> deleteGameById(@PathVariable Long id){
    gameRepo.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
    }
    }

