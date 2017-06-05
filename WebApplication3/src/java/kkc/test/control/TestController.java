/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kkc.test.control;

import java.util.List;
import javax.validation.Valid;
import kkc.test.dao.*;
import kkc.test.model.TestDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author 1
 */
@Controller
public class TestController {

//    @RequestMapping(value = "test", method = RequestMethod.GET)
//    public String showForm(ModelMap model) {
//        model.addAttribute("test",new Test());
//        return "test";
//    }
    @Autowired
    TestDAOImpl testDAO;
    
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public ModelAndView user() {
        return new ModelAndView("test", "test", new TestDB());
    }

    @RequestMapping(value = "addTest", method = RequestMethod.POST)
    public String submit(@ModelAttribute("test") TestDB testdb,
            BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        
        testDAO.save(testdb);
        
        List<TestDB> list = testDAO.list();
        for(TestDB t : list){
            System.out.println("list: "+t);
        }
        
        model.addAttribute("name", testdb.getName());
        return "result";
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public String helloWorld(ModelMap modelMap) {
//        System.out.println("on method");
//        modelMap.put("printme", "Hello Spring !!");
//        return "index";
//    }
}
