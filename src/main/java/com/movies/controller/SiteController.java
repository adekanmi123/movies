package com.movies.controller;

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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SiteController {
    
    @Autowired
    GenreDao genreDao;
    
    @Autowired
    MovieDao movieDao;
    
    @Autowired
    OrderDao orderDao;
    
    @Autowired
    MemberDao memberDao;
    
    @Autowired
    OrdersMoviesDao ordersMoviesDao;
    
    
    
    @RequestMapping("/")
    public String index(ModelMap model) {
        List<Genre> genres = genreDao.find();
        List<Movie> movies = movieDao.find();
        model.addAttribute("genres", genres);
        model.addAttribute("movies", movies);
        return "index";        
    }
    
    @RequestMapping("/{id}")
    public String byGenre(@PathVariable int id, ModelMap model) {
        List<Genre> genres = genreDao.find();
        List<Movie> movies = movieDao.findByGenre(id);
        model.addAttribute("genres", genres);
        model.addAttribute("movies", movies);
        return "index";
        
    }
    
    @RequestMapping("/tocart/{id}")
    public String toCart(@PathVariable int id, ModelMap model) {
        List<Genre> genres = genreDao.find();        
        Movie movie = movieDao.findById(id);
        model.addAttribute("genres", genres);
        model.addAttribute("movie", movie);        
        return "tocart";
    }
    
    @RequestMapping("/addtocart")
    public String addToCart(ModelMap model, HttpServletRequest request, @RequestParam(required = true) int id, @RequestParam(required = true) int quantity) {
        List<Genre> genres = genreDao.find();
        model.addAttribute("genres", genres);
        HttpSession session = request.getSession();
        HashMap<Integer, Movie> cart;
        if(session.getAttribute("cart")==null) {
            session.setAttribute("cart", new HashMap<Integer, Movie>());
        } 
        
        cart=(HashMap<Integer, Movie>)session.getAttribute("cart");
        
        if(!cart.containsKey(id)) {
            Movie m = movieDao.findById(id);
            m.setQuantity(quantity);
            cart.put(id, m);
        } else {
            Movie cartMovie = cart.get(id);
            cartMovie.setQuantity(cartMovie.getQuantity() + quantity);
        }     
        
        return "addedtocart";   
    }
    
    @RequestMapping("/cart")
    public String cart(HttpServletRequest request, ModelMap model) {
        List<Genre> genres = genreDao.find();
        model.addAttribute("genres", genres);
        
        List<Movie> movies = new ArrayList<>();
        
        HttpSession session = request.getSession();
        
        if(session.getAttribute("cart")==null) {
            return "emptycart";
        } else {
            HashMap<Integer,Movie> sessionMovies = (HashMap<Integer,Movie>) session.getAttribute("cart");
            for (Map.Entry<Integer, Movie> m : sessionMovies.entrySet()) {
                movies.add(m.getValue());                
            }
        }
        
        double price = 0;
        for (Movie m : movies) {
            price += m.getPrice()*m.getQuantity();
        }
        
        model.addAttribute("movies", movies);
        model.addAttribute("price", price);
        
        return "cart";
    }
    
    @RequestMapping("/remove")
    public String remove(@RequestParam(required = true) int id, HttpServletRequest request, ModelMap model) {
        List<Genre> genres = genreDao.find();
        model.addAttribute("genres", genres);
        
        HttpSession session = request.getSession();
        if(session.getAttribute("cart")!=null) {
            HashMap<Integer,Movie> movies = (HashMap<Integer,Movie>) session.getAttribute("cart");
            if(movies.containsKey(id)){
                movies.remove(id);
            }
        }
        
        return "removedfromcart";
    }
    
    @RequestMapping("/confirm")
    public String confirmOrder(@RequestParam (required = false) String firstName, @RequestParam (required = false) String lastName, @RequestParam (required = false) String address, @RequestParam (required = false) String city, @RequestParam (required = false) String postalCode, @RequestParam (required = false) String email, ModelMap model, HttpServletRequest request) {
        List<Genre> genres = genreDao.find();
        model.addAttribute("genres", genres);
        
        List<Movie> movies = new ArrayList<>();
        
        HttpSession session = request.getSession();
        
        if(firstName!=null && lastName!=null && address!=null && city!=null && postalCode!=null && email!=null) {
            Order1 o = new Order1();
            Member1 user;
            if (memberDao.find(firstName, lastName, address, city, postalCode, email)==null && memberDao.findByEmail(email)!=null) {
                return "exist";
            } else if (memberDao.find(firstName, lastName, address, city, postalCode, email)==null) {
                user = new Member1();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setAddress(address);
                user.setCity(city);
                user.setPostalCode(postalCode);
                user.setEmail(email);
                memberDao.insert(user);
            } else {
                user=memberDao.find(firstName, lastName, address, city, postalCode, email);
            }
            o.setUser(user);
            o.setOrdertime(new Date());
            
            HashMap<Integer,Movie> sessionMovies = (HashMap<Integer,Movie>) session.getAttribute("cart");
            for (Map.Entry<Integer, Movie> m : sessionMovies.entrySet()) {
                movies.add(m.getValue());
            }
        
            o.setMovieList(movies);
            
            
            double price=0;
            for (Movie m : movies) {
                price +=m.getPrice() * m.getQuantity();
            }
            o.setPrice(price);
                        
            orderDao.save(o);
            
            for (Movie m : movies) {
               OrdersMovies om = new OrdersMovies(); 
               om.setMovieId(m);
               om.setQuantity(m.getQuantity());
               om.setOrderId(o);
               ordersMoviesDao.save(om);
            }
            
            session.removeAttribute("cart");
            return "confirmsuccess";
        }
        
        return "confirm";
        
    }
}
