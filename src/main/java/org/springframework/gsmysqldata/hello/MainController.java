package org.springframework.gsmysqldata.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/add")
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping("/add_person")
    public @ResponseBody String addNewPerson (@RequestParam String firstName, @RequestParam String lastName, @RequestParam Integer age) {
        Person p = new Person();
        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setAge(age);
        personRepository.save(p);
        return "Saved";
    }

    @GetMapping
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/person")
    public @ResponseBody Iterable<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }

    @GetMapping("/find")
    public @ResponseBody List<User> getUsersByName(@RequestParam String name) {
        return userRepository.findByNameContaining(name);
    }
}
