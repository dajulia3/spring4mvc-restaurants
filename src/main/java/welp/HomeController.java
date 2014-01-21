package welp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

//@RequestMapping("/")
//    public String eating(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "home";
//    }

}