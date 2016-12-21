package com.zqb.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zqb.domain.Course;
import com.zqb.domain.CourseInfo;
import com.zqb.domain.StudentCourse;
import com.zqb.domain.User;
import com.zqb.service.*;
import com.zqb.util.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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

    @Autowired
    StudentCourseService studentCourseService;


    //================================第一种返回数据给前台的方法，通过@responseBody注解
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


    @Autowired
    UserService userService;

    //===============================第三种返回数据给前台的方法，通过modelAndView
    @RequestMapping("/courseStudent")
    public ModelAndView courseStudent()
    {
        HttpSession session=request.getSession();
        ModelAndView model_view = new ModelAndView();
        model_view.setViewName("student_course_list");
        if(session.getAttribute("user")==null)
        {
            model_view.setViewName("redirect:/ctrs/login");
            return model_view;
        }
        else
        {
            User user=(User)session.getAttribute("user");
            if(!user.isUserType())
            {
                model_view.setViewName("redirect:/ctrs/index");
                return model_view;
            }
        }
        int courseId=0;
        try {
            courseId=Integer.parseInt(request.getParameter("courseId"));
        }catch (Exception e)
        {
            return model_view;
        }

        //System.out.println(courseId);
        //获取课程名字
        Course course=courseService.selectByPrimaryKey(courseId);
        model_view.addObject("courseName",course.getCourseName());

        //获取该课程下所有学生
        List<StudentCourse> stu_course = studentCourseService.selectByCourseId(courseId);

        //用以保存学生名单
        List<User> user_list=new LinkedList<User>();
        User user=null;
        for(int i=0;i<stu_course.size();i++)
        {
            int userId=stu_course.get(i).getUserId();

            user=userService.selectByPrimaryKey(userId);
            user_list.add(user);
        }
        model_view.addObject("userList",user_list);
        return model_view;
    }



    //=================================第二种返回数据给前台的方法，通过response回写
    @RequestMapping("/addCourse")
    public void addCourse(HttpServletResponse response)
    {
        response.setCharacterEncoding("utf-8");
        String course_name=request.getParameter("course_name");
        String remark=request.getParameter("remark");
//        Map<String,Object>resultMap=new HashMap<String,Object>();
        int res=courseService.addCourse(course_name,remark,request);
        String resultMap="";
        if(res>0)
        {
//            resultMap.put("code",200);
//            resultMap.put("msg","添加课程成功");
            resultMap="添加课程成功";
        }
        else
        {
//            resultMap.put("code",500);
//            resultMap.put("msg","添加课程失败");
            resultMap="添加课程失败";
        }
        try {
            PrintWriter out=response.getWriter();
            //System.out.println(resultMap.toString());
            out.print(resultMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/removeStudent")
    @ResponseBody
    public Map<String,Object> removeStudent()
    {
        Map<String,Object>resultMap=new HashMap<String,Object>();
        String action=request.getParameter("action");
        String user_id=request.getParameter("user_id");
        String course_id=request.getParameter("course_id");
        if(action==null||user_id==null||course_id==null)
        {
            resultMap.put("code",500);
            resultMap.put("msg","参数错误");
            return resultMap;
        }
        int userId=0;
        int courseId=0;
        try
        {
            userId=Integer.parseInt(user_id);
            courseId=Integer.parseInt(course_id);
        }
        catch (Exception e)
        {
            resultMap.put("code",500);
            resultMap.put("msg","参数错误");
            return resultMap;
        }

        if(action.equals("remove_student_single"))//单个删除
        {
            int res=studentCourseService.deleteStudent(userId,courseId);
            if(res>0)
            {
                resultMap.put("code",200);
                resultMap.put("msg","删除成功");
            }
            else
            {
                resultMap.put("code",500);
                resultMap.put("msg","删除失败");
            }
        }
        else//批量删除
        {

        }
        return resultMap;
    }

    @RequestMapping("/deleteCourse")
    @ResponseBody
    public Map<String,Object> deleteCourse()
    {
        Map<String,Object>resultMap=new HashMap<String,Object>();
        String courseId=request.getParameter("courseId");
        if(courseId==null)
        {
            resultMap.put("code",500);
            resultMap.put("msg","参数错误");
            return resultMap;
        }
        int course_id=0;
        try
        {
            course_id=Integer.parseInt(courseId);
        }catch (Exception e)
        {
            resultMap.put("code",500);
            resultMap.put("msg","参数错误");
            return resultMap;
        }

        int res=courseService.deleteCourse(course_id);
        if(res>0)
        {
            resultMap.put("code",200);
            resultMap.put("msg","删除成功");
        }
        else
        {
            resultMap.put("code",200);
            resultMap.put("msg","删除失败");
        }
        return resultMap;
    }

    @RequestMapping("/batchAddStudent")
    @ResponseBody
    public Map<String,Object>batchAddStudent()
    {
        Map<String,Object>resultMap=new HashMap<String,Object>();
        try {
            FileUpload.fileUpload(request);
        } catch (IOException e) {
            e.printStackTrace();
            resultMap.put("code",200);
            resultMap.put("msg","上传出错");
        }
        return resultMap;
    }

    @RequestMapping("/addStudent")
    @ResponseBody
    public Map<String,Object>addStudent()
    {
        Map<String,Object>resultMap=new HashMap<String,Object>();
        String username=request.getParameter("username");
        String courseId=request.getParameter("courseId");

        System.out.println("username="+username+" courseId="+courseId);
        if(username==null||courseId==null)
        {
            resultMap.put("code",500);
            resultMap.put("msg","参数出错");
            return resultMap;
        }
        int course_id=Integer.parseInt(courseId);
        System.out.println(course_id);
        if(registerService.registerCheck(username))//该学生还不是用户，先注册再与课程关联
        {
            registerService.registerNewUser(username,"123456");//默认密码123456
        }

        //注册成功,获取该用户id
        User user=userService.getUserByname(username);
        System.out.println(user.toString());
        int res=studentCourseService.addStudentCourse(user.getUserId(),course_id);
        System.out.println(res);
        if(res>0)
        {
            resultMap.put("code",200);
            resultMap.put("msg","添加成功");
            return resultMap;
        }
        else
        {
            resultMap.put("code",500);
            resultMap.put("msg","添加失败");
            return resultMap;
        }

    }
}
