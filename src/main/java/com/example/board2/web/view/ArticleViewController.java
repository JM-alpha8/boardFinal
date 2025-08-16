package com.example.board2.web.view;

import com.example.board2.service.ArticleService;
import com.example.board2.web.dto.ArticleUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.board2.web.dto.ArticleCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
@RequiredArgsConstructor
@RequestMapping
public class ArticleViewController {
    private final ArticleService articleService;

    @GetMapping("/articles")
    public String list(Model model){
        model.addAttribute("articles", articleService.findAll());
        return "article-list";
    }

    @GetMapping("/articles/new")
    public String createForm(){ return "article-form"; }

//    @PostMapping("/articles")
//    public String create(ArticleCreateRequest req){
//        articleService.create(req);
//        return "redirect:/articles";
//    }

    @GetMapping("/articles/{id}")
    public String detail(@PathVariable Long id, Model model, Authentication auth){
        var article = articleService.findById(id);
        model.addAttribute("article", article);
        boolean isOwner = auth != null && article.getAuthor().getEmail().equals(auth.getName());
        model.addAttribute("isOwner", isOwner);
        return "article-detail";
    }


    @GetMapping("/articles/{id}/edit")
    public String editForm(@PathVariable Long id, Model model){
        var article = articleService.findById(id);
        model.addAttribute("article", article);
        return "article-edit";
    }

//    @PostMapping("/articles/{id}/edit")
//    public String edit(@PathVariable Long id, ArticleUpdateRequest req){
//        articleService.update(id, req);
//        return "redirect:/articles/" + id; // 수정 후 상세로 이동
//    }

//    @PostMapping("/articles/{id}/delete")
//    public String delete(@PathVariable Long id){
//        articleService.delete(id);
//        return "redirect:/articles";
//    }




}

