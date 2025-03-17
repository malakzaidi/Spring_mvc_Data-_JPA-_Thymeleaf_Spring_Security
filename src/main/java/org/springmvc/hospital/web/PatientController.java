package org.springmvc.hospital.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springmvc.hospital.entities.Patient;
import org.springmvc.hospital.repositories.PatientRepository;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(Model model){
        List<Patient> patients = patientRepository.findAll();
        model.addAttribute("patients",patients);
        return "patients";
    }
}
