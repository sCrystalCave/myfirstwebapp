package com.in28minutes.springboot.myfirstwebapp.play;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class PlayService {
    private static List<Play> plays = new ArrayList<>();
    private static int playsCount = 0;
    static{
        plays.add(new Play(++playsCount, "in28minutes", "Learn AWS",
                LocalDate.now().plusYears(1), false));
        plays.add(new Play(++playsCount, "in28minutes", "Learn DevOps",
                LocalDate.now().plusYears(2), false));
        plays.add(new Play(++playsCount, "in28minutes", "Learn Full Stack Development",
                LocalDate.now().plusYears(3), false));
    }

    public List<Play> findByUsername(String username){
        //일단 이렇게 해놓음.
        return plays;
    }
    public void addPlay(String username, String description, LocalDate targetDate, boolean done){
        Play play = new Play(++playsCount, username, description, targetDate, done);
        plays.add(play);
    }

    public void deleteById(int id){
        Predicate<?super Play> predicate;
        predicate = play -> play.getId() == id;
        plays.removeIf(predicate);
    }

    public Play findById(int id){
        Predicate<?super Play> predicate = play -> play.getId() == id;
        Play play = plays.stream().filter(predicate).findFirst().get();
        return play;
    }

    public void updatePlay(@Valid Play play){
        deleteById(play.getId());
        plays.add(play);
    }
}
