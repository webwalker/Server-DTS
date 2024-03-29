package com.xujian.sample.controller;

import com.xujian.sample.model.User;
import com.xujian.sample.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    /**
     * 页面跳转, 所有的非捕获请求都会到这里
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }

    /**
     * 添加用户
     */
    @RequestMapping("/addUser")
    public String addUser(User user) {
        this.usersService.addUser(user);
        return "ok";
    }

    /**
     * 查询全部用户
     */
    @RequestMapping("/findUserAll")
    public String findUserAll(Model model) {
        List<User> list = this.usersService.findUserAll();
        model.addAttribute("list", list);
        return "showUsers";
    }

    /**
     * 根据用户id查询用户
     */
    @RequestMapping("/findUserById")
    public String findUserById(Integer id, Model model) {
        User user = this.usersService.findUserById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    /**
     * 更新用户
     */
    @RequestMapping("/editUser")
    public String editUser(User user) {
        this.usersService.updateUser(user);
        return "ok";
    }

    /**
     * 删除用户
     */
    @RequestMapping("/delUser")
    public String delUser(Integer id) {
        this.usersService.deleteUserById(id);
        return "redirect:/user/findUserAll";
    }

    /**
     * 如果想为传递的对象更改名称，可以使用@ModelAttribute("aa")这表示当前传递的对象的key为aa。
     * 那么我们在页面中获取该对象的key也需要修改为aa
     *
     * @param user
     * @return
     */
    @RequestMapping("/add")
    public String showPage(@ModelAttribute("aa") User user) {
        return "add";
    }

    //validator, 验证提交结果
    @RequestMapping("/save")
    public String saveUser(@ModelAttribute("aa") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "add";
        }
        System.out.println(user);
        return "ok";
    }
}
