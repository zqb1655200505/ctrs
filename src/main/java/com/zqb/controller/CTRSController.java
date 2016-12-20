package com.zqb.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zqb.domain.Course;
import com.zqb.domain.CourseInfo;
import com.zqb.domain.User;
import com.zqb.service.CourseService;
import com.zqb.service.LoginService;
import com.zqb.service.PublishNoticeService;
import com.zqb.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zqb on 2016/12/4.
 */
@Controller
@RequestMapping("/ctrs")
public class CTRSController {

   @Resource
    HttpServletRequest request;


    @Autowired
    LoginService loginService;

    @Autowired
    RegisterService registerService;

    @Autowired
    PublishNoticeService publishNoticeService;


    //截获根目录请求
    @RequestMapping(method = RequestMethod.GET)
    public String welcome()
    {
        return "login";
    }

    @RequestMapping("/login")
    public String toLogin()
    {
        return "login";
    }


    /**
     关于 @ResponseBody：
     将内容或对象作为 HTTP 响应正文返回，使用@ResponseBody将会跳过视图处理部分，
     而是调用适合HttpMessageConverter，将返回值写入输出流。
     */
    @RequestMapping(value = "/register_check",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String,Object> register_check()
    {

        Map<String,Object> resultMap = new HashMap<String, Object>();
        String username=request.getParameter("user_register");
        String password=request.getParameter("pass_register");
        String password_confer=request.getParameter("pass_confer");
        if(username==""||password==""||password_confer=="")
        {
            //request.setAttribute("register_status","请先完善注册信息！");
            resultMap.put("result","请先完善注册信息！");
        }
        else
        {
            if(!password.equals(password_confer))
            {
                //request.setAttribute("register_status","密码前后不一致！");
                resultMap.put("result","密码前后不一致！");
            }
            else
            {
                boolean check=registerService.registerCheck(username);//判断该用户名是否被注册
                if(check)
                {
                    check=registerService.registerNewUser(username,password);
                    if(check)
                    {
                        //request.setAttribute("register_status","注册成功！");
                        resultMap.put("result","注册成功！");
                    }
                    else
                    {
                        //request.setAttribute("register_status","注册失败，请重新注册！");
                        resultMap.put("result","注册失败，请重新注册！");
                    }
                }
                else
                {
                    //request.setAttribute("register_status","该用户名已经被注册！");
                    resultMap.put("result","该用户名已经被注册！");
                }
            }
        }
        System.out.println(resultMap.get("result"));
        return resultMap;
    }

    @RequestMapping("/login_check")
    @ResponseBody
    public Map<String,Object> login_check()
    {
        Map<String,Object> resultMap = new HashMap<String, Object>();
        HttpSession session=request.getSession();
        //该浏览器已经有用户在线
        if(session.getAttribute("user")!=null)
        {

            resultMap.put("code","error");
            resultMap.put("msg","您在该浏览器已经登录过账户！");
            return resultMap;
        }
        else
        {
            String username=request.getParameter("user_login");
            String password=request.getParameter("pass_login");
            User user = loginService.doLogin(username,password);
            if(user!=null)
            {
                session.setAttribute("user",user);//将用户状态保存在session中
                resultMap.put("code","success");
                return resultMap;
            }
            else
            {
                resultMap.put("code","error");
                resultMap.put("msg","用户名或密码错误！");
                return resultMap;
            }
        }
    }


    @RequestMapping("/index")
    public String indexPage()
    {
        HttpSession session=request.getSession();
        //判断用户是否已经登录，如没有则返回登录界面
        if(session.getAttribute("user")!=null)
        {
            return "index";
        }
        return "redirect:/ctrs/login";
    }

    @RequestMapping("/checkUserType")
    @ResponseBody
    public Map<String,Object> checkUserType()
    //public String checkUserType()
    {
        Map<String,Object> resultMap=new HashMap<String,Object>();
        HttpSession session=request.getSession();
        //判断用户是否已经登录，如没有则返回登录界面
        if(session.getAttribute("user")==null)
        {
            resultMap.put("code",500);
            resultMap.put("msg","login");
            //System.out.println("login");
            return resultMap;
        }
        else
        {
            User user=(User)session.getAttribute("user");
            resultMap.put("username",user.getUserName());
            if(user.isUserType())
            {
                resultMap.put("code",200);
                resultMap.put("msg","true");
                //System.out.println("true");
                return resultMap;
            }
            else
            {
                resultMap.put("code",200);
                resultMap.put("msg","false");
                //System.out.println("false");
                return resultMap;
            }
        }
    }

    @RequestMapping("/signOut")
    @ResponseBody
    public Map<String,Object> sign_out()
    {
        Map<String,Object> resultMap = new HashMap<String, Object>();

        //清空所有session
        Enumeration em = request.getSession().getAttributeNames();
        while(em.hasMoreElements()){
            request.getSession().removeAttribute(em.nextElement().toString());
        }
        resultMap.put("code",200);
        resultMap.put("msg","注销成功");
        return resultMap;
    }

    @RequestMapping("/onlineDiscussion")
    public String onlineDiscussion()
    {
        return "online_discussion";
    }

    @RequestMapping("/courseRelate")
    public String courseRelate()
    {
        return "course_relate";
    }

    @RequestMapping("/publishNotice")
    public String publishNotice()
    {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        if(user!=null&&user.isUserType())
        {
            return "publish_notice";
        }
        return "redirect:/ctrs/index";
    }

    @RequestMapping("/studentManage")
    public String studentManage()
    {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        if(user!=null&&user.isUserType())
        {
            return "student_manage";
        }
        return "redirect:/ctrs/index";
    }

    @RequestMapping("centralPerson")
    public String centralPerson()
    {
        return "central_person";
    }


    @RequestMapping("/publish")
    @ResponseBody
    public Map<String,Object> publish()
    {
        String title=request.getParameter("title");
        String content=request.getParameter("content");
        return publishNoticeService.publishNotice(title,content,request);
    }

    @Autowired
    CourseService courseService;

    @RequestMapping("/getCourseList")
    @ResponseBody
    public List<CourseInfo> getCourseList()
    {
        String action=request.getParameter("action");
        if(action.equals("getAll"))
        {
            return courseService.getAllCourseInfo();
        }
        else
        {
            String keyword=request.getParameter("keyWord");
            return courseService.search(keyword);
        }
    }

    @RequestMapping("/courseStudent")
    public ModelAndView courseStudent()
    {
        ModelAndView model_view = new ModelAndView();
        System.out.println(request.getParameter("courseId"));
        return model_view;
    }



    @RequestMapping("/addCourse")
    public void addCourse(HttpServletResponse response)
    {
        response.setCharacterEncoding("utf-8");
        String course_name=request.getParameter("course_name");
        String remark=request.getParameter("remark");
        Map<String,Object>resultMap=new HashMap<String,Object>();
        int res=courseService.addCourse(course_name,remark,request);
        if(res>0)
        {
            resultMap.put("code",200);
            resultMap.put("msg","添加课程成功");
        }
        else
        {
            resultMap.put("code",500);
            resultMap.put("msg","添加课程失败");
        }
        try {
            PrintWriter out=response.getWriter();
            System.out.println(resultMap.toString());
            out.print(resultMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
