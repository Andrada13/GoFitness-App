package backend.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.models.Program;
import backend.repository.ProgramRepository;

@Service
public class ProgramService {

    @Autowired
    ProgramRepository programRepository;


    /*
    public void updatePrograms(Long id, String name, String description,String trainerName, String price) {
        Program program = findProgramById(id);
        program.setName(name);
        program.setTrainerName(trainerName);
        program.setDescription(description);
        program.setPrice(price);
        programRepository.save(program);
    }
*/
    public Program findProgramById(long id) {
        for (Program program : findAllPrograms()) {
            if (program.getId() == id) {
                return program;
            }
        }

        return null;
    }

    public List<Program> findAllPrograms() {
        List<Program> programs = new ArrayList<>();
        programRepository.findAll().forEach(programs::add);
        return programs;
    }

}
