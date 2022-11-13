package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.categories;
import com.example.demo.repository.categoriesRepository;
import com.example.demo.service.CategoriesService;

@RestController
@RequestMapping("/api")
public class categoriesController {

	
	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	categoriesRepository cr;
	
	@GetMapping("/hello")
	public String getHelloMsg() {
		return "Hello...";
	}

	@GetMapping("/categories")
	public ResponseEntity<List<categories>> getAllcategories(@RequestParam(required = false) String cname) {
		try {
			if (cname == null)
				return new ResponseEntity<>(cr.findAll(), HttpStatus.OK);
			else
				return new ResponseEntity<>(cr.findByCname(cname), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/categories/{di}")
	public ResponseEntity<categories> getcategoriesById(@PathVariable("di") long cid) {
		Optional<categories> catdata = cr.findById(cid);

		if (catdata.isPresent()) {
			return new ResponseEntity<>(catdata.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/categories/{di}")
	public ResponseEntity<categories> updatecategories(@PathVariable("di") long cid, @RequestBody categories cate) {
		Optional<categories> cateData = cr.findById(cid);

		if (cateData.isPresent()) {
			categories _categories = cateData.get();
			_categories.setCname(cate.getCname());
			_categories.setDescription(cate.getDescription());

			return new ResponseEntity<>(cr.save(_categories), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/categories/{di}")
	public ResponseEntity<HttpStatus> deletecategories(@PathVariable("di") long cid) {
		try {
			cr.deleteById(cid);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/categories")
	public ResponseEntity<HttpStatus> deleteAllcategories() {
		try {
			cr.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
