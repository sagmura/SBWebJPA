package com.sagmura.jpa.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sagmura.jpa.dto.Alien;
import com.sagmura.jpa.repo.AlienRepository;

@RestController
public class AlienController {

	
	@Autowired
	AlienRepository alienRepo;
	
	
	@PostMapping(path="/addItem")
	public Alien addItem(Alien alien) {
		System.out.println("@PostMapping Aid "+alien.getAid()+" Aname "+alien.getAname());
		alienRepo.save(alien);
		return alien;
	}
	
	@PostMapping(path="/newItem",consumes= {"application/json"})
	public List<Alien> newItem(@RequestBody Alien alien) {
		System.out.println("@PostMapping Aid "+alien.getAid()+" Aname "+alien.getAname());
		alienRepo.save(alien);
		return alienRepo.findAll();
	}
	
	
	@PutMapping(path="/newItem",consumes= {"application/json"})
	public List<Alien> saveItem(@RequestBody Alien alien) {
		System.out.println("@PutMapping Aid "+alien.getAid()+" Aname "+alien.getAname());
		alienRepo.save(alien);
		return alienRepo.findAll();
	}
	
	
	@DeleteMapping(path="/items/{aid}")
	public List<Alien> deleteItem(@PathVariable("aid") int aid) {
		alienRepo.deleteById(aid);
	    return alienRepo.findAll();
	}
	
	
//	@RequestMapping("/")
//	public String showPage() {
//		return "home";
//	}
	
	
	@GetMapping("/items")
	@ResponseBody
	public List<Alien> items() {
	    return alienRepo.findAll();
	}
	
	@RequestMapping(path="/items/{aid}",produces={"application/xml"})
	@ResponseBody
	public Alien idItems(@PathVariable("aid") int aid) {
	    return alienRepo.findById(aid).orElse(new Alien());
	}
	
	
	@RequestMapping("/getItem")
	public ModelAndView getItem(@RequestParam int aid) {
		ModelAndView mv= new ModelAndView();
		Alien alien=alienRepo.findById(aid).orElse(new Alien());
		mv.addObject("alien", alien);
		mv.setViewName("showitem");
		return mv;
	}
}
