package com.sunglowsys.web;

import com.sunglowsys.domain.Address;
import com.sunglowsys.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class AddressController {
    private final Logger  log = LoggerFactory.getLogger(AddressController.class);

    private final AddressService addressService;
    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }


    @GetMapping
    public ModelAndView home(){
        log.debug("Web request to home");
        Page<Address> page = addressService.findAll(PageRequest.of(0,20));
        return new ModelAndView("index","addresses",page.getContent());
    }

    @GetMapping("/addresses/create")
    public ModelAndView createForm(@ModelAttribute Address address){
        log.debug("Web request to create address form");
        return new ModelAndView("add-address","address",new Address());
    }

    @PostMapping("/addresses")
    public ModelAndView createAddress(@ModelAttribute Address address){
        log.debug("Web request to create address: {} ",address);
        return new ModelAndView("redirect:/","address",addressService.save(address));
    }

    @GetMapping("/addresses/update/{id}")
    public ModelAndView update(@PathVariable Long id){
        log.debug("web request to update address: {}",id);
        return new ModelAndView("add-address","address",addressService.findById(id).get());
    }

    @GetMapping("/addresses/search")
    public ModelAndView search(@RequestParam String searchText){
        log.debug("Web request to search address");
        return  new ModelAndView("index","address",addressService.search(searchText));
    }

    @GetMapping("/addresses/delete/{id}")
    public ModelAndView delete(@PathVariable Long id){
        log.debug("web request to delete address:{}",id);
        addressService.findById(id);
        return new ModelAndView("redirect:/");
    }

}
