package org.springmvc.hospital.web;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        Page<Patient> pagePatients = patientRepository.findAll(PageRequest.of(0, 4));
        model.addAttribute("patients",pagePatients.getContent());
        return "patients";
    }
}
