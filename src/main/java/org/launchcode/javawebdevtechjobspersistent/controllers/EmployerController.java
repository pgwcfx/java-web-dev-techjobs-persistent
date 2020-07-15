package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title","Add Employer");
            model.addAttribute(new Employer());
            return "employers/add";
        }
        employerRepository.save(newEmployer);
        return "redirect:";
    }

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable Integer employerId) {
        if (employerId==null) {
            model.addAttribute("title","All Jobs");
            model.addAttribute("employer", employerRepository.findAll());
        } else {
            Optional<Employer> result=employerRepository.findById(employerId);
            if (result.isEmpty()){
                model.addAttribute("title","Invalid Employer id: " + employerId);
            } else {
                Employer employer = result.get();
                model.addAttribute("title","Jobs with Employer: " + employer.getName());
                model.addAttribute("employer",employer.getName());
            }
            return "redirect:../";
        }
        return "employers/view";
    }
}
