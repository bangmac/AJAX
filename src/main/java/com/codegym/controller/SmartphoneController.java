package com.codegym.controller;

import com.codegym.model.Smartphone;
import com.codegym.service.SmartphoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/smartphones")
public class SmartphoneController {

    @Autowired
    private SmartphoneService smartphoneService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createSmartphonePage() {
        ModelAndView mav = new ModelAndView("phones/new-phone");
        mav.addObject("sPhone", new Smartphone());
        return mav;
    }

    @RequestMapping(value = "/createnewPhone",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Smartphone createSmartphone(@RequestBody Smartphone smartphone) {
        //@RequestBody Smartphone smartphone thực hiện gán dữ liệu từ json nhận được vào các git  tương ứng của smartphone
        return smartphoneService.save(smartphone);
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Iterable<Smartphone> allPhones(){
        return smartphoneService.findAll();
    }

    @GetMapping("")
    public ModelAndView allPhonesPage() {
        ModelAndView modelAndView = new ModelAndView("phones/all-phones");

        modelAndView.addObject("allphones", allPhones());
        return modelAndView;
    }

    @RequestMapping(value = "/delete/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Smartphone deleteSmartphone(@PathVariable Integer id){
        return smartphoneService.remove(id);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editSmartphonePage(@PathVariable int id) {
        ModelAndView mav = new ModelAndView("phones/edit-phone");
        Smartphone smartphone = smartphoneService.findById(id);
        mav.addObject("sPhone", smartphone);
        return mav;
    }

    @RequestMapping(value = "/edit/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            // mediaType chỉ nhận kiểu dữ liệu Json;
            // produces : xác định kiểu dữ liệu trả về (response)
            consumes = MediaType.APPLICATION_JSON_VALUE)
    // consumes : xác định kiểu dữ liệu gửi yêu cầu (request)
    @ResponseBody
    public Smartphone editSmartphone(@PathVariable int id, @RequestBody Smartphone smartphone) {
        smartphone.setId(id);
        return smartphoneService.save(smartphone);
    }
}
