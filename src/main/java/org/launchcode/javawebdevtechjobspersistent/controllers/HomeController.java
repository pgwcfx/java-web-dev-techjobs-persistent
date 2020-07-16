package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.Job;
import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers",employerRepository.findAll());
        model.addAttribute("skills",skillRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob, @RequestParam @Valid int employerId, @RequestParam @Valid List<Integer> skills,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        }
        Employer employer = employerRepository.findById(employerId).orElse(new Employer());
        List<Skill> skillObj = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObj);
        newJob.setEmployer(employer);
        jobRepository.save(newJob);
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable(required = false) Integer jobId) {

        if (jobId == null){
            model.addAttribute("title","All Jobs");
            model.addAttribute("jobs",jobRepository.findAll());
        } else {
            Optional<Job> result = jobRepository.findById(jobId);
            if (result.isEmpty()){
                model.addAttribute("title","Invalid Job ID: " + jobId);
            } else {
                Job job = result.get();
                model.addAttribute("title","Jobs  with title: " + job.getName());
                model.addAttribute("jobs",job);
            }
        }
        return "view";
    }


}
