package com.in28minutes.springboot.myfirstwebapp.play;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class PlayController {

    public PlayController(PlayService playService) {
        this.playService = playService;
    }
    private PlayService playService;



    @RequestMapping("list-plays")
    public String listAllTodos(ModelMap model){
        List<Play> plays = playService.findByUsername("in28minutes");
        model.addAttribute("plays", plays);
        //listPlays.jsp 뷰를 띄움.
        return "listPlays";
    }

    //양방향 바인딩.
    //미리 값을 채울 수 있고, 채운 값을 post에서 사용할 수도 있다.
    @RequestMapping(value="add-play", method = RequestMethod.GET)
    public String showNewPlayPage(ModelMap model){
        String username = (String)model.get("name");
        //새로운 객체를 만들어서 의미없는 값으로 초기화 시킴.
        Play play = new Play(0,  username, "",
                LocalDate.now().plusYears(1), false);

        model.put("play", play);
        return "play";
    }

    //showNewTodoPage의 결과로 description이 채워진 post 요청이 생김.
    @RequestMapping(value="add-play", method = RequestMethod.POST)
    //Todo Bean에 직접 값을 넣는 방법. @RequestParam을 사용하지 않음.
    //Binding Result : 오류 메시지 관련
    public String addNewPlayPage(ModelMap model, @Valid Play play, BindingResult result){
        if(result.hasErrors()) {return "play";}

        String username = (String)model.get("name");
        playService.addPlay(username, play.getDescription(),
                play.getTargetDate(), false);
        //이전 페이지로 돌아감.
        return "redirect:list-plays";
    }

    @RequestMapping(value="delete-play", method = RequestMethod.GET)
    public String deletePlayPage(@RequestParam int id){
        playService.deleteById(id);
        return "redirect:list-plays";
    }

    @RequestMapping(value = "update-play", method = RequestMethod.GET)
    public String showUpdatePlayPage(@RequestParam int id, ModelMap model) {
        Play play = playService.findById(id);
        model.addAttribute("play", play);
        return "play";
    }

    @RequestMapping(value = "update-play", method = RequestMethod.POST)
    public String UpdatePlayPage(ModelMap model, @Valid Play play, BindingResult result){
        if(result.hasErrors()) {return "play";}

        String username = (String)model.get("name");
        play.setUsername(username);
        playService.updatePlay(play);
        //이전 페이지로 돌아감.
        return "redirect:list-plays";
    }
}
