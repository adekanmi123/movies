package com.movies.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "orders_movies")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrdersMovies.findAll", query = "SELECT o FROM OrdersMovies o"),
    @NamedQuery(name = "OrdersMovies.findById", query = "SELECT o FROM OrdersMovies o WHERE o.id = :id"),
    @NamedQuery(name = "OrdersMovies.findByOrder", query = "SELECT o FROM OrdersMovies o WHERE o.orderId.id = :id"),
    @NamedQuery(name = "OrdersMovies.findByQuantity", query = "SELECT o FROM OrdersMovies o WHERE o.quantity = :quantity")})
public class OrdersMovies implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quantity")
    private Integer quantity;
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Movie movieId;
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Order1 orderId;

    public OrdersMovies() {
    }

    public OrdersMovies(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Movie getMovieId() {
        return movieId;
    }

    public void setMovieId(Movie movieId) {
        this.movieId = movieId;
    }

    public Order1 getOrderId() {
        return orderId;
    }

    public void setOrderId(Order1 orderId) {
        this.orderId = orderId;
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
        if (!(object instanceof OrdersMovies)) {
            return false;
        }
        OrdersMovies other = (OrdersMovies) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.movies.model.OrdersMovies[ id=" + id + " ]";
    }

}
