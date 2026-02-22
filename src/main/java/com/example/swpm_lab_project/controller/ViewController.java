package com.example.swpm_lab_project.controller;

import com.example.swpm_lab_project.service.DashboardService; // Import the service
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final DashboardService dashboardService; // Inject Service

    public ViewController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping({"/", "/dashboard"})
    public String showDashboard(Model model, Authentication authentication) {
        // Use the service to get the correct data based on roles
        model.addAttribute("students", dashboardService.getStudentsForUser(authentication));
        model.addAttribute("courses", dashboardService.getAllCourses());

        return "dashboard";
    }
}