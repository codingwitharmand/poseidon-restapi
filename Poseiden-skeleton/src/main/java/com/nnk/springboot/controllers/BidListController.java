package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class BidListController {
    private final BidListRepository bidListRepository;

    @RequestMapping("/bidList/list")
    public String home(HttpServletRequest request, Model model)
    {
        List<BidList> bidLists = bidListRepository.findAll();
        model.addAttribute("bidLists", bidLists);
        model.addAttribute("remoteUser", request.getRemoteUser());

        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result) {
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListRepository.save(bid);

        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        var bidList = bidListRepository.findById(id).orElseThrow(() -> new RuntimeException("bidList not found..."));
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "bidList/update/" + id;
        }
        bidListRepository.save(bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id) {
        var bidList = bidListRepository.findById(id).orElseThrow(() -> new RuntimeException("bidList not found..."));
        bidListRepository.delete(bidList);
        return "redirect:/bidList/list";
    }
}
