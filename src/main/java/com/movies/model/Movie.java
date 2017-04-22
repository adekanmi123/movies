package com.movies.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "movies")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Movie.findAll", query = "SELECT m FROM Movie m"),
    @NamedQuery(name = "Movie.findById", query = "SELECT m FROM Movie m WHERE m.id = :id"),
    @NamedQuery(name = "Movie.findByTitle", query = "SELECT m FROM Movie m WHERE m.title = :title"),
    @NamedQuery(name = "Movie.findByPrice", query = "SELECT m FROM Movie m WHERE m.price = :price"),
    @NamedQuery(name = "Movie.findByImage", query = "SELECT m FROM Movie m WHERE m.image = :image"),
    @NamedQuery(name = "Movie.findByGenre", query = "SELECT m FROM Movie m WHERE m.genre.id = :genre AND m.onstock = 0"),
    @NamedQuery(name = "Movie.findByOnstock", query = "SELECT m FROM Movie m WHERE m.onstock = 0")})
public class Movie implements Serializable {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movieId")
    private List<OrdersMovies> ordersMoviesList;
    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 256)
    @Column(name = "title")
    private String title;
    @Size(max = 1024)
    @Column(name = "image")
    private String image;
    @Column(name = "onstock")
    private Integer onstock;
    @Transient
    private List<Order1> order1List;
    @JoinColumn(name = "genre", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Genre genre;
    
    @Transient
    private int quantity;

    public Movie() {
    }

    public Movie(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getOnstock() {
        return onstock;
    }

    public void setOnstock(Integer onstock) {
        this.onstock = onstock;
    }

    @XmlTransient
    public List<Order1> getOrder1List() {
        return order1List;
    }

    public void setOrder1List(List<Order1> order1List) {
        this.order1List = order1List;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.movies.model.Movie[ id=" + id + " ]";
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @XmlTransient
    public List<OrdersMovies> getOrdersMoviesList() {
        return ordersMoviesList;
    }

    public void setOrdersMoviesList(List<OrdersMovies> ordersMoviesList) {
        this.ordersMoviesList = ordersMoviesList;
    }

   

}
