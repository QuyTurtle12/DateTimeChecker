package com.swr302.datetimechecker.controllers;

import com.swr302.datetimechecker.models.DateForm;
import com.swr302.datetimechecker.utils.DateUtil;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class DateController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("dateForm", new DateForm());
        return "index";
    }

    @PostMapping("/checkDate")
    public String checkDate(@Valid @ModelAttribute DateForm dateForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "index";
        }

            int day = Integer.parseInt(dateForm.getDay());
            int month = Integer.parseInt(dateForm.getMonth());
            int year = Integer.parseInt(dateForm.getYear());
            if (day >= 1 && day <= 31) { //Check day range
                if (month >= 1 && month <= 12) { //Check month range
                    if (year >= 1000 && year <= 3000) { //Check year range
                        boolean isValid = DateUtil.isValidDate(year, month, day);
                        String dateStr = day + "/" + month + "/" + year;
                        if (isValid) {
                            model.addAttribute("success", dateStr + " is a correct date time!");
                        } else {
                            model.addAttribute("error", dateStr + " is NOT a correct date time!");
                        }
                    } else{
                        model.addAttribute("error", "Input data for Year is out of range!");
                    }
                } else{
                    model.addAttribute("error", "Input data for Month is out of range!");
                }
            } else{
                model.addAttribute("error", "Input data for Day is out of range!");
            }

            return "index";
    }
}