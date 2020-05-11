package wizut.tpsi.lab9.web;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wizut.tpsi.lab9.model.BlogPost;
import wizut.tpsi.lab9.model.BlogRepository;


@Controller
public class BlogRepositoryController {
    
    @Autowired
    private BlogRepository blogRepo;
    
    @RequestMapping("/")
    public String home(Model model) {
        try {
        List<BlogPost> blogPosts = blogRepo.getAllPosts();
        model.addAttribute("blogPosts", blogPosts);
        }
        catch (SQLException e)
        {
        }
    return "home";
    }
    
    @PostMapping("/newpost")
    public String newPost(BlogPost post) throws SQLException {
    blogRepo.createPost(post);
    return "redirect:/";
    }
    
    @PostMapping("/delpost")
    public String delPost(String id) throws SQLException {
    blogRepo.deletePost(Integer.parseInt(id));
    return "redirect:/";
    }
}
