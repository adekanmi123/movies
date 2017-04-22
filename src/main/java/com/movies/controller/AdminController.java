package com.movies.controller;

import com.movies.model.Admin;
import com.movies.model.AdminDao;
import com.movies.model.Member1;
import com.movies.model.MemberDao;
import com.movies.model.Movie;
import com.movies.model.MovieDao;
import com.movies.model.OrderDao;
import com.movies.model.Genre;
import com.movies.model.GenreDao;
import com.movies.model.Order1;
import com.movies.model.OrdersMovies;
import com.movies.model.OrdersMoviesDao;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    GenreDao genreDao;
    
    @Autowired
    MovieDao movieDao;
    
    @Autowired
    OrderDao orderDao;
    
    @Autowired
    MemberDao memberDao;
    
    @Autowired
    OrdersMoviesDao omDao;
    
    @Autowired
    AdminDao adminDao;
    
    @RequestMapping("/")
    public String login() {
        return"admin/login";
    }
    
    @RequestMapping("/adminconsole")
    public String adminConsole (@RequestParam (required = true) String username, @RequestParam (required = true) String password) {
        Admin a = adminDao.find(username, password);
        if(a!=null) {
            return "admin/index";
        } else {
            return "admin/wrong";
        }
        
    }
    
    @RequestMapping("/categories")
    public String categories() {
       return "admin/categories";
    }
    
    @RequestMapping("/updatecategory")
    public String updateCategory(@RequestParam (required = false) Integer id,ModelMap model) {
        List<Genre> genres = genreDao.find();
        model.addAttribute("genres", genres);
        if(id!=null) {
            Genre selectedGenre = genreDao.findById(id);
            model.addAttribute("selectedcategory", selectedGenre);
        }
        return "admin/updatecategory";        
    }
    
    @RequestMapping("/updatedcategory")
    public String updatedCategory(@RequestParam Integer id, @RequestParam String name, ModelMap model) {
        Genre selectedGenre = genreDao.findById(id);
        selectedGenre.setName(name);
        genreDao.update(selectedGenre);
        model.addAttribute("genre", selectedGenre);
        return "admin/updatedcategory";
    }
    
    @RequestMapping("/insertcategory")
    public String insertCategory(){
        return "admin/insertcategory";
    }
    
    @RequestMapping("/addedcategory")
    public String addedCategory(@RequestParam(required = true) String name, ModelMap model) {
        Genre g = new Genre();
        g.setName(name);
        genreDao.insert(g);
        model.addAttribute("genre", g);
        return "admin/addedcategory";
    }
    
    @RequestMapping("/products")
    public String products() {
        return "admin/products"; 
    }
    
    @RequestMapping("/insertproduct")
    public String insertProduct(ModelMap model) {
        List<Genre> genres = genreDao.find();
        model.addAttribute("genres", genres);
        return "admin/insertproduct";
    }
    
    @RequestMapping("/addedproduct")
    public String addedProduct(@RequestParam(required = true) String title, @RequestParam(required = true) Integer genre, @RequestParam(required = true) String price, @RequestParam MultipartFile image, ModelMap model, HttpServletRequest request) throws FileNotFoundException, IOException {
        
        String filepath = request.getServletContext().getRealPath("resources/images");
        FileOutputStream fos = new FileOutputStream(filepath + "/" + image.getOriginalFilename());
        fos.write(image.getBytes());
        fos.close();
        
        Movie m = new Movie();
        m.setTitle(title);
        Genre g = genreDao.findById(genre);
        m.setGenre(g);
        Double priceDouble = Double.parseDouble(price);
        m.setPrice(priceDouble);        
        m.setImage("images/" + image.getOriginalFilename());
        m.setOnstock(0);
        movieDao.insert(m);
        model.addAttribute("movie", m);
        return "admin/addedproduct";
    }
    
    @RequestMapping("/allproducts")
    public String allProducts(@RequestParam(defaultValue = "1") Integer page, ModelMap model) {
        List<Movie> movies = movieDao.findByPage(page-1);
        model.addAttribute("movies", movies);
        model.addAttribute("totalpages", movieDao.pages());
        
        return "admin/allproducts";
    }
    
    @RequestMapping("/updateproduct")
    public String updateProduct(@RequestParam(required = true) Integer id, ModelMap model) {
        Movie movie = movieDao.findById(id);
        model.addAttribute("selectedMovie", movie);
        List<Genre> genres = genreDao.find();
        model.addAttribute("genres", genres);
        return "admin/updateproduct";
    }
    
    @RequestMapping("/updatedproduct")
    public String updatedProduct(@RequestParam(required = true) Integer id, @RequestParam(required = false) Integer check,@RequestParam(required = true) String title, @RequestParam(required = true) Integer genre, @RequestParam(required = true) String price, @RequestParam MultipartFile image, ModelMap model, HttpServletRequest request) throws FileNotFoundException, IOException {     
        Movie m = movieDao.findById(id);
        m.setTitle(title);
        Genre g = genreDao.findById(genre);
        m.setGenre(g);
        Double priceDouble = Double.parseDouble(price);
        m.setPrice(priceDouble);
        
        if(image!=null && !image.isEmpty()){
            String filepath = request.getServletContext().getRealPath("resources/images");
            FileOutputStream fos = new FileOutputStream(filepath + "/" + image.getOriginalFilename());
            fos.write(image.getBytes());
            fos.close();
            m.setImage("images/" + image.getOriginalFilename());
        } else {
            m.setImage(m.getImage());
        }
        if(check!=null) {
            m.setOnstock(1);
        } else {
            m.setOnstock(0);
        }
        movieDao.update(m);
        model.addAttribute("movie", m);
        
        return "admin/updatedproduct";
    }
        
    @RequestMapping("/members")
    public String members() {
        return "admin/members";
    }
    
    @RequestMapping("/insertmember")
    public String insertMember() {
        return "admin/insertmember";
    }
    
    @RequestMapping("/addedmember")
    public String addedMember(@RequestParam(required = true) String firstName, @RequestParam(required = true) String lastName, @RequestParam(required = true) String address, @RequestParam(required = true) String city, @RequestParam(required = true) String postalCode, @RequestParam(required = true) String email, ModelMap model) {
        Member1 m = new Member1();
        m.setFirstName(firstName);
        m.setLastName(lastName);
        m.setAddress(address);
        m.setCity(city);
        m.setPostalCode(postalCode);
        m.setEmail(email);
        memberDao.insert(m);
        model.addAttribute("member", m);
        return "admin/addedmember";
    }
    
    @RequestMapping("/allmembers")
    public String allMembers(@RequestParam(defaultValue = "1") Integer page, ModelMap model) {
        List<Member1> members = memberDao.findByPage(page-1);
        model.addAttribute("members", members);
        model.addAttribute("totalpages", memberDao.pages());
        
        return "admin/allmembers";
    }
    
    @RequestMapping("/updatemember")
    public String updateMember(@RequestParam (required = true) Integer id, ModelMap model) {
        Member1 member = memberDao.findById(id);
        model.addAttribute("selectedMember", member);
        return "admin/updatemember";
    }
    
    @RequestMapping("/updatedmember")
    public String updatedMember (@RequestParam(required = true) Integer id, @RequestParam(required = true) String firstName, @RequestParam(required = true) String lastName, @RequestParam(required = true) String address, @RequestParam(required = true) String city, @RequestParam(required = true) String postalCode, @RequestParam(required = true) String email, ModelMap model) {

        Member1 m = memberDao.findById(id);
        m.setFirstName(firstName);
        m.setLastName(lastName);
        m.setAddress(address);
        m.setCity(city);
        m.setPostalCode(postalCode);
        m.setEmail(email);
        memberDao.update(m);
        model.addAttribute("member", m);
        return "admin/updatedmember";
    }
    
    @RequestMapping("/orders")
    public String allOrders(@RequestParam (defaultValue = "1") Integer page, ModelMap model) {
        List<Order1> orders = orderDao.findByPage(page-1);
        for (Order1 order : orders) {
            List<OrdersMovies> oms = omDao.findByOrder(order.getId());
            List<Movie> movies = new ArrayList<>();
            Movie m;
            for (OrdersMovies om : oms) {
                m = om.getMovieId();
                m.setQuantity(om.getQuantity());
                movies.add(m);
            }
            
            order.setMovieList(movies);
            
        }
        
        model.addAttribute("orders", orders);
        model.addAttribute("totalpages", orderDao.pages());
        return "admin/orders";
    }
    
    @RequestMapping("/deliver")
    public String deliver(@RequestParam Integer id, ModelMap model) {
        Order1 order = orderDao.findById(id);
        orderDao.deliver(order);
        model.addAttribute("order", order);
        
        return "admin/delivered";
    }
}
