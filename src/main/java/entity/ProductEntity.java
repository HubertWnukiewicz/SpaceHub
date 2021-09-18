package entity;

import com.example.demo1.Cordinates;
import com.example.demo1.JsonToMapConverter;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Map;

@Entity
@Table(name = "product", schema = "spring_docker", catalog = "")
public class ProductEntity {
    private int id;
    private String missionName;
    private Timestamp acquisitionDate;
    private String productFootprint;
    private Double price;
    private String url;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "mission_name", nullable = true, length = 32)
    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    @Basic
    @Column(name = "acquisition_date", nullable = true)
    public Timestamp getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Timestamp acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    @Basic
    @Column(name = "product_footprint", nullable = true, length = 64)
    public String getProductFootprint() {
        return productFootprint;
    }

    public void setProductFootprint(String productFootprint) {
        this.productFootprint = productFootprint;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "url", nullable = false, length = 64)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (id != that.id) return false;
        if (missionName != null ? !missionName.equals(that.missionName) : that.missionName != null) return false;
        if (acquisitionDate != null ? !acquisitionDate.equals(that.acquisitionDate) : that.acquisitionDate != null)
            return false;
        if (productFootprint != null ? !productFootprint.equals(that.productFootprint) : that.productFootprint != null)
            return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (missionName != null ? missionName.hashCode() : 0);
        result = 31 * result + (acquisitionDate != null ? acquisitionDate.hashCode() : 0);
        result = 31 * result + (productFootprint != null ? productFootprint.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
